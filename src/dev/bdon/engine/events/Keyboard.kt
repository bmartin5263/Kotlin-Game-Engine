@file:Suppress("unused")

package dev.bdon.engine.events

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.scene.Scene
import java.util.concurrent.ConcurrentHashMap

object Keyboard {

    private val pressed: MutableSet<Int> = ConcurrentHashMap.newKeySet()

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
            scene.keyMap.updateListeners(code)
        }
    }

//    fun onKeyPress(key: Int, action: (Int) -> Unit) {
//        onKeyPress(Engine.currentScene, key, action)
//    }

    fun <T : Entity> onKeyPress(entity: T, scene: Scene, key: Int, action: T.() -> Unit): MomentaryKey {
        require(entity.scene!! == scene)
        val keyMap = entity.scene!!.keyMap
        val listener = MomentaryKey(entity, key, action as Entity.() -> Unit)
        keyMap.register(listener)
        return listener
    }

    fun <T : Entity> whileKeyPressed(entity: T, scene: Scene, key: Int, delay: Long = 0L, action: T.() -> Unit): PressedKey {
        require(entity.scene!! == scene)
        val keyMap = entity.scene!!.keyMap
        val listener = PressedKey(entity, key, delay, action as Entity.() -> Unit)
        keyMap.register(listener)
        return listener
    }
}