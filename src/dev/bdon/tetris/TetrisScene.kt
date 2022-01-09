package dev.bdon.tetris

import dev.bdon.engine.Point
import dev.bdon.engine.entity.onKeyPress
import dev.bdon.engine.scene.Scene
import dev.bdon.engine.util.cycleIterator
import java.awt.event.KeyEvent
import kotlin.random.Random

class TetrisScene : Scene() {

    private val gameController = GameController()

    private val mainGame = TetrisGame(Constants.DEFAULT_DIM, Point(40, 40), 20, Random(123), false)
    private val game1 = TetrisGame(Constants.DEFAULT_DIM, Point(500, 40), 5, Random(123))
    private val game2 = TetrisGame(Constants.DEFAULT_DIM, Point(700, 40), 5, Random(123))
    private val game3 = TetrisGame(Constants.DEFAULT_DIM, Point(900, 40), 5, Random(123))
    private val game4 = TetrisGame(Constants.DEFAULT_DIM, Point(1100, 40), 5, Random(123))
    private val game5 = TetrisGame(Constants.DEFAULT_DIM, Point(500, 440), 5, Random(123))
    private val game6 = TetrisGame(Constants.DEFAULT_DIM, Point(700, 440), 5, Random(123))
    private val game7 = TetrisGame(Constants.DEFAULT_DIM, Point(900, 440), 5, Random(123))
    private val game8 = TetrisGame(Constants.DEFAULT_DIM, Point(1100, 440), 5, Random(123))

    private val gamesIterator = arrayOf(mainGame, game1, game2, game3, game4, game5, game6, game7, game8).cycleIterator()

    override fun initialize() {
        spawn(gameController, mainGame, game1, game2, game3, game4, game5, game6, game7, game8)
        gameController.game = gamesIterator.next()
        gameController.onKeyPress(KeyEvent.VK_O) {
            println("O")
            game = gamesIterator.previous()
        }
        gameController.onKeyPress(KeyEvent.VK_P) {
            println("P")
            game = gamesIterator.next()
        }

    }
}