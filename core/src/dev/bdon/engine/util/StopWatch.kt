package dev.bdon.engine.util

class StopWatch {

    var start: Long = 0
    var stop: Long = 0

    inline fun time(name: String, operation: () -> Unit): Long {
        start = System.currentTimeMillis()
        operation()
        stop = System.currentTimeMillis()
        return start - stop
    }

}