package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import dev.bdon.engine.Point
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Box
import dev.bdon.engine.sprite.Label
import dev.bdon.engine.sprite.SpriteGroup
import java.awt.Color
import java.awt.Font
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

class BoxGrid(
    title: String,
    pos: Point,
    val dimensions: Dimensions,
    val boxSize: Int
): SpriteGroup() {

    val grid: MutableList<Array<Box>> = ArrayList<Array<Box>>().apply {
        for (y in 0 until dimensions.row) {
            val row = Array(dimensions.col) { grayBox(boxSize) }
            val adjustedSize = boxSize + PADDING
            val yValue = adjustedSize * y
            row.forEachIndexed { x, sprite ->
                sprite.x = pos.x + (adjustedSize * x)
                sprite.y = pos.y + yValue
            }
            add(0, row)
        }
    }

    val boxes: List<Box> = grid.flatMap { it.map { sprite -> sprite } }

    val topLeftCorner: Point
        get() = grid[dimensions.row - 1][0].position

    val topRightCorner: Point
        get() {
            val horizontalBoxes = grid[0].size
            val adjustedSize = boxSize + PADDING
            val totalWidth = horizontalBoxes * adjustedSize
            return topLeftCorner + Point(totalWidth, 0)
        }

    val bottomLeftCorner: Point
        get() {
            val verticalBoxes = grid.size
            val adjustedSize = boxSize + PADDING
            val totalHeight = verticalBoxes * adjustedSize
            return topLeftCorner + Point(0, totalHeight)
        }

    val widthInPixels: Int
        get() {
            return (topRightCorner - topLeftCorner).x
        }

    val heightInPixels: Int get() {
        val topLeftSprite = grid[0][0]
        val topLeftCorner = topLeftSprite.position
        return (bottomLeftCorner - topLeftCorner).y
    }

    private val label: Label = Label().apply {
        x = topLeftCorner.x
        y = topLeftCorner.y - (boxSize / 4)
        text = title
        font = Font("Arial", 0, max(10, (boxSize * .75).toInt()))
    }

    override val sprites = boxes.plus(label)


//    fun setColor(pos: Point, color: Color) {
//        grid[pos.x][pos.y].strokeColor = color
//    }

    fun place(tetrino: Tetrino, pos: Point) {
        tetrino.body.forEach {
            val x = pos.x + it.x
            val y = pos.y + it.y
            grid[y][x].strokeColor = tetrino.color
        }
    }

    fun clearRows(rows: SortedSet<Int>) {
        for (row in rows.reversed()) {
            grid.removeAt(row)
        }
        for (row in rows) {
            grid += Array(dimensions.col) { grayBox(boxSize) }
        }
    }

    fun clear() {
        this.boxes.forEach { it.strokeColor = GameBoard.UNOCCUPIED }
    }

    fun placeTopCenter(tetrino: Tetrino) {
        val center = Point((grid[0].size / 2) - 1, dimensions.row - 1)
        tetrino.body.forEach {
            val x = center.x + it.x
            val y = center.y + it.y
            grid[y][x].strokeColor = tetrino.color
        }
    }

    fun updateAll(gameBoard: GameBoard) {
        for (row in 0 until dimensions.row) {
            for (col in 0 until dimensions.col) {
                grid[row][col].strokeColor = gameBoard[row][col]
            }
        }
//        grid[0][0].strokeColor = Color.WHITE
//        grid[dimensions.row - 1][dimensions.col - 1].strokeColor = Color.YELLOW
    }

    fun remove(tetrino: Tetrino, pos: Point) {
        tetrino.body.forEach {
            val x = pos.x + it.x
            val y = pos.y + it.y
            grid[y][x].strokeColor = GameBoard.UNOCCUPIED
        }
    }

    private fun grayBox(size: Int) = Box().apply {
        height = size
        width = size
        fillColor = Color(0,0,0,0)
        strokeColor = GameBoard.UNOCCUPIED
    }

    companion object {
        const val PADDING = 4
    }
}