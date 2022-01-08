package com.bdon.tetris


import dev.bdon.engine.Clock
import dev.bdon.engine.Point
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.graphics.Graphics
import dev.bdon.tetris.*
import java.awt.Color

class Tetris(
    dimensions: Dimensions,
    pos: Point,
    blockSize: Int,
    private val blockGenerator: TetrinoGenerator = TetrinoGenerator()
) : Entity() {

    val gameBoard: GameBoard = GameBoard(dimensions)
    val boxGrid: BoxGrid = BoxGrid(pos, dimensions, blockSize)

    var row: Int = SPAWN_ROW
    var col: Int = SPAWN_COL
    var currentTetrino: Tetrino = Tetrinos.L0
    var lastDrop: Long = 0L
    var dropTime: Long = 60L

    private var color = Color.CYAN

    override fun draw(g: Graphics) {
        boxGrid.draw(g)
    }

    override fun update() {
        if (Clock.time >= lastDrop + dropTime) {
            moveDown()
        }
    }

    fun spawnNextBlock() {
        row = SPAWN_ROW
        col = SPAWN_COL
        currentTetrino = blockGenerator.next()
        place(currentTetrino)
    }

    fun moveDown() {
        lastDrop = Clock.time
        val success = attemptMove { ++row }
        if (!success) {
            spawnNextBlock()
        }
    }

    fun moveUp() {
        attemptMove { --row }
    }

    fun moveLeft() {
        attemptMove { --col }
    }

    fun moveRight() {
        attemptMove { ++col }
    }

    fun rotate() {
        attemptMove {
            currentTetrino = currentTetrino.rotate()
        }
    }

    private inline fun attemptMove(operations: Tetris.() -> Unit): Boolean {
        val rowSave = row
        val colSave = col
        val tetrinoSave = currentTetrino
        var success = true
        remove(currentTetrino)
        this.operations()
        if (!canPlace(currentTetrino)) {
            row = rowSave
            col = colSave
            currentTetrino = tetrinoSave
            success = false
        }
        place(currentTetrino)
        return success
    }

    private fun place(tetrino: Tetrino) {
        tetrino.body.forEach {
            val y = row + it.y
            val x = col + it.x
            boxGrid.grid[y][x].strokeColor = tetrino.color
        }
        gameBoard.place(tetrino, Point(col, row))
        boxGrid.place(tetrino, Point(col, row))
    }

    private fun canPlace(tetrino: Tetrino): Boolean {
        return gameBoard.canPlace(tetrino, Point(col, row))
    }

    private fun remove(tetrino: Tetrino) {
        gameBoard.remove(tetrino, Point(col, row))
        boxGrid.remove(tetrino, Point(col, row))
    }

    init {
        spawnNextBlock()
    }

    companion object {
        const val SPAWN_ROW = 0
        const val SPAWN_COL = 4
    }
}