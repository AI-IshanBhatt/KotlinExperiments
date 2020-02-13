package Chapter4

class Button: Focusable, Clickable {
    override fun click() = println("I was clicked")
    override fun showoff() {
        super<Clickable>.showoff()
        super<Focusable>.showoff()
    }
}

// In Kotlin classes are by default marked as final to prevent fragile base class.
// the base class is “fragile” in the sense that any change in it may lead to unexpected changes of behavior in subclasses.
// To make it Non final you need to explicitly specify them as open

open class RichButton: Clickable {  // The class is open so that other classes can inherit from it
    fun disable() {}  // This function by default is final, you can't override in the subclass

    open fun animate() {}  // This function is open, You can override it in a class

    override fun click() {}  // This one is a bit tricky, As this one overrides the click method, It is open by default
                             // You need to mark it final , if you do not want it to be overridden in subclass
                             // final override fun click() {}
}

// Just like Java class can be abstract
abstract class Animated {

    abstract fun animate()  // Must be overridden in the subclasses
    open fun stopAnimating() {}  // Non abstract function , They aren't open by default but can be marked as open
    fun animateTwice() {}  // Non abstract but final as well, can not be overridden
}


fun main() {
    val button = Button()
    button.showoff()
    button.setFocus(true)
    button.click()
}