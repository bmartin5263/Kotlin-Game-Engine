package dev.bdon.engine.events

class IntervalTimerHandle : TimerHandle() {
    fun reset() {
        (timer as? IntervalTimer)?.reset()
    }
}