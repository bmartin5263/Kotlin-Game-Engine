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

class FivePlayerScene : GameplayScene() {

    private val mainGame = TetrisGame(Constants.DEFAULT_DIM, Point(20f, 30f), 14, Random(seed))
    private val game1 = TetrisGame(Constants.DEFAULT_DIM, Point(600f, 30f), 5, Random(seed))
    private val game2 = TetrisGame(Constants.DEFAULT_DIM, Point(800f, 30f), 5, Random(seed))
    private val game5 = TetrisGame(Constants.DEFAULT_DIM, Point(600f, 270f), 5, Random(seed))
    private val game6 = TetrisGame(Constants.DEFAULT_DIM, Point(800f, 270f), 5, Random(seed))

//    private val gamesIterator = arrayOf(mainGame, /*game1, game2, game3, game4, game5, game6, game7, game8 */).cycleIterator()

    override fun initialize() {
        println("${Clock.time} : FivePlayerScene::initialize()")
        spawn(gameController, mainGame)
        spawn(game1, game2, game5, game6)
        gameController.game = mainGame
        gameController.onKeyPress(KeyEvent.VK_X) {
            println("Close X")
            close()
        }
    }

    override fun onEnter() {
        println("${Clock.time} : FivePlayerScene::onEnter()")
    }

    override fun onExit() {
        println("${Clock.time} : FivePlayerScene::onExit()")
    }

    override fun terminate() {
        println("${Clock.time} : FivePlayerScene::terminate()")
    }
}