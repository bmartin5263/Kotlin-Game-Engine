package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D

class Box : Sprite() {

    var width: Int = 20
    var height: Int = 20
    var fillColor: Color = Color.GREEN
    var strokeColor: Color = Color.GREEN
    var strokeWidth: Float = 1.0f

    override fun render(g: Graphics) {
        g.color = strokeColor
        g.stroke = BasicStroke(strokeWidth)
        g.drawRect(x, y, width, height)
        g.color = fillColor
        g.fillRect(x, y, width, height)
    }
}