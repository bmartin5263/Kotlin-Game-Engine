package dev.bdon.engine.events

open class TimerHandle {

    internal var isCancelled: Boolean = false
    internal var timer: TimerComponent? = null

    internal fun link(timer: TimerComponent) {
        this.timer = timer
    }

    internal fun unlink() {
        this.timer = null
    }

    fun cancel() {
        if (!isCancelled) {
            isCancelled = true
            if (timer != null) {
                timer!!.cancel()
            }
        }
    }
}