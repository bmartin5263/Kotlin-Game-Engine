package dev.bdon.engine.sprite

import dev.bdon.engine.graphics.Graphics

interface Sprite {
    var x: Int
    var y: Int
//    var width: Int
//    var height: Int
//    var visible: Boolean


    fun draw(g: Graphics)
    fun move(x: Int, y: Int)
}