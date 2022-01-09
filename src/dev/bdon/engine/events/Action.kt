package dev.bdon.engine.events

class Action<T>(
    val target: T,
    val method: T.() -> Unit
) {
    operator fun invoke() {
        target.method()
    }
}