package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity

abstract class KeyListener(
    val handle: KeyHandle,
    val key: Int,
    val action: Action<Entity>
) {

    fun update() {
        if (test()) {
            action()
        }
    }

    fun cancel() {
        action.target.scene?.deregisterKeyListener(this)
    }

    abstract fun test(): Boolean
    override fun toString(): String {
        return "KeyListener(key=$key, entity=${action.target})"
    }


}
