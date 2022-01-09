package dev.bdon.engine.util

class Node<T>(
    val data: T,
    var prev: Node<T>? = null,
    var next: Node<T>? = null
) {
}