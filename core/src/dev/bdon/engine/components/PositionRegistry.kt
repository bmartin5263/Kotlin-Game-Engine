package dev.bdon.engine.components

import dev.bdon.engine.Config
import dev.bdon.engine.ecs.ECS
import dev.bdon.engine.ecs.Id
import dev.bdon.engine.ecs.index
import dev.bdon.engine.memory.FloatArrayObjectPool
import dev.bdon.engine.memory.Pointer

class PositionRegistry(internal val ecs: ECS) {

    val positions: FloatArrayObjectPool<Pointer> = FloatArrayObjectPool(4, Config.maxEntities)

    internal operator fun get(id: Id): Position? {
        return if (ecs.isAlive(id)) {
            return Position(this, id)
        }
        else {
            null
        }
    }

    internal fun getX(id: Id): Float {
        val index = id.index
        return positions.read(index, 1)
    }

    internal fun getY(id: Id): Float {
        val index = id.index
        return positions.read(index, 2)
    }

    internal fun getCenterX(id: Id): Float {
        val index = id.index
        return positions.read(index, 3)
    }

    internal fun getCenterY(id: Id): Float {
        val index = id.index
        return positions.read(index, 4)
    }

    internal fun setX(id: Id, value: Float) {
        val index = id.index
        positions.write(index, 1, value)
    }

    internal fun setY(id: Id, value: Float) {
        val index = id.index
        positions.write(index, 2, value)
    }

    internal fun setCenterX(id: Id, value: Float) {
        val index = id.index
        positions.write(index, 3, value)
    }

    internal fun setCenterY(id: Id, value: Float) {
        val index = id.index
        positions.write(index, 4, value)
    }

}