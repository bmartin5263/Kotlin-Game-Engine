package dev.bdon.engine

data class Point(val x: Float, val y: Float) {

    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

    operator fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y)
    }
}
