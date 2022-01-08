package dev.bdon

import com.bdon.tetris.TetrisGrid
import dev.bdon.engine.Engine.launch
import kotlin.jvm.JvmStatic
import dev.bdon.engine.Engine
import dev.bdon.engine.entity.script
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.scene.AnonymousScene
import java.awt.event.KeyEvent

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val scene = AnonymousScene().apply {
            onInitializeFn = {
                val grid = TetrisGrid(20, 20, 20)
                val grid2 = TetrisGrid(400, 20, 5)
                register(grid)
                register(grid2)

                grid.script {
                    whileKeyPressed(KeyEvent.VK_DOWN, 4) {
                        moveDown()
                    }
                    whileKeyPressed(KeyEvent.VK_UP, 4) {
                        moveUp()
                    }
                    whileKeyPressed(KeyEvent.VK_LEFT, 4) {
                        moveLeft()
                    }
                    whileKeyPressed(KeyEvent.VK_RIGHT, 4) {
                        moveRight()
                    }
                    onKeyPress(KeyEvent.VK_SPACE) {
                        rotate()
                    }
                }

                grid2.script {
                    whileKeyPressed(KeyEvent.VK_S, 4) {
                        moveDown()
                    }
                    whileKeyPressed(KeyEvent.VK_W, 4) {
                        moveUp()
                    }
                    whileKeyPressed(KeyEvent.VK_A, 4) {
                        moveLeft()
                    }
                    whileKeyPressed(KeyEvent.VK_D, 4) {
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