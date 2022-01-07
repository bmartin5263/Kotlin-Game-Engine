package dev.bdon.engine.entity

import java.awt.Graphics2D

abstract class Entity {
    open fun draw(g: Graphics2D) {}
    open fun update() {}
}