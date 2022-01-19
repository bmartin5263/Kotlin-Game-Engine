package dev.bdon.tetris

import dev.bdon.engine.IntPoint
import dev.bdon.engine.Point
import java.awt.Color
import kotlin.random.Random

object Tetrinos {
    fun random(): Tetrino {
        return RANDOM_LIST[Random.nextInt(RANDOM_LIST.size)]
    }

    private fun point(x: Int, y: Int) = IntPoint(x, y)
    private val FUCHSIA = Color(153, 0, 255)

    val L0 = Tetrino(Color.ORANGE, arrayOf(point(0,0), point(0,-1), point(0,-2), point(1,-2)))
    val L1 = Tetrino(Color.ORANGE, arrayOf(point(0,0), point(0,-1), point(1,0), point(2,0)))
    val L2 = Tetrino(Color.ORANGE, arrayOf(point(0,0), point(1,0), point(1,-1), point(1,-2)))
    val L3 = Tetrino(Color.ORANGE, arrayOf(point(0,-1), point(1,-1), point(2,-1), point(2,0)))

    val J0 = Tetrino(Color.BLUE, arrayOf(point(0, -2), point(1, -2), point(1, -1), point(1, 0)))
    val J1 = Tetrino(Color.BLUE, arrayOf(point(0, 0), point(0, -1), point(1, -1), point(2, -1)))
    val J2 = Tetrino(Color.BLUE, arrayOf(point(1, 0), point(0, 0), point(0, -1), point(0, -2)))
    val J3 = Tetrino(Color.BLUE, arrayOf(point(0, 0), point(1, 0), point(2, 0), point(2, -1)))

    val T0 = Tetrino(Color.CYAN, arrayOf(point(0, 0), point(1, 0), point(1, -1), point(2, 0)))
    val T1 = Tetrino(Color.CYAN, arrayOf(point(1, -1), point(2, 0), point(2, -1), point(2, -2)))
    val T2 = Tetrino(Color.CYAN, arrayOf(point(1, -1), point(0, -2), point(1, -2), point(2, -2)))
    val T3 = Tetrino(Color.CYAN, arrayOf(point(0, 0), point(0, -1), point(1, -1), point(0, -2)))

    val O0 = Tetrino(Color.YELLOW, arrayOf(point(0,0), point(0, -1), point(1, 0), point(1, -1)))

    val I0 = Tetrino(Color.RED, arrayOf(point(0, 0), point(0, -1), point(0, -2), point(0, -3)))
    val I1 = Tetrino(Color.RED, arrayOf(point(0, -1), point(1, -1), point(2, -1), point(3, -1)))

    val Z0 = Tetrino(Color.GREEN, arrayOf(point(0, 0), point(1, 0), point(1, -1), point(2, -1)))
    val Z1 = Tetrino(Color.GREEN, arrayOf(point(1, 0), point(1, -1), point(0, -1), point(0, -2)))

    val S0 = Tetrino(FUCHSIA, arrayOf(point(0, -1), point(1, -1), point(1, 0), point(2, 0)))
    val S1 = Tetrino(FUCHSIA, arrayOf(point(0, 0), point(0, -1), point(1, -1), point(1, -2)))

    val RIGHT_ROTATIONS = mapOf(
        L0 to L1,
        L1 to L2,
        L2 to L3,
        L3 to L0,

        O0 to O0,

        I0 to I1,
        I1 to I0,

        J0 to J1,
        J1 to J2,
        J2 to J3,
        J3 to J0,

        T0 to T1,
        T1 to T2,
        T2 to T3,
        T3 to T0,

        Z0 to Z1,
        Z1 to Z0,

        S0 to S1,
        S1 to S0
    )

    val RANDOM_LIST = listOf(
        L1,
        O0,
        I1,
        J1,
        T0,
        Z0,
        S0
    )
}