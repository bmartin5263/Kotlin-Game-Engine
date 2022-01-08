package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity

class MomentaryKey(key: Int, action: Entity.() -> Unit) : KeyListener(key, action) {

    private var pressTime: Long = -1

    override fun test(): Boolean {
        return if (Clock.time == pressTime + 1) {
            ++pressTime
            false
        } else {
            pressTime = Clock.time
            true
        }
    }
}