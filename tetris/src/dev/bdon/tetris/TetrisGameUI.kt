package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import dev.bdon.engine.Point
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Box
import dev.bdon.engine.sprite.Sprite
import dev.bdon.engine.sprite.SpriteGroup
import java.awt.Color
import kotlin.random.Random

class TetrisGameUI(
    dimensions: Dimensions,
    pos: Point,
    blockSize: Int,
): SpriteGroup() {

    val boxGrid: BoxGrid = BoxGrid("Player 1", pos, dimensions, blockSize)
    private val nextGrid: BoxGrid = BoxGrid("Next", boxGrid.topRightCorner + Point(blockSize, 0), Dimensions(3, 5), blockSize)
    private val afterGrid: BoxGrid = BoxGrid("After", nextGrid.bottomLeftCorner + Point(0, blockSize), Dimensions(3, 5), blockSize)

    override val sprites: List<Sprite> = listOf(
        boxGrid, nextGrid, afterGrid
    )

    fun updateNextAndAfter(next: Tetrino, after: Tetrino) {
        nextGrid.clear()
        nextGrid.placeTopCenter(next)
        afterGrid.clear()
        afterGrid.placeTopCenter(after)
    }
}