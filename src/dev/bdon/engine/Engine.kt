package dev.bdon.engine

import dev.bdon.engine.entity.*
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.events.Timers
import dev.bdon.engine.graphics.Java2d
import dev.bdon.engine.scene.Scene
import java.util.*

object Engine {

    private val java2d = Java2d()
    private val entitySupplier = EntitySupplier(emptyList())
    private val sceneStack: Deque<Scene> = LinkedList()

    val currentScene get() = sceneStack.first!!

    fun launch(scene: Scene) {
        java2d.initialize(entitySupplier)

        sceneStack.push(scene)
        scene.onInitialize()

        Clock.start {
            entitySupplier.entities = currentScene.entities
            update()
            draw()
        }
    }

    private fun update() {
        entitySupplier.entities.forEach { it.update() }
        Keyboard.process(currentScene)
        Timers.process(currentScene)
    }

    private fun draw() {
        java2d.draw()
    }
}