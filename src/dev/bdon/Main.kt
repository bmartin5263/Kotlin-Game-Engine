package dev.bdon

import com.bdon.tetris.Tetris
import dev.bdon.engine.Engine.launch
import dev.bdon.engine.Point
import kotlin.jvm.JvmStatic
import dev.bdon.engine.entity.script
import dev.bdon.engine.scene.AnonymousScene
import dev.bdon.tetris.Constants
import java.awt.event.KeyEvent

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val scene = AnonymousScene().apply {
            onInitializeFn = {
                val grid = Tetris(Constants.DEFAULT_DIM, Point(20, 20), 20)
                val grid2 = Tetris(Constants.DEFAULT_DIM, Point(400, 20), 5)
                register(grid)
                register(grid2)

                grid.script {
                    whileKeyPressed(KeyEvent.VK_DOWN, 2) {
                        moveDown()
                    }
                    whileKeyPressed(KeyEvent.VK_UP, 2) {
                        moveUp()
                    }
                    whileKeyPressed(KeyEvent.VK_LEFT, 2) {
                        moveLeft()
                    }
                    whileKeyPressed(KeyEvent.VK_RIGHT, 2) {
                        moveRight()
                    }
                    onKeyPress(KeyEvent.VK_SPACE) {
                        rotate()
                    }
                }

                grid2.script {
                    whileKeyPressed(KeyEvent.VK_S, 2) {
                        moveDown()
                    }
                    whileKeyPressed(KeyEvent.VK_W, 2) {
                        moveUp()
                    }
                    whileKeyPressed(KeyEvent.VK_A, 2) {
                        moveLeft()
                    }
                    whileKeyPressed(KeyEvent.VK_D, 2) {
                        moveRight()
                    }
                    onKeyPress(KeyEvent.VK_Q) {
                        rotate()
                    }
                }
            }
        }
        launch(scene)
    }
}