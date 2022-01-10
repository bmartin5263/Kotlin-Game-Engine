package dev.bdon.tetris

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.onKeyPress
import dev.bdon.engine.entity.toEntity
import dev.bdon.engine.entity.whileKeyPressed
import dev.bdon.engine.scene.Scene
import dev.bdon.engine.sprite.Label
import java.awt.Font
import java.awt.event.KeyEvent

class TestScene : Scene() {

    override fun initialize() {
        println("${Clock.time} : TestScene::initialize()")
        val label = Label().apply {
            text = "Press Space"
            x = 100
            y = 100
            font = Font("Arial", Font.PLAIN, 50)
        }.toEntity()

        label.whileKeyPressed(KeyEvent.VK_RIGHT) {
            sprite.move(20, 0)
        }

        label.onKeyPress(KeyEvent.VK_SPACE) {
            println("Open Tetris Scene Space")
            TetrisScene().open()
        }
        label.onKeyPress(KeyEvent.VK_X) {
            println("Open Tetris Scene X")
            TetrisScene().open()
        }

        spawn(label)
    }

    override fun terminate() {
        println("${Clock.time} : TestScene::terminate()")
    }

    override fun onEnter() {
        println("${Clock.time} : TestScene::onEnter()")
    }

    override fun onExit() {
        println("${Clock.time} : TestScene::onExit()")
    }
}