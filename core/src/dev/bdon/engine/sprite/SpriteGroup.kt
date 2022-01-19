package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics

abstract class SpriteGroup : Sprite {

    abstract val sprites: Iterable<Sprite>

    override var x: Int
        get() = sprites.minOf { it.x }
        set(value) {
            val delta = value.compareTo(y)
            move(delta, 0)
        }

    override var y: Int
        get() = sprites.minOf { it.y }
        set(value) {
            val delta = value.compareTo(y)
            move(0, delta)
        }

    override fun draw(g: Graphics) {
        sprites.forEach { it.draw(g) }
    }

    override fun move(x: Int, y: Int) {
        sprites.forEach { it.move(x, y) }
    }

}