package dev.bdon.tetris

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.onKeyPress
import dev.bdon.engine.entity.toEntity
import dev.bdon.engine.entity.whileKeyPressed
import dev.bdon.engine.scene.Scene
import dev.bdon.engine.sprite.Label
import java.awt.Font
import java.awt.event.KeyEvent

class MainMenuScene : Scene() {

    override fun initialize() {
        println("${Clock.time} : MainMenuScene::initialize()")
        val label = Label().apply {
            text = "Press '2' for Two Player Scene, Press '5' for Five Player Scene"
            x = 100
            y = 100
            font = Font("Arial", Font.PLAIN, 30)
        }.toEntity()

        label.whileKeyPressed(KeyEvent.VK_RIGHT) {
            sprite.move(20, 0)
        }

        label.onKeyPress(KeyEvent.VK_2) {
            TwoPlayerScene().open()
        }
        label.onKeyPress(KeyEvent.VK_5) {
            FivePlayerScene().open()
        }

        spawn(label)
    }

    override fun terminate() {
        println("${Clock.time} : MainMenuScene::terminate()")
    }

    override fun onEnter() {
        println("${Clock.time} : MainMenuScene::onEnter()")
    }

    override fun onExit() {
        println("${Clock.time} : MainMenuScene::onExit()")
    }
}