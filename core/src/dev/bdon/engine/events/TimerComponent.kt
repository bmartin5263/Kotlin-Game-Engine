package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.TimerQueue

abstract class TimerComponent(
    val handle: TimerHandle,
    val action: Action1<Entity, out TimerComponent>
) {

    fun execute() {
        val casted = action as Action1<Entity, TimerComponent>
        casted(this)
    }

    fun cancel() {
        action.target.scene?.cancelTimer(this)
    }

    abstract fun insertInto(timerQueue: TimerQueue)
    abstract fun removeFrom(timerQueue: TimerQueue)

}