package dev.bdon.tetris

import com.bdon.tetris.Tetrino

class TetrinoGenerator {

    fun next(): Tetrino {
        return Tetrino.random()
    }

}