package dev.bdon.engine.graphics

import java.awt.Color
import java.awt.Font
import java.awt.Stroke
import java.text.AttributedCharacterIterator

interface Graphics {
    var color: Color
    var font: Font
    var stroke: Stroke

    fun drawRect(x: Float, y: Float, width: Float, height: Float)
    fun drawString(string: String, x: Float, y: Float)
    fun drawString(iter: AttributedCharacterIterator, x: Float, y: Float)
    fun fillRect(x: Float, y: Float, width: Float, height: Float)
}