package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.events.Timer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class TimerQueue {
    private val queue: PriorityQueue<Timer> = PriorityQueue<Timer>()
    private val entityToTimers: MutableMap<Entity, MutableSet<Timer>> = HashMap()

    fun add(timer: Timer) {
        queue.add(timer)
        entityToTimers.getOrPut(timer.entity) { HashSet() }.add(timer)
    }

//    fun process() {
//        val currentTime = Clock.time
//        while (queue.isNotEmpty()) {
//            val next = queue.peek()
//            if (currentTime >= next.executeAt) {
//                queue.poll()
//                next.execute()
//            }
//            else {
//                break
//            }
//        }
//    }

    fun remove(timer: Timer) {
        queue.remove(timer)
        entityToTimers[timer.entity]!!.remove(timer)
    }

    fun removeTimersFor(entity: Entity) {
        val timers = entityToTimers.getOrDefault(entity, Collections.emptySet())
        queue -= timers
    }

    fun poll(): Timer {
        val timer = queue.poll()
        entityToTimers[timer.entity]!!.remove(timer)
        return timer
    }

    fun isEmpty() = queue.isEmpty()
    fun isNotEmpty() = queue.isNotEmpty()
    fun peek() = queue.peek()
}