package Chapter1

import java.util.Random

class Rectangle(val height: Int, val width: Int) {

    val isSquare: Boolean get() = height == width
}

// you must declare the type of the value the function is intending to return, if function is more than 1 liner
// It contains more than 1 statements
// unlike val that is not going to be inferred
fun createRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}