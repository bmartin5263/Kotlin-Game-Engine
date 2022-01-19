package dev.bdon.engine.ecs

import dev.bdon.engine.entity.Entity

class SpawnCommand(
    private val entity: Entity,
    private val id: Id
) {
    operator fun invoke(ECS: ECS) {
        ECS.doRegister(entity, id)
    }
}