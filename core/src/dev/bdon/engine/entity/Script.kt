@file:Suppress("unused")

package dev.bdon.engine.entity

import dev.bdon.engine.events.Keyboard

@DslMarker
annotation class ScriptMarker

typealias X<T> = T.(Int) -> Unit

@ScriptMarker
class Script<T>(private val entity: T) where T : Entity {

    var timers = mutableListOf<TimerConfig<T>>()

//    fun onKeyPress(key: Int, function: T.() -> Unit) {
//        Keyboard.onKeyPress(entity, key, function)
//    }

//    fun onKeyPress(keys: Set<Int>, function: T.(Int) -> Unit) {
//        _onKeyPressAction = { keyCode ->
//            if (keyCode in keys) {
//                function(keyCode)
//            }
//        }
//    }

//    fun whileKeyPressed(key: Int, delay: Long = 0, function: T.() -> Unit) {
//        Keyboard.whileKeyPressed(entity, key, delay, function)
//    }

//    fun whileKeyPress(keys: Set<Int>, delay: Int = 0, function: T.(Int) -> Unit) {
//
//    }

    inline fun timer(function: TimerConfig<T>.() -> Unit) {
        val config = TimerConfig<T>()
        config.function()
        timers.add(config)
    }

}

class TimerConfig<T> where T : Entity {

    var secondsSupplier = { 0L }

    fun seconds(amount: Long) {
        secondsSupplier = { amount }
    }

    inline fun action(function: T.() -> Unit) {

    }

    inline fun onCancel(function: T.() -> Unit) {

    }
}

inline fun <T> T.script(builder: Script<T>.(T) -> Unit): Script<T> where T : Entity {
    val script = Script(this)
    script.builder(this)
    return script
}