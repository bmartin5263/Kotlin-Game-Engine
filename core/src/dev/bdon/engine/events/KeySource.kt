package dev.bdon.engine.events

import java.util.concurrent.ConcurrentHashMap

abstract class KeySource {
    abstract val pressed: Set<Int>
}

object NullKeySource : KeySource() {
    override val pressed: Set<Int>
        get() = emptySet()
}

class AwtKeySource : KeySource() {
    private val concurrentSet = ConcurrentHashMap.newKeySet<Int>()

    override val pressed: Set<Int>
        get() = HashSet(concurrentSet)

    fun releaseKey(code: Int) {
        concurrentSet.remove(code)
    }

    fun pressKey(code: Int) {
        concurrentSet.add(code)
    }
}