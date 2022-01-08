package com.bdon.tetris


import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.script
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.Box
import dev.bdon.engine.sprite.Sprite
import java.awt.Color
import java.awt.event.KeyEvent

class TetrisGrid(
        startX: Int,
        startY: Int,
        size: Int
) : Entity() {

//    override fun onSpawn() {
//        script {
//            whileKeyPress(KeyEvent.VK_UP) {
//
//            }
//        }
//    }

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
        forEachIndexed { y, row ->
            row.forEachIndexed { x, sprite ->
                sprite.x = startX + (24 * x)
                sprite.y = startY + (24 * y)
            }
        }
    }

    val sprites: List<Box>
        get() = grid.flatMap { it.map { sprite -> sprite } }

    var i = 0
    var j = 0
    var currentTetrino = Tetrino.L0

    private var color = Color.CYAN

    override fun draw(g: Graphics) {
        grid.forEach { it.forEach { sprite -> sprite.draw(g) } }
    }

    fun moveDown() {
        remove(currentTetrino)
        ++i
        place(currentTetrino)
    }

    fun moveUp() {
        remove(currentTetrino)
        --i
        place(currentTetrino)
    }

    fun moveLeft() {
        remove(currentTetrino)
        --j
        place(currentTetrino)
    }

    fun moveRight() {
        remove(currentTetrino)
        ++j
        place(currentTetrino)
    }

    fun place(tetrino: Tetrino) {
        tetrino.body.forEach {
            val y = i + it.y
            val x = j + it.x
            if (y in 0..23 && x in 0..9) {
                grid[y][x].strokeColor = tetrino.color
            }
        }
    }

    fun remove(tetrino: Tetrino) {
        tetrino.body.forEach {
            val y = i + it.y
            val x = j + it.x
            if (y in 0..23 && x in 0..9) {
                grid[y][x].strokeColor = Color.DARK_GRAY
            }
        }
    }

    init {
        place(currentTetrino)
    }
}