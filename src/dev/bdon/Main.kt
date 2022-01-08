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
                register(grid)
                register(TetrisGrid(400, 20, 5))

                grid.script {
                    whileKeyPressed(KeyEvent.VK_DOWN, 10) {
                        moveDown()
                    }
                    whileKeyPressed(KeyEvent.VK_UP) {
                        moveUp()
                    }
                    whileKeyPressed(KeyEvent.VK_LEFT) {
                        moveLeft()
                    }
                    whileKeyPressed(KeyEvent.VK_RIGHT) {
                        moveRight()
                    }
                }
            }
        }
        launch(scene)
    }
}