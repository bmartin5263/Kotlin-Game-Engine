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
        pressed.add(code)
    }

    fun updateKeyListeners(scene: Scene) {
        scene.keyMap.startNewListeners()
        for (code in pressed) {
            scene.keyMap.updateListeners(code)
        }
        scene.keyMap.removeExpiredListeners()
    }

//    fun deregister(keyListener: KeyListener) {
//        val entity = keyListener.entity
//        val keyMap = entity.scene!!.keyMap
//        keyMap.deregister(entity)
//    }

}