package dev.bdon.engine.scene

import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.KeyMap
import dev.bdon.engine.entity.TimerQueue
import dev.bdon.engine.events.KeyListener
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.events.Timers

abstract class Scene {

    internal val timerQueue: TimerQueue = TimerQueue()
    internal val keyMap: KeyMap = KeyMap()
    internal val liveEntities: MutableSet<Entity> = HashSet()

    private val spawnRequests: MutableSet<Entity> = HashSet()
    private val destroyRequests: MutableSet<Entity> = HashSet()

    fun spawn(vararg entities: Entity) {
        entities.forEach {
            if (it !in liveEntities) spawnRequests += it
        }
    }

    fun destroy(entity: Entity) {
        if (entity in liveEntities) destroyRequests += entity
    }


    fun nextFrame() {
        spawnNewEntities()

        liveEntities.forEach { it.update() }
        Keyboard.updateKeyListeners(this)
        Timers.process(this)

        destroyExpiredEntities()
    }

    private fun spawnNewEntities() {
        liveEntities += spawnRequests
        spawnRequests.forEach { doSpawn(it) }
        spawnRequests.clear()
    }

    private fun doSpawn(entity: Entity) {
        entity.registerToScene(this)
        entity.initialize()
    }

    private fun destroyExpiredEntities() {
        spawnRequests.forEach { doDestroy(it) }
        liveEntities -= destroyRequests
        spawnRequests.clear()
    }

    private fun doDestroy(entity: Entity) {
        entity.terminate()

        // Automatically deregister from events
        keyMap.removeListenersFor(entity)
        timerQueue.removeTimersFor(entity)

        entity.deregisterFromScene()
    }

    fun registerKeyListener(keyListener: KeyListener) {
        keyMap.addListener(keyListener)
    }

    fun deregisterKeyListener(keyListener: KeyListener) {
        keyMap.removeListener(keyListener)
    }

    open fun initialize() {}
    open fun terminate() {}

    open fun onEnter() {}
    open fun onExit() {}

}