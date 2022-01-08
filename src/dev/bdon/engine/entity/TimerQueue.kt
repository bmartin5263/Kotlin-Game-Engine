package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.events.Timer
import java.util.*

class TimerQueue(private val parent: Entity){
    private val timers = PriorityQueue<Timer>()

    fun add(timer: Timer) {
        timers.add(timer)
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
    }

    fun isEmpty() = timers.isEmpty()
    fun isNotEmpty() = timers.isNotEmpty()
    fun peek() = timers.peek()
    fun poll() = timers.poll()
}