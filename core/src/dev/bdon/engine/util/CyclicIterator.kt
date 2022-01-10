package dev.bdon.engine.util

import java.util.function.Consumer

class CyclicIterator<T>(private val array: Array<T>) : ListIterator<T> {

    init {
        assert(array.isNotEmpty())
    }

    private var index = -1

    override fun next(): T {
        index = nextIndex()
        return array[index]
    }

    override fun previous(): T {
        index = previousIndex()
        return array[index]
    }

    override fun hasNext() = true
    override fun hasPrevious() = true

    override fun nextIndex(): Int {
        return if (index == array.size) {
            0
        }
        else {
            index + 1
        }
    }

    override fun previousIndex(): Int {
        return if (index <= 0) {
            array.size - 1
        }
        else {
            index - 1
        }
    }
}

fun <T> Array<T>.cycleIterator() = CyclicIterator(this)