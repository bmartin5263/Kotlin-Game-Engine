package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.events.IntervalTimer
import dev.bdon.engine.events.KeyListener
import dev.bdon.engine.events.TimeoutTimer
import dev.bdon.engine.events.Timer
import dev.bdon.engine.scene.Scene
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class TimerQueue {

    internal val queue: PriorityQueue<TimeoutTimer> = PriorityQueue()
    internal val intervals: MutableList<IntervalTimer> = ArrayList()

    private val entityToTimers: MutableMap<Entity, MutableSet<Timer>> = HashMap()

    private val addRequests: MutableSet<Timer> = HashSet()
    private val removeRequests: MutableSet<Timer> = HashSet()

    fun add(timer: Timer) {
        addRequests += timer
    }

    fun remove(timer: Timer) {
        removeRequests += timer
    }

    fun removeTimersFor(entity: Entity) {
        val timers = entityToTimers.getOrDefault(entity, Collections.emptySet())
        timers.forEach { it.removeFrom(this) }
    }

    fun poll(): Timer {
        val timer = queue.poll()
        entityToTimers[timer.action.target]!!.remove(timer)
        return timer
    }

    fun startNewTimers() {
        addRequests.forEach { timer ->
            println("Starting $timer")
            timer.insertInto(this)
            entityToTimers.getOrPut(timer.action.target) { HashSet() }.add(timer)
        }
        addRequests.clear()
    }

    fun removeCancelledTimers() {
        removeRequests.forEach { timer ->
            println("Removing $timer")
            timer.removeFrom(this)
            entityToTimers[timer.action.target]!!.remove(timer)
        }
        removeRequests.clear()
    }

    fun isEmpty() = queue.isEmpty()
    fun isNotEmpty() = queue.isNotEmpty()
    fun peek() = queue.peek()
}