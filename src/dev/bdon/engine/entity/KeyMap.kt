package dev.bdon.engine.entity

import dev.bdon.engine.events.KeyListener
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KeyMap {

    private val codeToListeners: MutableMap<Int, MutableList<KeyListener>> = HashMap()
    private val entityToListeners: MutableMap<Entity, MutableList<KeyListener>> = HashMap()

    fun register(vararg keyListeners: KeyListener) {
        keyListeners.forEach {
            codeToListeners.getOrPut(it.key) { ArrayList() }.add(it)
            entityToListeners
        }
    }

    fun register(keyListeners: List<KeyListener>) {
        keyListeners.forEach {
            codeToListeners.getOrPut(it.key) { ArrayList() }.addAll(keyListeners)
        }
    }

    fun deregister(keyListener: KeyListener) {
        codeToListeners.remove(keyListener.key)
    }

    fun deregister(entity: Entity) {
        val listeners = entityToListeners[entity]
        if (listeners != null) {
            listeners.forEach { codeToListeners[it.key]!!.remove(it) }
            listeners.clear()
        }
    }

    fun updateListeners(key: Int) {
        codeToListeners.getOrDefault(key, Collections.emptyList()).forEach { it.update() }
    }

//    operator fun get(key: Int): MutableList<KeyListener> {
//        return codeToListeners.getOrDefault(key, Collections.emptyList())
//    }
//
//    operator fun get(entity: Entity): MutableList<KeyListener> {
//        return entityToListeners.getOrDefault(entity, Collections.emptyList())
//    }
}