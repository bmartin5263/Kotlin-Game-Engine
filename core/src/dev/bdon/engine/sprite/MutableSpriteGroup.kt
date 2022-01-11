package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics

abstract class MutableSpriteGroup : SpriteGroup() {

    abstract override val sprites: MutableCollection<Sprite>

    fun add(s: Sprite) {
        sprites += s
    }

    fun add(vararg s: Sprite) {
        sprites.addAll(s)
    }

    fun remove(s: Sprite) {
        sprites -= s
    }

    override var x: Int
        get() = sprites.firstOrNull()?.x ?: 0
        set(value) {
            val delta = value.compareTo(y)
            move(delta, 0)
        }

    override var y: Int
        get() = sprites.firstOrNull()?.y ?: 0
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