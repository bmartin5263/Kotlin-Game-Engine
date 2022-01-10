package dev.bdon.engine.events

class Action1<T, P1>(
    val target: T,
    val method: T.(P1) -> Unit
) {
    operator fun invoke(obj1: P1) {
        target.method(obj1)
    }
}