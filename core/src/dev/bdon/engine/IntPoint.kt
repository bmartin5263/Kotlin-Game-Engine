package dev.bdon.engine

data class IntPoint(val x: Int, val y: Int) {

    operator fun plus(point: IntPoint): IntPoint {
        return IntPoint(x + point.x, y + point.y)
    }

    operator fun minus(point: IntPoint): IntPoint {
        return IntPoint(x - point.x, y - point.y)
    }
}
