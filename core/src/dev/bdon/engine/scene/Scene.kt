package dev.bdon.engine.scene

import dev.bdon.engine.Clock
import dev.bdon.engine.Engine
import dev.bdon.engine.ecs.EntityRegistry
import dev.bdon.engine.ecs.Id
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.KeyMap
import dev.bdon.engine.entity.TimerQueue
import dev.bdon.engine.events.KeyListener
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.events.TimerComponent
import dev.bdon.engine.events.Timers

abstract class Scene {

    internal val timerQueue: TimerQueue = TimerQueue()
    internal val keyMap: KeyMap = KeyMap()

    internal val entityRegistry: EntityRegistry = EntityRegistry()
    internal val liveEntities: Iterator<Entity>
        get() = entityRegistry.arr.iterator()

//    internal val liveEntities: MutableSet<Entity> = HashSet()

    internal var lastFrameProcessed: Long = -1L

    open fun initialize() {}
    open fun terminate() {}
    open fun onEnter() {}
    open fun onExit() {}

    fun open() {
        Engine.requestSceneChange(this)
    }

    fun close() {
        Engine.requestSceneRemoval(this)
    }

    fun spawn(entity: Entity) {
        entityRegistry.register(entity)
    }

    fun spawn(vararg entities: Entity) {
        entities.forEach { spawn(it) }
    }

    fun markForDestruction(entity: Entity) {
        markForDestruction(entity.id)
    }

    fun markForDestruction(id: Id) {
        entityRegistry.deregister(id)
    }

    internal fun frameDelta(): Long {
        return Clock.time - lastFrameProcessed
    }

    internal fun nextFrame() {
        entityRegistry.destroyEntities()
        entityRegistry.spawnEntities()
        println(entityRegistry.size)

        entityRegistry.update()
        processInputEvents()
        Timers.process(this)

        lastFrameProcessed = Clock.time
    }

    internal fun fastForward(frames: Long) {
        keyMap.fastForwardMomentaryKeys()
        timerQueue.fastForwardTimeouts(frames)
    }

    private fun processInputEvents() {
        Keyboard.recordKeys()
        updateKeyListeners()
    }

    private fun updateKeyListeners() {
        keyMap.doAddRequests()
        for (code in Keyboard.pressed) {
            keyMap.updateListeners(code)
        }
        keyMap.doRemoveRequests()
    }

    private fun doSpawn(entity: Entity) {
        entity.addSceneData(this)
        entity.onSpawn()
    }

    private fun doDestroy(entity: Entity) {
        entity.onDestruction()

        // Automatically deregister from events
        keyMap.removeListenersFor(entity)
        timerQueue.removeTimersFor(entity)

        entity.removeSceneData()
    }

    internal fun registerKeyListener(keyListener: KeyListener) {
        keyMap.addListener(keyListener)
    }

    internal fun deregisterKeyListener(keyListener: KeyListener) {
        keyMap.removeListener(keyListener)
    }

    internal fun startTimer(timer: TimerComponent) {
        timerQueue.add(timer)
    }

    internal fun cancelTimer(timer: TimerComponent) {
        timerQueue.remove(timer)
    }

}