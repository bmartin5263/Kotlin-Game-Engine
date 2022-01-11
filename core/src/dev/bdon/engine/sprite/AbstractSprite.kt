package dev.bdon.engine.sprite

import dev.bdon.engine.Point
import dev.bdon.engine.graphics.Graphics
import java.awt.Graphics2D

abstract class AbstractSprite : Sprite {

    override var x: Int = 0
    override var y: Int = 0

    @Suppress("MemberVisibilityCanBePrivate")
    var hidden: Boolean = false

    val position: Point
        get() = Point(x, y)

    override fun move(x: Int, y: Int) {
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