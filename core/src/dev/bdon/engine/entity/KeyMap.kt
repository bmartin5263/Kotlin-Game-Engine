package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.events.KeyListener
import dev.bdon.engine.events.MomentaryKey
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

    fun fastForwardMomentaryKeys() {
        codeToListeners.values.forEach { list ->
            list.forEach { listener ->
                if (listener is MomentaryKey) {
                    listener.pressTime = Clock.time
                }
            }
        }
    }

    fun doAddRequests() {
        addRequests.forEach { listener ->
            codeToListeners.getOrPut(listener.key) { ArrayList() }.add(listener)
            entityToListeners.getOrPut(listener.action.target) { ArrayList() }.add(listener)
        }
        addRequests.clear()
    }

    fun doRemoveRequests() {
        removeRequests.forEach { listener ->
            val entity = listener.action.target
            codeToListeners.remove(listener.key)
            assert(entityToListeners[entity]!!.remove(listener))
            listener.handle.keyListener = null
        }
        removeRequests.clear()
    }

}