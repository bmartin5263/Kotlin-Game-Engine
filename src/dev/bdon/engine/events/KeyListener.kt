package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity

abstract class KeyListener(
    val entity: Entity,
    val key: Int,
    val action: Entity.() -> Unit
) {
    fun update() {
        if (test()) {
            entity.action()
        }
    }

    abstract fun test(): Boolean
}
