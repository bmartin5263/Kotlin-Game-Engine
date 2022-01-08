@file:Suppress("unused")

package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.KeyMap
import dev.bdon.engine.scene.Scene
import java.util.concurrent.ConcurrentHashMap

object Keyboard {

    private val pressed: MutableSet<Int> = ConcurrentHashMap.newKeySet()
//    private val observers = EnumMap<Int, List<KeyObserver>>(Int::class.java)

    fun isKeyPressed(code: Int): Boolean {
        return code in pressed
    }

    fun releaseKey(code: Int) {
        pressed.remove(code)
    }

    fun pressKey(code: Int) {
        if (code !in pressed) {
            pressed.add(code)
        }
    }

    fun process(scene: Scene) {
        for (code in pressed) {
            for (entity in scene.entities) {
                entity.keyMap?.updateListeners(code)
            }
        }
    }

//    fun onKeyPress(key: Int, action: (Int) -> Unit) {
//        onKeyPress(Engine.currentScene, key, action)
//    }

    fun <T : Entity> onKeyPress(entity: T, key: Int, action: T.() -> Unit) {
        val keyMap = if (entity.keyMap == null) {
            entity.keyMap = KeyMap(entity)
            entity.keyMap!!
        }
        else {
            entity.keyMap!!
        }
        keyMap.register(MomentaryKey(key, action as Entity.() -> Unit))
    }

    fun <T : Entity> whileKeyPressed(entity: T, key: Int, delay: Long = 0L, action: T.() -> Unit) {
        val keyMap = if (entity.keyMap == null) {
            entity.keyMap = KeyMap(entity)
            entity.keyMap!!
        }
        else {
            entity.keyMap!!
        }
        keyMap.register(PressedKey(key, delay, action as Entity.() -> Unit))
    }
}