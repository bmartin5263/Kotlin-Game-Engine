package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics
import java.awt.Color
import java.awt.Font

class Label : Sprite() {

    var text: String = "Hello, World!"
    var color: Color = Color.GREEN
    var font: Font = Font("Arial", Font.PLAIN, 12)

    var fontName: String
        get() = font.fontName
        set(value) {
            font = Font(value, font.style, font.size)
        }

    var bold: Boolean
        get() = font.isBold
        set(value) {
            font = font.deriveFont(applyStyle(font.style, Font.BOLD, value))
        }

    var italic: Boolean
        get() = font.isBold
        set(value) {
            font = font.deriveFont(applyStyle(font.style, Font.ITALIC, value))
        }

    private fun applyStyle(style: Int, flag: Int, value: Boolean): Int {
        return if (value) {
            style.or(flag)
        } else {
            style.and(flag.inv())
        }
    }

    override fun render(g: Graphics) {
        g.color = color
        g.font = font
        g.drawString(text, x, y)
    }
}