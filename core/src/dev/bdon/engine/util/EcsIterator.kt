package dev.bdon.engine.util

import dev.bdon.engine.ecs.HasId

class EcsIterator<T : HasId>(private val target: EcsArray<T>) : Iterator<T> {

    private var index: Int = 0

    init {
        moveIndex()
    }

    override fun hasNext(): Boolean {
        return index < target.nextIndex
    }

    override fun next(): T {
        require(hasNext())
        val out = target.getByIndex(index++)
        moveIndex()
        return out!!
    }

    private fun moveIndex() {
        while (index < target.nextIndex && target.getByIndex(index) == null) {
            ++index
        }
    }
}