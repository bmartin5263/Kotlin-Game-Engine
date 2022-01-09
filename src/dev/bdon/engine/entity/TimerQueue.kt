package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.events.Timer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class TimerQueue {
    private val timers: PriorityQueue<Timer> = PriorityQueue<Timer>()
    private val entityToTimers: MutableMap<Entity, MutableList<Timer>> = HashMap()

    fun add(timer: Timer) {
        timers.add(timer)
        entityToTimers.getOrPut(timer.entity) { ArrayList() }.add(timer)
    }

//    fun process() {
//        val currentTime = Clock.time
//        while (timers.isNotEmpty()) {
//            val next = timers.peek()
//            if (currentTime >= next.executeAt) {
//                timers.poll()
//                next.execute()
//            }
//            else {
//                break
//            }
//        }
//    }

    fun remove(timer: Timer) {
        timers.remove(timer)
        entityToTimers[timer.entity]!!.remove(timer)
    }

    fun poll(): Timer {
        val timer = timers.poll()
        entityToTimers[timer.entity]!!.remove(timer)
        return timer
    }

    fun isEmpty() = timers.isEmpty()
    fun isNotEmpty() = timers.isNotEmpty()
    fun peek() = timers.peek()
}