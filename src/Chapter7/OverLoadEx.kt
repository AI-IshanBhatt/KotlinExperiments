package Chapter7


import kotlin.math.pow
import kotlin.math.sqrt

// x,y must be val but I changed it to var so that I can support set method
data class Point(var x: Int, var y: Int) {
    // COuld have done this.x but that is optional
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = sqrt((x - other.x).toDouble().pow(2) + (y - other.y).toDouble().pow(2))

    /*
    override fun equals(obj: Any?): Boolean {  // Overrides the equals method of Any class
        if (obj === this) return true  // Optimization check whether obj is same as this
        if (obj !is Point) return false  // Check parameter type
        return obj.x == x && obj.y == y  // As in the previous line you did !is smart cast is used and obj is converted into Point
}
    */

}

// Defining something as extension function
operator fun Point.times(scale: Int) = Point(x * scale, y * scale)

// Unary minus operator
operator fun Point.unaryMinus() = Point(-x, -y)

// Accessing elements by index get and set
// CHECK THE POSSIBILITY OF USING INHERITANCE FOR 3RD DIMENSION
// point[index] will get translated into get method
operator fun Point.get(index: Int) = when(index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Only 2 dimension points are supported")
}

// Here's a cool trick, using when to assign something / change something,
// Previously we were doing just return expression and other things
// Also, if you needed something like x[a,b] = c -> x.set(a,b,c)
operator fun Point.set(index: Int, value: Int) {
    when(index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Only 2 dimension points are supported")
    }
}

class ComparablePerson(private val firstName: String, private val lastName: String) : Comparable<ComparablePerson> {
    override fun compareTo(other: ComparablePerson): Int {
        return compareValuesBy(this, other, ComparablePerson::firstName, ComparablePerson::lastName)
        // This function first compare it with firstName then with the lastName and so on
    }

}

// The in convention, Say Point(1,2) in SOME RECTANGLE, It is similar to checking if element is in list or map
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(point: Point): Boolean {
    return point.x in upperLeft.x until lowerRight.x && point.y in upperLeft.y until lowerRight.y
}


fun main() {
    val p1 = Point(3,4)
    val p2 = Point(5,7)

    println(p1 + p2)
    println(p1 - p2)
    println(p1 * 3)

    var p3 = Point(10,20)
    p3 += Point(3,3)
    println(p3)
    println(-p3)

    println("DOING STUFF ON COLLECTION")
    val list = arrayListOf(1,2)
    list += 3  // CHanges the current list
    val newList = list + listOf(4, 5)  // Returns new list containing all the elements
    println(list)
    println(newList)

    val per1 = ComparablePerson("Alan", "Smith")
    val per2 = ComparablePerson("Bruce", "Wayne")
    println(per1 < per2)

    println("X co-ordinate of p1 is ${p1[0]}")
    p1[0] = 25
    println("CHANGED X co-ordinate of p1 is ${p1[0]}")

    println("CHECKING THE IN")
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20,30) in rect)  // Here, Point(20,30) is passed as an argument to function contains
    println(Point(5,5) in rect)
}