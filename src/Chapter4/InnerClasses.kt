package Chapter4

import java.io.Serializable

// Kotlin nested classes don’t have access to the outer class instance, unless you specifically request that.
interface State: Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class Buttonning: View {

    // Here the ButtonState being an inner class does not hold the reference to Buttonning class
    // It is like Java's static inner class which do not have the reference to outer class, In Kotlin inner class are Java's static inner class
    // Declaring a nested class as static removes the implicit reference from that class to its enclosing class.
    class ButtonState : State {}

    override fun getCurrentState() = ButtonState()
    override fun restoreState(state: State) {}


}

fun main() {

}