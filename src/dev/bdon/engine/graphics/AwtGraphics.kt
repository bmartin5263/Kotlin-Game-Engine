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

    override fun drawRect(x: Int, y: Int, width: Int, height: Int) {
        graphics2D.drawRect(x, y, width, height)
    }

    override fun fillRect(x: Int, y: Int, width: Int, height: Int) {
        graphics2D.fillRect(x, y, width, height)
    }

    override fun drawString(string: String, x: Int, y: Int) {
//        graphics2D.font = Font("Arial", 0, 30)
        graphics2D.drawString(string, x, y)
    }

    override fun drawString(string: String, x: Float, y: Float) {
        graphics2D.drawString(string, x, y)
    }

    override fun drawString(iter: AttributedCharacterIterator, x: Int, y: Int) {
        graphics2D.drawString(iter, x, y)
    }

    override fun drawString(iter: AttributedCharacterIterator, x: Float, y: Float) {
        graphics2D.drawString(iter, x, y)
    }
}