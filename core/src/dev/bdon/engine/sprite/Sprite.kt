package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics

interface Sprite {
    var x: Int
    var y: Int

    fun draw(g: Graphics)
    fun move(x: Int, y: Int)
}