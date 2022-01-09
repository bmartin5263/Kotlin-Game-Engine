package dev.bdon.engine.util

class CyclicIterator<T>(private val array: Array<T>) : ListIterator<T> {

    init {
        assert(array.isNotEmpty())
    }

    private var index = -1

    override fun next(): T {
        index = nextIndex()
        println(index)
        println(array[index])
        return array[index]
    }

    override fun previous(): T {
        index = previousIndex()
        println(index)
        println(array[index])
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