package dev.bdon.engine.graphics

import java.awt.Graphics2D

abstract class Sprite {

    var x: Int = 0
    var y: Int = 0

    fun move(x: Int, y: Int) {
        this.x += x
        this.y += y
    }

    abstract fun render(g: Graphics2D)
}