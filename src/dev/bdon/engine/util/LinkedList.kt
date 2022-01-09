package dev.bdon.engine.util

class LinkedList<T> {

    var head: Node<T>? = null
    var tail: Node<T>? = null

    val isEmpty get() = head == null
    val isNotEmpty get() = head != null

    fun pushFront(value: T) {
        if (isEmpty) {
            head = Node(value)
            tail = head
        }
        else {
            head!!.prev = Node(value, next = head)
            head = head!!.prev
        }
    }

    fun popFront(): T {
        assert(isNotEmpty)
        val value = head!!.data
        if (head == null) {
            tail = null
        }
        return value
    }
}