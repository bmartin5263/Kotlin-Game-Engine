package dev.bdon.engine

import dev.bdon.engine.entity.*
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.graphics.Java2d
import dev.bdon.engine.scene.EmptyScene
import dev.bdon.engine.scene.Scene
import java.util.*

object Engine {

    private val java2d = Java2d()

    private val sceneStack: Deque<Scene> = LinkedList<Scene>().apply { add(EmptyScene) }
    private val sceneChangeQueue: Deque<Scene> = LinkedList()
    private val sceneRemovalQueue: Deque<Scene> = LinkedList()

    private val currentScene get() = sceneStack.first!!

    fun launch(initialScene: Scene) {
        java2d.initialize()
        Keyboard.keySource = java2d.keySource
        requestSceneChange(initialScene)

        Clock.start {
            doSceneChangeRequests()
            update()
            draw()
            doSceneRemovalRequests()
        }
    }

    fun entities(): Set<Entity> {
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
}