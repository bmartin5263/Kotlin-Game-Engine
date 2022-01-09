package dev.bdon.engine.entity

import dev.bdon.engine.Clock
import dev.bdon.engine.events.*
import dev.bdon.engine.events.Timer
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.scene.Scene
import java.util.*

abstract class Entity {

    internal var scene: Scene? = null
    internal val registerCommandQueue: Deque<RegisterCommand> = ArrayDeque()

    open fun draw(g: Graphics) {}
    open fun update() {}
    open fun initialize() {}
    open fun terminate() {}

    fun markForDestruction() {
        scene!!.destroy(this)
    }

    internal fun registerToScene(scene: Scene) {
        this.scene = scene
        while (registerCommandQueue.isNotEmpty()) {
            registerCommandQueue.poll().execute(scene)
        }
    }

    internal fun deregisterFromScene() {
        this.scene = null
        this.registerCommandQueue.clear()
    }

    internal fun register(registerCommand: RegisterCommand) {
        if (scene != null) {
            registerCommand.execute(scene!!)
        }
        else {
            registerCommandQueue += registerCommand
        }
    }
}

fun <T : Entity> T.timeout(frames: Long, method: T.(Timer) -> Unit): TimerHandle {
    val handle = TimerHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            if (!handle.cancelled) {
                require(scene == this@timeout.scene)
                val action = Action1(this@timeout, method)
                val timer = TimeoutTimer(handle, Clock.time + frames, action as Action1<Entity, TimeoutTimer>)
                scene.startTimer(timer)
                handle.link(timer)
            }
        }
    }
    register(command)
    return handle
}

fun <T : Entity> T.interval(frames: Long, callImmediately: Boolean = false, method: T.(IntervalTimer) -> Unit): TimerHandle {
    val handle = TimerHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            if (!handle.cancelled) {
                require(scene == this@interval.scene)
                val action = Action1(this@interval, method)
                val timer = IntervalTimer(handle, Clock.time + frames, action as Action1<Entity, IntervalTimer>)
                if (callImmediately) {
                    timer.remaining = 0
                }
                scene.startTimer(timer)
                handle.link(timer)
            }
        }
    }
    register(command)
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
    register(command)
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
    register(command)
    return handle
}