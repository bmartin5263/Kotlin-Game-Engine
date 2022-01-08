package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity

class PressedKey(
    key: Int,
    private val delay: Long = 0,
    action: Entity.() -> Unit
) : KeyListener(key, action) {

    private var lastExecute: Long = -1
    private var lastTest: Long = -1
    private var pressed: Boolean = false

    override fun test(): Boolean {
        val now = Clock.time
        val delta = now - lastTest
        lastTest = now
        return if (delta == 1L) {
            if (Clock.time > lastExecute + delay) {
                lastExecute = now
                true
            } else {
                false
            }
        }
        else {
            lastExecute = now
            true
        }
    }
}