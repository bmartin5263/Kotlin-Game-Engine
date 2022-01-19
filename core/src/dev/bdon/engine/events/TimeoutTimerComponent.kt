package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.TimerQueue

class TimeoutTimerComponent(
    handle: TimerHandle,
    var executeAt: Long,
    action: Action1<Entity, TimeoutTimerComponent>
): TimerComponent(handle, action), Comparable<TimeoutTimerComponent> {

    override fun compareTo(other: TimeoutTimerComponent) = this.executeAt.compareTo(other.executeAt)
    override fun insertInto(timerQueue: TimerQueue) {
        timerQueue.queue.add(this)
    }

    override fun removeFrom(timerQueue: TimerQueue) {
        timerQueue.queue.remove(this)
    }

    override fun toString(): String {
        return "TimeoutTimer(executeAt=$executeAt, entity=${action.target})"
    }

}