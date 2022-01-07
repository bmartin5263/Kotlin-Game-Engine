package dev.bdon.engine.entity

import dev.bdon.engine.graphics.Sprite
import java.awt.Graphics2D

class SpriteEntity<S : Sprite>(var sprite: S) : Entity() {

    private var updateFn: (S) -> Unit = {}

    override fun draw(g: Graphics2D) {
        sprite.render(g)
    }

    override fun update() {
        updateFn(sprite)
    }

    fun onUpdate(fn: (S) -> Unit): SpriteEntity<S> {
        this.updateFn = fn
        return this
    }
}

fun <S : Sprite> S.toEntity(): SpriteEntity<S> = SpriteEntity(this)