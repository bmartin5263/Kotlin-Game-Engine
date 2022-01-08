package dev.bdon.engine

import dev.bdon.engine.events.Keyboard
import java.awt.event.KeyEvent

object Clock {
    var time: Long = 0

    private var running: Boolean = false
    private var paused: Boolean = false
    private var onTick: () -> Unit = {}

    fun start(onTick: () -> Unit) {
        this.time = 0
        this.running = true
        this.paused = false
        this.onTick = onTick
        while (running) {
            if (Keyboard.isKeyPressed(KeyEvent.VK_O)) {
                unpause()
            }
            else if (Keyboard.isKeyPressed(KeyEvent.VK_P)) {
                pause()
            }
            if (!paused) {
                tick()
            }
        }
    }

    private fun tick() {
        val start = System.currentTimeMillis()
        onTick()
        val stop = System.currentTimeMillis()
        val difference = stop - start
        println(difference)
        Thread.sleep(17 - difference)
        ++time
    }

    fun unpause() {
        paused = false
    }

    fun pause() {
        paused = true
    }

    fun togglePause() {
        println("toggle")
        paused = !paused;
    }
}