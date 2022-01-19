package dev.bdon.engine.util

import dev.bdon.engine.ecs.Id
import dev.bdon.engine.ecs.generation
import dev.bdon.engine.ecs.index
import dev.bdon.engine.ecs.createId
import java.util.*

class IdGenerator(private val max: Int) {

    private val released: Deque<Id> = LinkedList()
    var nextIndex: Int = 0
        private set

    fun next(): Id {
        return if (released.isNotEmpty()) {
            val previouslyUsedId = released.remove()
            createId(previouslyUsedId.index, previouslyUsedId.generation + 1u)
        }
        else {
            val next = nextIndex++
            if (nextIndex > max) {
                throw IllegalStateException("Run out of Ids")
            }
            createId(index = next)
        }
    }

    fun release(id: Id) {
        released.add(id)
    }
}