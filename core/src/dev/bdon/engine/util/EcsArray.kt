package dev.bdon.engine.util

import dev.bdon.engine.ecs.*
import kotlin.math.max

class EcsArray<T : HasId>(
    private val array: Array<T?>,
    private val idGenerator: IdGenerator
) : Iterable<T> {

    private var maxIndex: Int = 0
    var size = 0
        private set

    val nextIndex get() = idGenerator.nextIndex

    fun nextId(): Id = idGenerator.next()

    fun remove(id: Id) {
        if (id.isValid()) {
            array[id.index] = null
        }
    }

    private fun doRemove(id: Id) {
        array[id.index] = null
        --size
    }

    operator fun get(id: Id): T? {
        return if (id.isValid()) {
            array[id.index]
        }
        else {
            null
        }
    }

    operator fun set(id: Id, item: T) {
        require(id.indexIsValid())
        val index = id.index
        require(array[index] == null)

        maxIndex = max(index, maxIndex)
        array[id.index] = item
        ++size
    }

    fun getByIndex(index: Int) = array[index]

    inline fun <reified R : T> getByType(): Array<R> {
        var count = 0
        forEach {
            if (it is R) {
                ++count;
            }
        }
        var i = 0
        return Array(count) {
            while (i < nextIndex) {
                val x = getByIndex(i)
                if (x != null && x is R) {
                    return@Array x
                }
                ++i
            }
            throw IllegalStateException()
        }
    }

    inline fun doForEach(operation: (T) -> Unit) {
        for (i in 0 until nextIndex) {
            getByIndex(i)?.let(operation)
        }
    }

    private fun Id.isValid(): Boolean {
        val index = this.index
        return if (!indexIsValid()) {
            false
        } else {
            val item = array[index]
            val generation = this.generation
            item != null && generation != item.id.generation
        }
    }

    private fun Id.indexIsValid(): Boolean {
        val index = this.index
        return index >= 0 && index < array.size
    }

    override fun iterator(): Iterator<T> = EcsIterator(this)
}