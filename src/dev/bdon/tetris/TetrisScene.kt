package dev.bdon.tetris

import dev.bdon.engine.Point
import dev.bdon.engine.entity.onKeyPress
import dev.bdon.engine.entity.script
import dev.bdon.engine.entity.whileKeyPressed
import dev.bdon.engine.scene.Scene
import java.awt.event.KeyEvent
import kotlin.random.Random

class TetrisScene : Scene() {

    private val grid = TetrisGame(Constants.DEFAULT_DIM, Point(40, 40), 20, Random(123))
    private val grid2 = TetrisGame(Constants.DEFAULT_DIM, Point(600, 40), 5, Random(123))

    override fun initialize() {
        register(grid)
        register(grid2)


        grid.whileKeyPressed(KeyEvent.VK_DOWN, 3) {
            moveDown()
        }
        grid.whileKeyPressed(KeyEvent.VK_UP, 3) {
            moveUp()
        }
        grid.whileKeyPressed(KeyEvent.VK_LEFT, 3) {
            moveLeft()
        }
        grid.whileKeyPressed(KeyEvent.VK_RIGHT, 3) {
            moveRight()
        }
        grid.onKeyPress(KeyEvent.VK_SPACE) {
            rotate()
        }

        grid2.whileKeyPressed(KeyEvent.VK_S, 3) {
            moveDown()
        }
        grid2.whileKeyPressed(KeyEvent.VK_W, 3) {
            moveUp()
        }
        grid2.whileKeyPressed(KeyEvent.VK_A, 3) {
            moveLeft()
        }
        grid2.whileKeyPressed(KeyEvent.VK_D, 3) {
            moveRight()
        }
        grid2.onKeyPress(KeyEvent.VK_Q) {
            rotate()
        }

    }
}