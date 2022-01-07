@file:Suppress("unused")

package dev.bdon.engine

import java.util.*

object Keyboard {

    private val pressed = mutableSetOf<Int>()
//    private val observers = EnumMap<Int, List<KeyObserver>>(Int::class.java)

    fun isKeyPressedImpl(code: Int): Boolean {
        return code in pressed
    }

    fun releaseKeyImpl(code: Int) {
        pressed.remove(code)
    }

    fun pressKeyImpl(code: Int) {
        if (code !in pressed) {
            pressed.add(code)
        }
    }

}