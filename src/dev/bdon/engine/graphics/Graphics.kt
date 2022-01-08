package dev.bdon.engine.graphics

import java.awt.Color
import java.awt.Stroke

interface Graphics {
    var color: Color
    var stroke: Stroke

    fun drawRect(x: Int, y: Int, width: Int, height: Int)
    fun fillRect(x: Int, y: Int, width: Int, height: Int)
}