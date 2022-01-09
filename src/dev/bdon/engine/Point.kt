package dev.bdon.engine

data class Point(val x: Int, val y: Int) {

    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

    operator fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y)
    }
}
