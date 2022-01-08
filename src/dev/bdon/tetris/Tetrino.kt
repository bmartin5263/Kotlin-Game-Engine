package com.bdon.tetris

import dev.bdon.engine.Point
import dev.bdon.tetris.Tetrinos
import java.awt.Color
import kotlin.random.Random


class Tetrino(
    val color: Color,
    val body: Array<Point>
) {
    fun rotate(): Tetrino = Tetrinos.RIGHT_ROTATIONS.getValue(this)
}