package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics
import java.lang.Integer.max
import kotlin.math.abs

class SpriteList(container: MutableCollection<Sprite>) : MutableSpriteGroup() {
    override val sprites = container
}