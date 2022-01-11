package dev.bdon.engine.entity

import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.sprite.AbstractSprite

class SpriteEntity<S : AbstractSprite>(var sprite: S) : Entity() {

    private var updateFn: (S) -> Unit = {}

    override fun draw(g: Graphics) {
        sprite.draw(g)
    }

    override fun update() {
        updateFn(sprite)
    }

    fun onUpdate(fn: (S) -> Unit): SpriteEntity<S> {
        this.updateFn = fn
        return this
    }
}

fun <S : AbstractSprite> S.toEntity(): SpriteEntity<S> = SpriteEntity(this)