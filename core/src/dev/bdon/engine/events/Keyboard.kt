@file:Suppress("unused")

package dev.bdon.engine.events

import dev.bdon.engine.scene.Scene

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

    fun update(scene: Scene) {
        val keyMap = scene.keyMap
        keyMap.doAddRequests()
        for (code in pressed) {
            keyMap.updateListeners(code)
        }
        keyMap.doRemoveRequests()
    }
}