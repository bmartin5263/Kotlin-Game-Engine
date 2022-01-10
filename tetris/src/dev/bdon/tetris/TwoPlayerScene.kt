package dev.bdon.tetris

import dev.bdon.engine.Clock
import dev.bdon.engine.Point
import dev.bdon.engine.entity.interval
import dev.bdon.engine.entity.onKeyPress
import dev.bdon.engine.entity.timeout
import dev.bdon.engine.scene.Scene
import dev.bdon.engine.util.cycleIterator
import java.awt.event.KeyEvent
import kotlin.random.Random

class TwoPlayerScene : GameplayScene() {

    private val mainGame = TetrisGame(Constants.DEFAULT_DIM, Point(20, 30), 14, Random(seed))
    private val game7 = TetrisGame(Constants.DEFAULT_DIM, Point(400, 30), 14, Random(seed))

    override fun initialize() {
        println("${Clock.time} : TwoPlayerScene::initialize()")
        spawn(gameController, mainGame)
        spawn(game7)
        gameController.game = mainGame
        gameController.onKeyPress(KeyEvent.VK_X) {
            println("Close X")
            close()
        }
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