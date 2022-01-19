package dev.bdon.engine.components

import dev.bdon.engine.ecs.Id
import dev.bdon.engine.entity.Component

class Position(internal val registry: PositionRegistry, internal val id: Id) : Component {
    var x: Float
        get() = registry.getX(id)
        set(value) = registry.setX(id, value)

    var y: Float
        get() = registry.getY(id)
        set(value) = registry.setY(id, value)

    var centerX: Float
        get() = registry.getCenterX(id)
        set(value) = registry.setCenterX(id, value)

    var centerY: Float
        get() = registry.getCenterY(id)
        set(value) = registry.setCenterY(id, value)
}