@file:Suppress("FINAL_UPPER_BOUND")

package dev.bdon.engine.memory

val EMPTY_FLOAT_HEADER = Float.Companion.fromBits(0)
val FREE_FLOAT_HEADER = Float.Companion.fromBits(0x7FFF_FFFF)

interface FloatObjectPool<T : Pointer> {
    fun alloc(index: Int, header: Float = EMPTY_FLOAT_HEADER): T
    fun read(index: Int, field: Int): Float
    fun write(index: Int, field: Int, value: Float)
    fun dealloc(index: Int)
}

@Suppress("UNCHECKED_CAST")
class FloatArrayObjectPool<T : Pointer>(
    val objectSize: Int,
    private val numberOfObjects: Int
): FloatObjectPool<T> {

    val pool: FloatArray = FloatArray(objectSize * numberOfObjects)
    var liveObjects: Int = 0
        private set

    override fun alloc(index: Int, header: Float): T {
        val ptr = index.toPointer()
        require(pool[ptr] == FREE_FLOAT_HEADER) { "Attempting to allocate index twice: $index" }
        ++liveObjects
        pool[ptr] = header
        return ptr
    }

    override fun read(index: Int, field: Int): Float {
        val ptr = index.toPointer()
        require(pool[ptr] != FREE_FLOAT_HEADER) { "Attempting to read unallocated memory: ($index, $field)" }
        return pool[ptr + field]
    }

    override fun write(index: Int, field: Int, value: Float) {
        val ptr = index.toPointer()
        require(pool[ptr] != FREE_FLOAT_HEADER) { "Attempting to write to unallocated memory: ($index, $field)" }
        pool[ptr + field] = value
    }

    override fun dealloc(index: Int) {
        val ptr = index.toPointer()
        require(pool[ptr] != FREE_FLOAT_HEADER) { "Attempting to deallocate unallocated memory: $index" }
        --liveObjects
        pool[index.toPointer()] = FREE_FLOAT_HEADER
    }

    inline fun forEach(operation: (FloatArray, Int) -> Unit) {
        var i = 0
        var checked = 0
        while (checked < liveObjects && i < pool.size) {
            if (pool[i] != FREE_FLOAT_HEADER) {
                operation(pool, i)
                ++checked
            }
            i += objectSize + 1
        }
        throw IllegalStateException()
    }

    fun Int.toPointer(): T = (this * (objectSize + 1)) as T
    fun Int.toPointer(field: Int): T = ((this * (objectSize + 1)) + field) as T
}