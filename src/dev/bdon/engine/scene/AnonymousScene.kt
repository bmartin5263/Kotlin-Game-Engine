package dev.bdon.engine.scene

class AnonymousScene : Scene() {

    var onInitializeFn: Scene.() -> Unit = {}

    override fun initialize() = onInitializeFn()
}