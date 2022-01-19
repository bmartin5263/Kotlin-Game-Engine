package dev.bdon.tetris

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.onKeyPress
import dev.bdon.engine.entity.whileKeyPressed
import java.awt.event.KeyEvent

class GameController : Entity() {
    var game: TetrisGame? = null
    override fun onSpawn() {
        whileKeyPressed(KeyEvent.VK_DOWN, 3) {
            game?.moveDown()
        }
        whileKeyPressed(KeyEvent.VK_UP, 3) {
            game?.moveUp()
        }
        whileKeyPressed(KeyEvent.VK_LEFT, 3) {
            game?.moveLeft()
        }
        whileKeyPressed(KeyEvent.VK_RIGHT, 3) {
            game?.moveRight()
        }
        onKeyPress(KeyEvent.VK_SPACE) {
            game?.rotate()
        }
    }
}