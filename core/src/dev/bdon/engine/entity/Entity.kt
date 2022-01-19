package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.Engine
import dev.bdon.engine.ecs.HasId
import dev.bdon.engine.ecs.Id
import dev.bdon.engine.ecs.emptyId
import dev.bdon.engine.events.*
import dev.bdon.engine.events.TimerComponent
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.scene.Scene
import java.util.*

abstract class Entity : HasId {

    override var id: Id = emptyId()
    internal var scene: Scene? = null
    private val registerCommandQueue: Deque<RegisterCommand> = ArrayDeque()

    open fun draw(g: Graphics) {}
    open fun update() {}
    open fun onSpawn() {}
    open fun onDestruction() {}

    fun spawn() {
        Engine.currentScene.spawn(this)
    }

    fun markForDestruction() {
        scene!!.markForDestruction(id)
    }

    internal fun addSceneData(scene: Scene) {
        this.scene = scene
        while (registerCommandQueue.isNotEmpty()) {
            registerCommandQueue.poll().execute(scene)
        }
    }

    internal fun removeSceneData() {
        this.scene = null
        this.id = emptyId()
        this.registerCommandQueue.clear()
    }

    internal fun addRegisterCommand(registerCommand: RegisterCommand) {
        if (scene != null) {
            registerCommand.execute(scene!!)
        }
        else {
            registerCommandQueue += registerCommand
        }
    }
}

fun <T : Entity> T.timeout(frames: Long, method: T.(TimerComponent) -> Unit): TimerHandle {
    val handle = TimerHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            if (!handle.isCancelled) {
                require(scene == this@timeout.scene)
                val action = Action1(this@timeout, method)
                val timer = TimeoutTimerComponent(handle, Clock.time + frames, action as Action1<Entity, TimeoutTimerComponent>)
                scene.startTimer(timer)
                handle.link(timer)
            }
        }
    }
    addRegisterCommand(command)
    return handle
}

fun <T : Entity> T.interval(frames: Long, callImmediately: Boolean = false, method: T.(IntervalTimerComponent) -> Unit): IntervalTimerHandle {
    val handle = IntervalTimerHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            if (!handle.isCancelled) {
                require(scene == this@interval.scene)
                val action = Action1(this@interval, method)
                val timer = IntervalTimerComponent(handle, frames, action as Action1<Entity, IntervalTimerComponent>)
                if (callImmediately) {
                    timer.remaining = 0
                }
                scene.startTimer(timer)
                handle.link(timer)
            }
        }
    }
    addRegisterCommand(command)
    return handle
}

fun <T : Entity> T.onKeyPress(key: Int, method: T.() -> Unit): KeyHandle {
    val handle = KeyHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            if (!handle.cancelled) {
                require(scene == this@onKeyPress.scene)
                val action = Action(this@onKeyPress, method)
                val listener = MomentaryKey(handle, key, action as Action<Entity>)
                scene.registerKeyListener(listener)
                handle.link(listener)
            }
        }
    }
    addRegisterCommand(command)
    return handle
}

fun <T : Entity> T.whileKeyPressed(key: Int, delay: Long = 0, method: T.() -> Unit): KeyHandle {
    val handle = KeyHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            if (!handle.cancelled) {
                require(scene == this@whileKeyPressed.scene)
                val action = Action(this@whileKeyPressed, method)
                val listener = PressedKey(handle, key, delay, action as Action<Entity>)
                scene.registerKeyListener(listener)
                handle.link(listener)
            }
        }
    }
    addRegisterCommand(command)
    return handle
}