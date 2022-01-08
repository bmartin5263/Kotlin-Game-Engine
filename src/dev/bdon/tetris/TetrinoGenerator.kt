package dev.bdon.tetris

import com.bdon.tetris.Tetrino

class TetrinoGenerator {

    private var after = Tetrinos.random()
    private var next = Tetrinos.random()

    fun generate(): Tetrino = Tetrinos.random()

    fun next(): Tetrino {
        val n = next
        next = after
        after = generate()
        return n
    }

    fun peekNext() = next
    fun peekAfter() = after

}