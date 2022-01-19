package dev.bdon.engine.sprite

import dev.bdon.engine.Point
import dev.bdon.engine.graphics.Graphics

abstract class AbstractSprite : Sprite {

    var x: Float = 0f
    var y: Float = 0f

    @Suppress("MemberVisibilityCanBePrivate")
    var hidden: Boolean = false

    val position: Point
        get() = Point(x, y)

    override fun move(x: Float, y: Float) {
        this.x += x
        this.y += y
    }

    override fun draw(g: Graphics) {
        if (!hidden) {
            render(g)
        }
    }

    protected abstract fun render(g: Graphics)
}