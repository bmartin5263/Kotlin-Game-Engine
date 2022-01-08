package com.bdon.tetris

import dev.bdon.tetris.IntPoint
import java.awt.Color


class Tetrino private constructor(val color: Color, val body: Array<IntPoint>) {

    fun rotate(): Tetrino = RIGHT_ROTATIONS.getValue(this)

    companion object {

        private fun point(x: Int, y: Int) = IntPoint(x, y)

        val L0 = Tetrino(Color.CYAN, arrayOf(point(0,0), point(0,1), point(0,2), point(1,2)))
        val L1 = Tetrino(Color.CYAN, arrayOf(point(0,0), point(0,1), point(1,0), point(2,0)))
        val L2 = Tetrino(Color.CYAN, arrayOf(point(0,0), point(1,0), point(1,1), point(1,2)))
        val L3 = Tetrino(Color.CYAN, arrayOf(point(0,1), point(1,1), point(2,1), point(2,0)))

        private val RIGHT_ROTATIONS = mapOf(
                L0 to L1,
                L1 to L2,
                L2 to L3,
                L3 to L0
        )
    }

}