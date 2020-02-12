package Chapter3

/*
* This file explains the nature of extension function in terms of subclassing, overridden etc
* */

open class View {
    open fun click() = println("View Clicked")
}

// Class overriding syntax, similar to Interface implementation but this one needs to include the constructor hence ()
class Button: View() {
    override fun click() = println("Button Clicked")
}

/*
Extension functions aren’t a part of the class; they’re declared externally to it.
Even though you can define extension functions with the same name and parameter
types for a base class and its subclass, the function that’s called depends on the
DECLARED STATIC TYPE of the variable, not on the runtime type of the value stored in that variable.
*/

// KEEP - If the class has a member function with the same signature as an extension function, the member function always takes precedence.
fun View.showOff() = println("VIEW SHOW OFF")
fun Button.showOff() = println("BUTTON SHOW OFF")

fun main() {

    // click method as you would expect obeys dynamic dispatch
    // But it is not the case with extension function
    val view: View = Button()
    view.click()

    view.showOff() // view show off will be called based on the type not object
}