package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.scene.Scene

object Timers {

    fun cancel(entity: Entity, timer: Timer) {
        val timerQueue = entity.scene!!.timerQueue
        timerQueue.remove(timer)
    }

    fun <T : Entity> setTimeout(entity: T, frames: Int, fn: T.(Timer) -> Unit): Timer {
        val timer = Timer(entity, Clock.time + frames, fn as Entity.(Timer) -> Unit)
        val timerQueue = entity.scene!!.timerQueue
        timerQueue.add(timer)
        return timer
    }

    internal fun process(scene: Scene) {
        val currentTime = Clock.time
        val timerQueue = scene.timerQueue
        while (timerQueue.isNotEmpty()) {
            val next = timerQueue.peek()
            if (currentTime >= next.executeAt) {
                timerQueue.poll()
                next.execute()
            }
            else {
                break
            }
        }
    }
}