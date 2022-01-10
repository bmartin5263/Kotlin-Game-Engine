package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity

class MomentaryKey(
    handle: KeyHandle,
    key: Int,
    action: Action<Entity>
) : KeyListener(handle, key, action) {

    internal var pressTime: Long = Clock.time - 1

    override fun test(): Boolean {
//        val x = Clock.time == pressTime + 1
//        println("Clock.time == pressTime + 1 == $x")
        return if (Clock.time == pressTime + 1) {
            ++pressTime
            false
        } else {
            println("Old Press Time: $pressTime")
            println("Clock.time: ${Clock.time}")
            pressTime = Clock.time
            true
        }
    }
}