package dev.bdon.engine

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.toEntity
import dev.bdon.engine.graphics.Box
import dev.bdon.engine.graphics.Java2d
import java.awt.Color
import java.awt.event.KeyEvent

class Engine {

    private val java2d = Java2d()
    private lateinit var entities: MutableList<Entity>

    fun launch() {
        entities = java2d.initialize()

        for (i in 0 until 10) {
            entities += Box().apply {
                x = 10 + (30 * i)
                y = 10
                fillColor = Color(0,0,0,0)
            }.toEntity().onUpdate {
                if (Keyboard.isKeyPressedImpl(KeyEvent.VK_A)) {
                    it.y += 1
                }
            }
        }

        while (true) {
            update()
            draw()
            Thread.sleep(10)
        }
    }

    fun update() {
        entities.forEach { it.update() }
    }

    fun draw() {
        java2d.draw()
    }
}