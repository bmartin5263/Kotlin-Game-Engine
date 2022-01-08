package dev.bdon.engine.entity

import dev.bdon.engine.events.KeyListener
import kotlin.collections.HashMap

class KeyMap(private val parent: Entity) {

    private val codeToListeners: MutableMap<Int, KeyListener> = HashMap()

    fun register(vararg keyListeners: KeyListener) {
        keyListeners.forEach {
            codeToListeners.getOrPut(it.key) { it }
        }
    }

    fun register(keyListeners: List<KeyListener>) {
        keyListeners.forEach {
            codeToListeners.getOrPut(it.key) { it }
        }
    }

    fun deregister(keyListener: KeyListener) {
        codeToListeners.remove(keyListener.key)
    }

    fun updateListeners(key: Int) {
        codeToListeners[key]?.update(parent)
    }

//    operator fun get(key: Int): MutableList<KeyListener> {
//        return codeToListeners.getOrDefault(key, Collections.emptyList())
//    }
//
//    operator fun get(entity: Entity): MutableList<KeyListener> {
//        return entityToListeners.getOrDefault(entity, Collections.emptyList())
//    }
}