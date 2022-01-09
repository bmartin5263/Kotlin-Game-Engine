package dev.bdon.engine.events

class KeyHandle {

    internal var cancelled: Boolean = false
    var keyListener: KeyListener? = null

    internal fun link(keyListener: KeyListener) {
        this.keyListener = keyListener
    }

    internal fun unlink() {
        this.keyListener = null
    }

    fun cancel() {
        if (!cancelled) {
            cancelled = true
            if (keyListener != null) {
                keyListener!!.cancel()
            }
        }
    }
}