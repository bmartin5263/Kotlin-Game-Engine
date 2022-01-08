package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import com.bdon.tetris.Tetris
import dev.bdon.engine.Point
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Box
import java.awt.Color

class BoxGrid(
    pos: Point,
    dimensions: Dimensions,
    boxSize: Int
) {

    val grid: Array<Array<Box>> = Array(dimensions.row) {
        Array(dimensions.col) {
            grayCell(boxSize)
        }
    }.apply {
        val adjustedSize = boxSize + 4
        forEachIndexed { y, row ->
            val yValue = adjustedSize * y
            row.forEachIndexed { x, sprite ->
                sprite.x = pos.x + (adjustedSize * x)
                sprite.y = pos.y + yValue
            }
        }
    }

    val sprites: List<Box>
        get() = grid.flatMap { it.map { sprite -> sprite } }

    fun move(x: Int, y: Int) {
        sprites.forEach { it.move(x, y) }
    }

    fun draw(g: Graphics) {
        sprites.forEach { it.draw(g) }
    }

    fun set(pos: Point, color: Color) {
        grid[pos.x][pos.y].strokeColor = color
    }

    fun place(tetrino: Tetrino, pos: Point) {
        tetrino.body.forEach {
            val x = pos.x + it.x
            val y = pos.y + it.y
            grid[y][x].strokeColor = tetrino.color
        }
    }

    fun remove(tetrino: Tetrino, pos: Point) {
        tetrino.body.forEach {
            val x = pos.x + it.x
            val y = pos.y + it.y
            grid[y][x].strokeColor = GameBoard.UNOCCUPIED
        }
    }

    private fun grayCell(size: Int) = Box().apply {
        height = size
        width = size
        fillColor = Color(0,0,0,0)
        strokeColor = GameBoard.UNOCCUPIED
    }

}