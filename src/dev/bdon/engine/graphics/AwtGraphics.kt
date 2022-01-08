package dev.bdon.engine.graphics

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Stroke

class AwtGraphics(private val graphics2D: Graphics2D) : Graphics {

    override var color: Color
        get() = graphics2D.color
        set(value) { graphics2D.color = value }

    override var stroke: Stroke
        get() = graphics2D.stroke
        set(value) { graphics2D.stroke = value }

    override fun drawRect(x: Int, y: Int, width: Int, height: Int) {
        graphics2D.drawRect(x, y, width, height)
    }

    override fun fillRect(x: Int, y: Int, width: Int, height: Int) {
        graphics2D.fillRect(x, y, width, height)
    }
}