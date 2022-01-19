package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.TimerQueue

class IntervalTimerComponent(
    handle: TimerHandle,
    internal var interval: Long,
    action: Action1<Entity, IntervalTimerComponent>
): TimerComponent(handle, action) {

    var remaining: Long = interval
        internal set

    var iteration: Int = -1
        internal set

    override fun insertInto(timerQueue: TimerQueue) {
        timerQueue.intervals.add(this)
    }

    override fun removeFrom(timerQueue: TimerQueue) {
        timerQueue.intervals.remove(this)
    }

    fun changeInterval(value: Long) {
        interval = value
    }

    fun reset() {
        remaining = interval
    }

    override fun toString(): String {
        return "IntervalTimer(interval=$interval, remaining=$remaining, iteration=$iteration, entity=${action.target})"
    }

}