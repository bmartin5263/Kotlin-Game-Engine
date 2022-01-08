package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import dev.bdon.engine.Point
import java.awt.Color

class GameBoard(
    val dimensions: Dimensions
) {

    private val board: Array<Array<Color>> = Array(dimensions.row) { Array(dimensions.col) { UNOCCUPIED } }

    fun updateContentsOf(boxGrid: BoxGrid) {
        for (row in 0..dimensions.row) {
            for (col in 0..dimensions.col) {
                boxGrid.set(Point(row, col), board[row][col])
            }
        }
    }

    fun place(tetrino: Tetrino, pos: Point) {
        tetrino.body.forEach {
            val x = pos.x + it.x
            val y = pos.y + it.y
            board[y][x] = tetrino.color
        }
    }

    fun canPlace(tetrino: Tetrino, pos: Point): Boolean {
        tetrino.body.forEach {
            val y = pos.y + it.y
            val x = pos.x + it.x
            if (y !in 0 until dimensions.row || x !in 0 until dimensions.col || board[y][x] != UNOCCUPIED) {
                return false
            }
        }
        return true
    }

    fun remove(tetrino: Tetrino, pos: Point) {
        tetrino.body.forEach {
            val x = pos.x + it.x
            val y = pos.y + it.y
            board[y][x] = UNOCCUPIED
        }
    }

    companion object {
        val UNOCCUPIED = Color.DARK_GRAY
    }
}