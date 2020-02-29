package Chapter7


import Chapter4.Person
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int) {
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

class ComparablePerson(private val firstName: String, private val lastName: String) : Comparable<ComparablePerson> {
    override fun compareTo(other: ComparablePerson): Int {
        return compareValuesBy(this, other, ComparablePerson::firstName, ComparablePerson::lastName)
        // This function first compare it with firstName then with the lastName
    }

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
}