package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics
import java.awt.BasicStroke
import java.awt.Color

class Box : AbstractSprite() {

    var width: Float = 20f
    var height: Float = 20f
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