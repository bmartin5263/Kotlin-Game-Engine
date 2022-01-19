package dev.bdon.engine.ecs

import dev.bdon.engine.Engine
import dev.bdon.engine.components.PositionRegistry
import dev.bdon.engine.entity.Entity
import dev.bdon.engine.util.EcsArray
import kotlin.collections.ArrayDeque

class ECS {

    val entities: EcsArray<Entity> = Engine.createEcsArray()

    private val spawnRequests = ArrayDeque<SpawnCommand>()
    private val destroyRequests = ArrayDeque<ECS.() -> Unit>()

    val positionRegistry: PositionRegistry = PositionRegistry(this)

    val size get() = entities.size

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Entity> get(id: Id): T? = entities[id] as T?

    inline fun <reified T : Entity> getByType(): Array<T> = entities.getByType()

    fun register(entity: Entity): Id {
        val id = entities.nextId()
        spawnRequests += SpawnCommand(entity, id)
        return id
    }

    internal fun doRegister(entity: Entity, id: Id) {
        entities[id] = entity
    }

    fun deregister(id: Id) {
        throw NotImplementedError()
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
        entities.forEach {
            it.update()
        }
    }

    fun isAlive(id: Id) = entities.isAlive(id)
}