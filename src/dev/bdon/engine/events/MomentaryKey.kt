package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity

class MomentaryKey(
    handle: KeyHandle,
    key: Int,
    action: Action<Entity>
) : KeyListener(handle, key, action) {

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