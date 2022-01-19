package dev.bdon.engine.graphics

import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.Stroke
import java.text.AttributedCharacterIterator

class AwtGraphics(private val graphics2D: Graphics2D) : Graphics {

    override var color: Color
        get() = graphics2D.color
        set(value) { graphics2D.color = value }

    override var font: Font
        get() = graphics2D.font
        set(value) { graphics2D.font = value }

    override var stroke: Stroke
        get() = graphics2D.stroke
        set(value) { graphics2D.stroke = value }

    override fun drawRect(x: Float, y: Float, width: Float, height: Float) {
        graphics2D.drawRect(x.toInt(), y.toInt(), width.toInt(), height.toInt())
    }

    override fun fillRect(x: Float, y: Float, width: Float, height: Float) {
        graphics2D.fillRect(x.toInt(), y.toInt(), width.toInt(), height.toInt())
    }

    override fun drawString(string: String, x: Float, y: Float) {
//        graphics2D.font = Font("Arial", 0, 30)
        graphics2D.drawString(string, x.toInt(), y.toInt())
    }

    override fun drawString(iter: AttributedCharacterIterator, x: Float, y: Float) {
        graphics2D.drawString(iter, x.toInt(), y.toInt())
    }
}