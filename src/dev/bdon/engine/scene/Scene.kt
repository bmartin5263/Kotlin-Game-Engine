package dev.bdon.engine.scene

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.KeyMap
import dev.bdon.engine.entity.TimerQueue

abstract class Scene {

    internal val timerQueue: TimerQueue = TimerQueue()
    internal val keyMap: KeyMap = KeyMap()
    internal val entities: MutableList<Entity> = ArrayList()

    fun register(vararg entity: Entity) {
        entity.forEach {
            require(it.scene == null) { "Entities cannot be registered to two scenes" }
            it.scene = this
        }
        entities += entity
    }

    fun deregister(vararg entity: Entity) {
        entity.forEach {
            entities.remove(it)
            it.scene = null
        }
    }

    fun enter() {
        onEnter()
    }

    fun exit() {
        onExit()
    }

    open fun initialize() {}
    open fun onEnter() {}
    open fun onExit() {}
    open fun destroy() {}

}