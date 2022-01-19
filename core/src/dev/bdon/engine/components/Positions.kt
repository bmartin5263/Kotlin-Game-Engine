package dev.bdon.engine.components

import dev.bdon.engine.ecs.ECS
import dev.bdon.engine.scene.Scene

object Positions {
    fun moveLeft(ecs: ECS) {
        ecs.positionRegistry.positions.forEach { floats, i ->
            floats[i + 1] += 1.0f
        }
    }
}