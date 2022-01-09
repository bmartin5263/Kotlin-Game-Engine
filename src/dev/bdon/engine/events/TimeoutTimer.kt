package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.TimerQueue

class TimeoutTimer(
    handle: TimerHandle,
    val executeAt: Long,
    action: Action1<Entity, TimeoutTimer>
): Timer(handle, action), Comparable<TimeoutTimer> {

    override fun compareTo(other: TimeoutTimer) = this.executeAt.compareTo(other.executeAt)
    override fun insertInto(timerQueue: TimerQueue) {
        timerQueue.queue.add(this)
    }

    override fun removeFrom(timerQueue: TimerQueue) {
        timerQueue.queue.remove(this)
    }

    override fun toString(): String {
        return "Timer(executeAt=$executeAt, entity=${action.target})"
    }

}