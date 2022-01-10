@file:Suppress("unused")

package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.scene.Scene
import java.util.concurrent.ConcurrentHashMap

object Keyboard {

    internal var keySource: KeySource = NullKeySource
    internal val keyHistory: MutableList<IntArray> = ArrayList()

    val pressed get() = keySource.pressed

    fun isKeyPressed(code: Int): Boolean {
        return code in keySource.pressed
    }

    fun recordKeys() {
        keyHistory += pressed.toIntArray()
    }
}