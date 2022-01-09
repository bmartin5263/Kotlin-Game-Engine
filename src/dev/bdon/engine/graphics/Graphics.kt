package dev.bdon.engine.graphics

import java.awt.Color
import java.awt.Font
import java.awt.Stroke
import java.text.AttributedCharacterIterator

interface Graphics {
    var color: Color
    var font: Font
    var stroke: Stroke

    fun drawRect(x: Int, y: Int, width: Int, height: Int)
    fun drawString(string: String, x: Int, y: Int)
    fun drawString(string: String, x: Float, y: Float)
    fun drawString(iter: AttributedCharacterIterator, x: Int, y: Int)
    fun drawString(iter: AttributedCharacterIterator, x: Float, y: Float)
    fun fillRect(x: Int, y: Int, width: Int, height: Int)
}