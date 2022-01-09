package dev.bdon.engine.events

class TimerHandle {

    internal var cancelled: Boolean = false
    var timer: Timer? = null

    internal fun link(timer: Timer) {
        this.timer = timer
    }

    internal fun unlink() {
        this.timer = null
    }

    fun cancel() {
        if (!cancelled) {
            cancelled = true
            if (timer != null) {
                timer!!.cancel()
            }
        }
    }
}