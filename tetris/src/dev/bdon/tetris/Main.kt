package dev.bdon.tetris

import dev.bdon.engine.Engine
import kotlin.jvm.JvmStatic

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Engine.launch(MainMenuScene())
    }
}