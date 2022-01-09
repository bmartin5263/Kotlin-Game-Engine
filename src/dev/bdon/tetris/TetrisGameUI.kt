package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import dev.bdon.engine.Point
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Box
import java.awt.Color
import kotlin.random.Random

class TetrisGameUI(
    dimensions: Dimensions,
    pos: Point,
    blockSize: Int,
) {

    val boxGrid: BoxGrid = BoxGrid("Player 1", pos, dimensions, blockSize)
    val nextGrid: BoxGrid = BoxGrid("Next", boxGrid.topRightCorner + Point(blockSize, 0), Dimensions(3, 5), blockSize)
    val afterGrid: BoxGrid = BoxGrid("After", nextGrid.bottomLeftCorner + Point(0, blockSize), Dimensions(3, 5), blockSize)
//    val border: Box = Box().apply {
//        x = pos.x - blockSize
//        y = pos.y - blockSize
//        width = boxGrid.widthInPixels + (blockSize * 2)
//        height = boxGrid.heightInPixels + (blockSize * 2)
//        fillColor = Color(0,0,0,0)
//        strokeColor = Color.WHITE
//    }
    val box: Box = Box().apply {
        width = 20
        height = 20
    }

    fun draw(g: Graphics) {
        boxGrid.draw(g)
        nextGrid.draw(g)
        afterGrid.draw(g)
//        border.draw(g)
        box.draw(g)
    }

    fun updateNextAndAfter(next: Tetrino, after: Tetrino) {
        nextGrid.clear()
        nextGrid.placeTopCenter(next)
        afterGrid.clear()
        afterGrid.placeTopCenter(after)
    }
}