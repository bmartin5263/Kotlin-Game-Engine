package dev.bdon.tetris

import dev.bdon.engine.scene.Scene
import kotlin.random.Random

abstract class GameplayScene : Scene() {
    protected val gameController = GameController()
    protected val seed = Random.nextInt()
}