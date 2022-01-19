package dev.bdon.engine.entity

import dev.bdon.engine.events.IntervalTimerComponent
import dev.bdon.engine.events.TimeoutTimerComponent
import dev.bdon.engine.events.TimerComponent
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class TimerQueue {

    internal val queue: PriorityQueue<TimeoutTimerComponent> = PriorityQueue()
    internal val intervals: MutableList<IntervalTimerComponent> = ArrayList()

    private val entityToTimers: MutableMap<Entity, MutableSet<TimerComponent>> = HashMap()
    private val addRequests: MutableSet<TimerComponent> = HashSet()
    private val removeRequests: MutableSet<TimerComponent> = HashSet()

    fun add(timer: TimerComponent) {
        addRequests += timer
    }

    fun remove(timer: TimerComponent) {
        removeRequests += timer
    }

    fun removeTimersFor(entity: Entity) {
        val timers = entityToTimers.getOrDefault(entity, Collections.emptySet())
        timers.forEach { it.removeFrom(this) }
    }

    fun poll(): TimerComponent {
        val timer = queue.poll()
        entityToTimers[timer.action.target]!!.remove(timer)
        return timer
    }

    fun startNewTimers() {
        addRequests.forEach { timer ->
//            println("Starting $timer")
            timer.insertInto(this)
            entityToTimers.getOrPut(timer.action.target) { HashSet() }.add(timer)
        }
        addRequests.clear()
    }

    fun removeCancelledTimers() {
        removeRequests.forEach { timer ->
//            println("Removing $timer")
            timer.removeFrom(this)
            entityToTimers[timer.action.target]!!.remove(timer)
        }
        removeRequests.clear()
    }

    fun fastForwardTimeouts(amount: Long) {
        queue.forEach { it.executeAt += amount }
    }

    fun isEmpty() = queue.isEmpty()
    fun isNotEmpty() = queue.isNotEmpty()
    fun peek() = queue.peek()
}