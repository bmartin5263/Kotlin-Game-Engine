package dev.bdon.engine.events

import dev.bdon.engine.Clock
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.scene.Scene

object Timers {

    internal fun process(scene: Scene) {
        val currentTime = Clock.time
        val timerQueue = scene.timerQueue
        timerQueue.startNewTimers()
        while (timerQueue.isNotEmpty()) {
            val next = timerQueue.peek()
            if (currentTime >= next.executeAt) {
                timerQueue.poll()
                next.execute()
                println("Executing Timer")
            }
            else {
                break
            }
        }

        for (intervalTimer in timerQueue.intervals) {
            intervalTimer.remaining -= 1
            if (intervalTimer.remaining <= 0) {
                intervalTimer.iteration++
                intervalTimer.execute()
                intervalTimer.remaining = intervalTimer.interval
            }
        }

        timerQueue.removeCancelledTimers()
    }
}