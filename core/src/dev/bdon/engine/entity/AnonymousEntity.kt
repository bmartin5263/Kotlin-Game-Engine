package dev.bdon.engine.entity

import dev.bdon.engine.graphics.Graphics

class AnonymousEntity : Entity() {

    private var updateFn: () -> Unit = {}
    private var drawFn: (Graphics) -> Unit = {}

    override fun draw(g: Graphics) {
        drawFn(g)
    }

    override fun update() {
        updateFn()
    }
}