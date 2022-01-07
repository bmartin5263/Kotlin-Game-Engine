package dev.bdon.engine.entity

import java.awt.Graphics2D

class BasicEntity : Entity() {

    var updateFn: () -> Unit = {}

    override fun update() {
        updateFn()
    }
}