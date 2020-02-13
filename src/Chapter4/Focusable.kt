package Chapter4

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")  // The whole if expression in ${}, How cool

    fun showoff() = println("I am focusable")
}