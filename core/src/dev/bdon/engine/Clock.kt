package dev.bdon.engine

import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.events.MomentaryKey
import dev.bdon.engine.graphics.Java2d
import dev.bdon.engine.util.StopWatch
import java.awt.event.KeyEvent
import kotlin.math.min

object Clock {
    var time: Long = 0

    internal var running: Boolean = false
    private var paused: Boolean = false
    private var onTick: () -> Unit = {}

    fun start(onTick: () -> Unit) {
        this.time = 0
        this.paused = false
        this.onTick = onTick
        this.running = true
        while (!Java2d.closed && running) {
            if (!paused) {
                tick()
            }
        }
    }

    private fun tick() {
        val ms = StopWatch().time("Tick") { onTick() }
        Thread.sleep(17 - min(ms, 17))
        ++time
    }

    fun unpause() {
        paused = false
    }

    fun pause() {
        paused = true
    }

    fun togglePause() {
        paused = !paused;
    }
}