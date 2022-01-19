package dev.bdon.engine

import dev.bdon.engine.ecs.HasId
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.graphics.Java2d
import dev.bdon.engine.scene.EmptyScene
import dev.bdon.engine.scene.Scene
import dev.bdon.engine.util.EcsArray
import dev.bdon.engine.util.IdGenerator
import java.util.*

object Engine {

    private val java2d = Java2d()
    private val sceneStack: Deque<Scene> = LinkedList()
    private val sceneChangeQueue: Deque<Scene> = LinkedList()
    private val sceneRemovalQueue: Deque<Scene> = LinkedList()

    val currentScene get() = sceneStack.first!!

    fun launch(initialScene: Scene) {
        sceneStack.push(EmptyScene)
        java2d.initialize()
        Keyboard.keySource = java2d.keySource

        requestSceneChange(initialScene)

        Clock.start(Engine::onTick)
    }

    private fun onTick() {
        doSceneChangeRequests()
        update()
        draw()
        doSceneRemovalRequests()
    }

    fun entities(): Iterator<Entity> {
        return currentScene.liveEntities
    }

    private fun update() {
        currentScene.nextFrame()
    }

    private fun draw() {
        java2d.draw()
    }

    private fun doSceneChangeRequests() {
        while (sceneChangeQueue.isNotEmpty()) {
            currentScene.onExit()
            pushScene(sceneChangeQueue.poll()!!)
        }
    }

    private fun doSceneRemovalRequests() {
        if (sceneRemovalQueue.isNotEmpty()) {
            while (sceneRemovalQueue.isNotEmpty()) {
                val sceneToRemove = sceneRemovalQueue.poll()!!
                if (sceneToRemove === currentScene) {
                    currentScene.onExit()
                    currentScene.terminate()
                    sceneStack.pop()
                }
                else if (sceneToRemove in sceneStack) {
                    sceneToRemove.terminate()
                    sceneStack.remove(sceneToRemove)
                }
            }
            if (sceneStack.size <= 1) {
                Clock.running = false
            }
            else {
                val delta = currentScene.frameDelta()
                if (delta > 1) {
                    println("FAST FORWARD")
                    currentScene.fastForward(delta)
                }
                currentScene.onEnter()
            }
        }
    }

    internal fun requestSceneChange(scene: Scene) {
        sceneChangeQueue.add(scene)
    }

    internal fun requestSceneRemoval(scene: Scene) {
        sceneRemovalQueue.add(scene)
    }

    private fun pushScene(scene: Scene) {
        sceneStack.push(scene)
        scene.initialize()
        scene.onEnter()
    }

    private fun popScene() {
        currentScene.onExit()
        currentScene.terminate()
        sceneStack.pop()
    }

    internal inline fun <reified T : HasId> createEcsArray(): EcsArray<T> {
        @Suppress("USELESS_CAST") val array = Array(Config.maxEntities) { null as T? }
        val idGenerator = IdGenerator(Config.maxEntities)
        return EcsArray(array, idGenerator)
    }
}