package dev.bdon.engine.ecs

typealias Id = ULong

const val INDEX_MASK: ULong =            0b0000000000000000000000000000000000000000111111111111111111111111uL
const val GENERATION_MASK: ULong =       0b0000000000000000000000000000000011111111000000000000000000000000uL

val Id.index: Int
    get() = (this and INDEX_MASK).toInt()

val Id.generation: UInt
    get() = (this and GENERATION_MASK).toUInt() shr 24

fun createId(index: Int = 0, generation: UInt = 1u): Id {
    require(generation > 0u)

    var id: Id = 0uL
    id += index.toUInt()
    println(id.toString(2))
    id = id xor (generation shl 24).toULong()
    println(id.toString(2))

    require(id.index == index)
    require(id.generation == generation) { "Expected generation ${id.generation} to equal $generation" }

    return id
}

fun emptyId(): Id {
    return 0uL
}

typealias EntityId = Id

fun <T> EntityId.get(): T {
    throw IllegalStateException()
}

fun <T> EntityId.register(): T {
    throw IllegalStateException()
}