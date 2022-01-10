package dev.bdon.engine.entity

import dev.bdon.engine.events.KeyListener
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class KeyMap {

    private val codeToListeners: MutableMap<Int, MutableList<KeyListener>> = HashMap()
    private val entityToListeners: MutableMap<Entity, MutableList<KeyListener>> = HashMap()

    private val addRequests: MutableSet<KeyListener> = HashSet()
    private val removeRequests: MutableSet<KeyListener> = HashSet()

    fun addListener(listener: KeyListener) {
        addRequests += listener
    }

    fun removeListener(listener: KeyListener) {
        removeRequests += listener
    }

    fun removeListenersFor(entity: Entity) {
        val listeners = entityToListeners[entity]
        if (listeners != null) {
            listeners.forEach { codeToListeners[it.key]!!.remove(it) }
            listeners.clear()
        }
    }

    fun updateListeners(key: Int) {
        codeToListeners.getOrDefault(key, Collections.emptyList()).forEach { it.update() }
    }

    fun startNewListeners() {
        addRequests.forEach { listener ->
            println("Registering $listener")
            codeToListeners.getOrPut(listener.key) { ArrayList() }.add(listener)
            entityToListeners.getOrPut(listener.action.target) { ArrayList() }.add(listener)
        }
        addRequests.clear()
    }

    fun removeExpiredListeners() {
        removeRequests.forEach { listener ->
            println("Deregistering $listener")
            val entity = listener.action.target
            codeToListeners.remove(listener.key)
            assert(entityToListeners[entity]!!.remove(listener))
            listener.handle.keyListener = null
        }
        removeRequests.clear()
    }

//    operator fun get(key: Int): MutableList<KeyListener> {
//        return codeToListeners.getOrDefault(key, Collections.emptyList())
//    }
//
//    operator fun get(entity: Entity): MutableList<KeyListener> {
//        return entityToListeners.getOrDefault(entity, Collections.emptyList())
//    }
}