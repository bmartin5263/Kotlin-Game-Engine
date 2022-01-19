package dev.bdon.engine.entity

import dev.bdon.engine.ecs.EntityId

@DslMarker
annotation class EntityMarker

interface Component {

}

@EntityMarker
class EntityBuilder {

//    inline fun <reified T : Component> register() {
//
//    }

    inline fun <reified T: Component> register(init: (T) -> Unit = {}) {

    }

    inline fun script(init: ScriptBuilder.() -> Unit) {

    }

    fun onSpawn(command: (EntityId) -> Unit) {

    }
}

class ScriptBuilder {

    fun wait(frames: Int) {

    }

    fun run(command: (EntityId) -> Unit) {

    }
}

inline fun entity(init: EntityBuilder.() -> Unit) {
    val builder = EntityBuilder().apply(init)
}

fun main() {
    val spaceShip = entity {
        sequence<Int> {
            yield(1)
        }
    }
}