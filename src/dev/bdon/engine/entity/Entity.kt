package dev.bdon.engine.entity

import dev.bdon.engine.events.KeyHandle
import dev.bdon.engine.events.Keyboard
import dev.bdon.engine.graphics.Graphics
import dev.bdon.engine.scene.Scene
import java.awt.Color
import java.util.*

abstract class Entity {

    internal var scene: Scene? = null
    internal val registerCommandQueue: Deque<RegisterCommand> = ArrayDeque()

    open fun draw(g: Graphics) {}
    open fun update() {}
    open fun onSpawn() {}
    open fun onDestruction() {}

    internal fun register(scene: Scene) {
        while (registerCommandQueue.isNotEmpty()) {
            registerCommandQueue.poll().execute(scene)
        }
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

//inline fun <T> T.onKeyPress(builder: Script<T>.(T) -> Unit): Script<T> where T : Entity {
//    val script = Script(this)
//    script.builder(this)
//    return script
//}

fun <T : Entity> T.onKeyPress(key: Int, function: T.() -> Unit): KeyHandle {
    val handle = KeyHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            val listener = Keyboard.onKeyPress(this@onKeyPress, scene, key, function)
            handle.activate(listener)
        }
    }
    register(command)
    return handle
}

fun <T : Entity> T.whileKeyPressed(key: Int, delay: Long = 0, function: T.() -> Unit): KeyHandle {
    val handle = KeyHandle()
    val command = object : RegisterCommand {
        override fun execute(scene: Scene) {
            val listener = Keyboard.whileKeyPressed(this@whileKeyPressed, scene, key, delay, function)
            handle.activate(listener)
        }
    }
    register(command)
    return handle
}