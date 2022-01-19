package dev.bdon.engine.ecs

import dev.bdon.engine.Engine
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.util.EcsArray
import kotlin.collections.ArrayDeque

class EntityRegistry {

    val arr: EcsArray<Entity> = Engine.createEcsArray()
    private val spawnRequests = ArrayDeque<EntityRegistry.() -> Unit>()
    private val destroyRequests = ArrayDeque<EntityRegistry.() -> Unit>()

    val size get() = arr.size

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Entity> get(id: Id): T? = arr[id] as T?

    inline fun <reified T : Entity> getByType(): Array<T> = arr.getByType()

    fun register(entity: Entity): Id {
        val id = arr.nextId()
        spawnRequests += { arr[id] = entity }
        return id
    }

    fun deregister(id: Id) {

    }

    fun destroyEntities() {
        destroyRequests.forEach {
            it.invoke(this)
        }
        destroyRequests.clear()
    }

    fun spawnEntities() {
        spawnRequests.forEach {
            it.invoke(this)
        }
        spawnRequests.clear()
    }

    fun update() {
        arr.forEach {
            it.update()
        }
    }
}