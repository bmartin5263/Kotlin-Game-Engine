package dev.bdon.tetris

import dev.bdon.engine.IntPoint
import java.awt.Color


class Tetrino(
    val color: Color,
    val body: Array<IntPoint>
) {
    fun rotate(): Tetrino = Tetrinos.RIGHT_ROTATIONS.getValue(this)
}