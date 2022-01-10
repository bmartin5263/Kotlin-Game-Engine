package dev.bdon.engine.scene

import dev.bdon.engine.Clock
import dev.bdon.engine.Engine
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.entity.KeyMap
import dev.bdon.engine.entity.TimerQueue
import dev.bdon.engine.events.KeyListener
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.events.Timer
import dev.bdon.engine.events.Timers

abstract class Scene {

    internal val timerQueue: TimerQueue = TimerQueue()
    internal val keyMap: KeyMap = KeyMap()
    internal val liveEntities: MutableSet<Entity> = HashSet()

    private val spawnRequests: MutableSet<Entity> = HashSet()
    private val destroyRequests: MutableSet<Entity> = HashSet()

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
        if (entity !in liveEntities) {
            spawnRequests += entity
        }
    }

    fun spawn(vararg entities: Entity) {
        entities.forEach { spawn(it) }
    }

    fun markForDestruction(entity: Entity) {
        if (entity in liveEntities) destroyRequests += entity
    }

    internal fun frameDelta(): Long {
        return Clock.time - lastFrameProcessed
    }

    internal fun nextFrame() {
        spawnNewEntities()

        liveEntities.forEach { it.update() }
        processInputEvents()
        Timers.process(this)

        destroyExpiredEntities()
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
        keyMap.startNewListeners()
        for (code in Keyboard.pressed) {
            keyMap.updateListeners(code)
        }
        keyMap.removeExpiredListeners()
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

    internal fun registerKeyListener(keyListener: KeyListener) {
        keyMap.addListener(keyListener)
    }

    internal fun deregisterKeyListener(keyListener: KeyListener) {
        keyMap.removeListener(keyListener)
    }

    internal fun startTimer(timer: Timer) {
        timerQueue.add(timer)
    }

    internal fun cancelTimer(timer: Timer) {
        timerQueue.remove(timer)
    }

}