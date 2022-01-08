package dev.bdon.engine.entity

import dev.bdon.engine.graphics.Graphics

abstract class Entity {

    internal var keyMap: KeyMap? = null
    internal var timerQueue: TimerQueue? = null

    open fun draw(g: Graphics) {}
    open fun update() {}
    open fun onSpawn() {}
    open fun onDestruction() {}
}