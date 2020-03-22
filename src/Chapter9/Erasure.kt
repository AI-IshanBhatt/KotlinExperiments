package Chapter9

import java.lang.IllegalArgumentException

// Her compiler warns you that this is UncheckedCast
// The type information about Int is erased at runtime so that's what compiler issues as warning
fun printSum(c: Collection<*>) {
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}

// Also if c: Collection<*> is specified, then c is List<Int> will be a compilation error
// Cannot check type of erased type List<Int>
fun anotherSum(c: Collection<Int>) {
    if (c is List<Int>)
        println(c.sum())
}

// Checking the type at runtime with help of inline function
// their type arguments can be reified.
inline fun <reified T> isA(value: Any) = value is T

fun main() {
    printSum(listOf(1,2,3))
//    printSum(setOf(1,2,3))  // This will cause an IllegalArgument Exception
//    printSum(listOf("a","b"))  // Here the as? will pass as it checks the base class is checked not type list
                                // So it fails when sum() is called
    anotherSum(listOf(3,4,5))
//    anotherSum(listOf("a"))  // This will never pass as the argument itself has type Int
    println(isA<String>(1))
    println(isA<String>("a"))

    println(listOf("One", 2, "Three").filterIsInstance<String>())  // As you might have guessed this has to be inline function with refied
}