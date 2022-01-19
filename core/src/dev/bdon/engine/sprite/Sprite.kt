package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics

interface Sprite {
    fun draw(g: Graphics)
    fun move(x: Float, y: Float)
}