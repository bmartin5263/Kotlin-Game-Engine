package dev.bdon.engine.entity

import dev.bdon.engine.scene.Scene

interface RegisterCommand {
    fun execute(scene: Scene)
}