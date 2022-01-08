package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity

abstract class KeyListener(
    val key: Int,
    val action: Entity.() -> Unit
) {
    fun update(entity: Entity) {
        if (test()) {
            entity.action()
        }
    }

    abstract fun test(): Boolean
}
