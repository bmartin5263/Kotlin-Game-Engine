package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.Engine
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.TimerQueue
import dev.bdon.engine.scene.Scene

object Timers {

    fun cancel(entity: Entity, timer: Timer) {
        entity.timerQueue?.remove(timer)
    }

    fun <T : Entity> setTimeout(entity: T, frames: Int, fn: T.(Timer) -> Unit): Timer {
        val timer = Timer(Clock.time + frames, fn as Entity.(Timer) -> Unit)
        val timerQueue = if (entity.timerQueue == null) {
            entity.timerQueue = TimerQueue(entity)
            entity.timerQueue!!
        }
        else {
            entity.timerQueue!!
        }
        timerQueue.add(timer)
        return timer
    }

    internal fun process(scene: Scene) {
        val currentTime = Clock.time
        scene.entities.forEach {
            val timerQueue = it.timerQueue
            if (timerQueue != null) {
                while (timerQueue.isNotEmpty()) {
                    val next = timerQueue.peek()
                    if (currentTime >= next.executeAt) {
                        timerQueue.poll()
                        next.execute(it)
                    }
                    else {
                        break
                    }
                }
            }
        }
    }
}