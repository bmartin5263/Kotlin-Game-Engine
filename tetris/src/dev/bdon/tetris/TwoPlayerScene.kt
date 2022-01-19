package dev.bdon.tetris

import dev.bdon.engine.Clock
import dev.bdon.engine.Point
import dev.bdon.engine.entity.*
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Label
import java.awt.event.KeyEvent
import kotlin.random.Random

class TwoPlayerScene : GameplayScene() {

    private val mainGame = TetrisGame(Constants.DEFAULT_DIM, Point(20f, 30f), 14, Random(seed))
    private val game7 = TetrisGame(Constants.DEFAULT_DIM, Point(400f, 30f), 14, Random(seed))

    override fun initialize() {
        println("${Clock.time} : TwoPlayerScene::initialize()")
        spawn(gameController, mainGame)
        spawn(game7)
        gameController.game = mainGame
        gameController.onKeyPress(KeyEvent.VK_X) {
            println("Close X")
            close()
        }

//        mainGame.whileKeyPressed(KeyEvent.VK_D) {
//            ui.move(2, 0)
//        }
//        mainGame.whileKeyPressed(KeyEvent.VK_S) {
//            ui.move(0, 2)
//        }
//        mainGame.whileKeyPressed(KeyEvent.VK_A) {
//            ui.move(-2, 0)
//        }
//        mainGame.whileKeyPressed(KeyEvent.VK_W) {
//            ui.move(0, -2)
//        }

//        val x = object : Entity() {
//            private val t = Label().apply {
//                size = 30
//            }
//            override fun draw(g: Graphics) {
////                val x = mainGame.ui.x.toString()
////                val y = mainGame.ui.y.toString()
////                t.text = "($x,$y)"
//                t.draw(g)
//            }
//        }
//        spawn(x)
    }

    override fun onEnter() {
        println("${Clock.time} : TwoPlayerScene::onEnter()")
    }

    override fun onExit() {
        println("${Clock.time} : TwoPlayerScene::onExit()")
    }

    override fun terminate() {
        println("${Clock.time} : TwoPlayerScene::terminate()")
    }
}