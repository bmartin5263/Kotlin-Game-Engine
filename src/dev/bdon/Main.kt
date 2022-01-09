package dev.bdon

import dev.bdon.tetris.TetrisGame
import dev.bdon.engine.Engine.launch
import dev.bdon.engine.Point
import kotlin.jvm.JvmStatic
import dev.bdon.engine.entity.script
import dev.bdon.engine.scene.AnonymousScene
import dev.bdon.tetris.Constants
import dev.bdon.tetris.TetrisScene
import java.awt.event.KeyEvent
import kotlin.random.Random

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val scene = TetrisScene()
        launch(scene)
    }
}