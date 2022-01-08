package com.bdon.tetris


import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Box
import dev.bdon.tetris.TetrinoGenerator
import java.awt.Color

class TetrisGrid(
        posX: Int,
        posY: Int,
        size: Int,
        private val blockGenerator: TetrinoGenerator = TetrinoGenerator()
) : Entity() {

    private fun grayCell(size: Int) = Box().apply {
        height = size
        width = size
        fillColor = Color(0,0,0,0)
        strokeColor = Color.DARK_GRAY
    }

    private val grid: Array<Array<Box>> = Array(24) {
        Array(10) {
            grayCell(size)
        }
    }.apply {
        val adjustedSize = size + 4
        forEachIndexed { y, row ->
            val yValue = adjustedSize * y
            row.forEachIndexed { x, sprite ->
                sprite.x = posX + (adjustedSize * x)
                sprite.y = posY + yValue
            }
        }
    }

    val sprites: List<Box>
        get() = grid.flatMap { it.map { sprite -> sprite } }

    var row = SPAWN_ROW
    var col = SPAWN_COL
    var currentTetrino = Tetrino.L0
    var lastDrop = 0L
    var dropTime = 60L

    private var color = Color.CYAN

    override fun draw(g: Graphics) {
        grid.forEach { it.forEach { sprite -> sprite.draw(g) } }
    }

    override fun update() {
        if (Clock.time >= lastDrop + dropTime) {
            moveDown()
        }
    }

    fun spawnNewBlock() {
        row = SPAWN_ROW
        col = SPAWN_COL
        currentTetrino = blockGenerator.next()
        place(currentTetrino)
    }

    fun moveDown() {
        lastDrop = Clock.time
        val success = attemptMove { ++row }
        if (!success) {
            spawnNewBlock()
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

    inline fun attemptMove(operations: TetrisGrid.() -> Unit): Boolean {
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

    fun place(tetrino: Tetrino) {
        tetrino.body.forEach {
            val y = row + it.y
            val x = col + it.x
            if (y in 0..23 && x in 0..9) {
                grid[y][x].strokeColor = tetrino.color
            }
        }
    }

    fun canPlace(tetrino: Tetrino): Boolean {
        tetrino.body.forEach {
            val y = row + it.y
            val x = col + it.x
            if (y !in 0..23 || x !in 0..9 || grid[y][x].strokeColor != Color.DARK_GRAY) {
                return false
            }
        }
        return true
    }

    fun remove(tetrino: Tetrino) {
        tetrino.body.forEach {
            val y = row + it.y
            val x = col + it.x
            if (y in 0..23 && x in 0..9) {
                grid[y][x].strokeColor = Color.DARK_GRAY
            }
        }
    }

    init {
        spawnNewBlock()
    }

    companion object {
        const val SPAWN_ROW = 0
        const val SPAWN_COL = 4
    }
}