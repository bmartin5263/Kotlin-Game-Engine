package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics
import java.awt.Color
import java.awt.Font

class Label : Sprite() {

    var text: String = "hello!"
    var color: Color = Color.GREEN
    var font: Font = Font("Arial", 0, 12)

    override fun render(g: Graphics) {
        g.color = color
        g.font = font
        g.drawString(text, x, y)
    }
}