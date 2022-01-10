package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import dev.bdon.engine.Point
import java.awt.Color
import java.util.*
import kotlin.collections.ArrayList

class GameBoard(
    val dimensions: Dimensions
) {

//    private val board: Array<Array<Color>> = Array(dimensions.row) { Array(dimensions.col) { UNOCCUPIED } }

    private val board: MutableList<Array<Color>> = ArrayList<Array<Color>>(dimensions.col).apply {
        for (row in 0 until dimensions.row) {
            add(Array(dimensions.col) { UNOCCUPIED } )
        }
    }

    operator fun get(index: Int): Array<Color> {
        return board[index]
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

    fun clearRows(tetrino: Tetrino, pos: Point) {
        val rowsToClear: SortedSet<Int> = TreeSet()
        tetrino.body.forEach {
            val row = pos.y + it.y
            val shouldClear = board[row].none { cell -> cell == UNOCCUPIED }
            if (shouldClear) {
                rowsToClear += row
            }
        }
        for (row in rowsToClear.reversed()) {
            board.removeAt(row)
        }
        for (row in rowsToClear) {
            board += Array(dimensions.col) { UNOCCUPIED }
        }
    }

    companion object {
        val UNOCCUPIED: Color = Color.DARK_GRAY
    }
}