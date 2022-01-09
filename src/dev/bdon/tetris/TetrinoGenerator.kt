package dev.bdon.tetris

import com.bdon.tetris.Tetrino
import kotlin.random.Random

class TetrinoGenerator(private val random: Random) {

    private var after = generate()
    private var next = generate()

    fun generate(): Tetrino = Tetrinos.RANDOM_LIST[random.nextInt(Tetrinos.RANDOM_LIST.size)]

    fun next(): Tetrino {
        val n = next
        next = after
        after = generate()
        return n
    }

    fun peekNext() = next
    fun peekAfter() = after

}