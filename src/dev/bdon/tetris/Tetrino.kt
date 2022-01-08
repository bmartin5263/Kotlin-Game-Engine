package com.bdon.tetris

import dev.bdon.tetris.IntPoint
import java.awt.Color
import kotlin.random.Random


class Tetrino private constructor(
    val color: Color,
    val body: Array<IntPoint>
) {

    fun rotate(): Tetrino = RIGHT_ROTATIONS.getValue(this)

    companion object {
        fun random(): Tetrino {
            return RANDOM_LIST[Random.nextInt(RANDOM_LIST.size)]
        }
        private fun point(x: Int, y: Int) = IntPoint(x, y)

        val L0 = Tetrino(Color.CYAN, arrayOf(point(0,0), point(0,1), point(0,2), point(1,2)))
        val L1 = Tetrino(Color.GREEN, arrayOf(point(0,0), point(0,1), point(1,0), point(2,0)))
        val L2 = Tetrino(Color.RED, arrayOf(point(0,0), point(1,0), point(1,1), point(1,2)))
        val L3 = Tetrino(Color.YELLOW, arrayOf(point(0,1), point(1,1), point(2,1), point(2,0)))

        val O0 = Tetrino(Color.YELLOW, arrayOf(point(0,0), point(0, 1), point(1, 0), point(1, 1)))

        val I0 = Tetrino(Color.RED, arrayOf(point(0, 0), point(0, 1), point(0, 2), point(0, 3)))
        val I1 = Tetrino(Color.RED, arrayOf(point(0, 0), point(1, 1), point(2, 1), point(3, 1)))

        private val RIGHT_ROTATIONS = mapOf(
            L0 to L1,
            L1 to L2,
            L2 to L3,
            L3 to L0,

            O0 to O0,

            I0 to I1,
            I1 to I0
        )

        private val RANDOM_LIST = listOf(
            L0,
            O0,
            I0
        )
    }

}