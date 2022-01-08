package dev.bdon.engine.scene

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.TimerQueue

abstract class Scene {

//    internal val timerQueue: TimerQueue = TimerQueue()
//    internal val keyMap: KeyMap = KeyMap()
    internal val entities: MutableList<Entity> = ArrayList()

    fun register(entity: Entity) {
        entities += entity
    }

    fun deregister(entity: Entity) {
        entities.remove(entity)
    }

    fun enter() {
        onEnter()
    }

    fun exit() {
        onExit()
    }

    open fun onInitialize() {}
    open fun onEnter() {}
    open fun onExit() {}
    open fun onDestruction() {}

}