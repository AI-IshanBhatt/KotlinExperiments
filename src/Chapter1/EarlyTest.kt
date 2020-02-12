package Chapter1

import Chapter3.lastChar
import java.io.BufferedReader
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

/*
* kotlinc <source file or directory> -include-runtime -d <jar name>
* Code compiled with the Kotlin compiler depends on the Kotlin runtime library. It contains
* the definitions of Kotlin’s own standard library classes and the extensions that
* Kotlin adds to the standard Java APIs. The runtime library needs to be distributed with
* your application.
* java -jar <jar name>
* */
data class Person(val name: String, val age: Int? = null)

// Cases in the when must be exhaustive
// Also note that when is an expression not statement, that's why = is allowed
fun getMnemonics(color: Color) =
    when(color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

fun getWarmth(color: Color) =
    when(color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.INDIGO, Color.BLUE, Color.VIOLET -> "cold"
    }

// Using some objects, other than enum
// Even though you are throwing exception , you don't need to throw it
fun mix(c1: Color, c2: Color) =
    when(setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
        else -> throw Exception("Dirty Color")
    }

// Implementation of expr as sum and num
class Num(val value: Int) : Expr
class Sum(val left :Expr, val right: Expr) : Expr


fun evalDummy(e: Expr): Int {  // Can't be inferred as it contains multiple lines
    // We are checking if e is num , here is used to check it,
    // e as num for type cast,
    // Great thing about Kotlin is that once "is" is used to check type you can directly use e without explicit cast
    if (e is Num) {  // if can be used Normally as well. Thank God
        val n = e as Num
        return n.value
    }
    if (e is Sum) {
        return evalDummy(e.right) + evalDummy(e.left)
    }
    throw IllegalArgumentException("Unknown Expression")
}

// Here return type is needed otherwise the function will go in infinite recursion
// https://kotlinlang.org/docs/reference/functions.html#explicit-return-types
fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value  // Be assured that the cast is performed automatically using is
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown Expression")
    }

fun fizzBuzz(i: Int) = when {
    i%15 == 0 -> "FizzBuzz "
    i%3 == 0 -> "Fizz "
    i%5 == 0 -> "Buzz "
    else -> "$i "
}

// Using "in" to check collection and range membership
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

// Using "in" expression in when, very powerful construct
fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "DIGIT"
    in 'a'..'z', in 'A'..'Z' -> "LETTER"
    else -> "YOU RETARD"
}

// Using exception try,catch, finally blocks
// See that throws clause is not there, In Java it has to be present
// Kotlin doesn’t require you to declare the exceptions that can be thrown by a function.
// Operation on BufferedReader can throw IOException which is a checked exception.
// In Java checked exceptions needs to be handled explicitly, here not anymore even if you call something and that throws exception you are still good not declaring them
fun readNumber(reader: BufferedReader): Int? { // Notice Int? which signifies that this function can return null
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) { return null }
    finally {
        reader.close()
    }
}

// You can use try as an expression as well , just like if, when you can assign it;s value to a variable
fun anotherReadNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {null}
    println(number)  // Number will be either actual number or null based on reader's content How Cool !
}


fun main(args: Array<String>) {

    val persons = listOf(Person("Alice"),
                        Person("Bob", age = 23)
        )

    val oldest = persons.maxBy { it.age ?: 0 }
    println("The oldest is $oldest")

    println(createRectangle().isSquare)

    println("SOME PLAY WITH enum and when")
    println(Color.BLUE.rgb())
    println(getMnemonics(Color.INDIGO))
    println(getWarmth(Color.YELLOW))

    // As we have kept setOf in when so it just sees which colors are there not their order
    println(mix(Color.YELLOW, Color.BLUE))
    println(mix(Color.BLUE, Color.YELLOW))

    // Examples showcasing smart cast
    println(eval(Sum(Num(1), Num(2))))
    println(evalDummy(Sum(Num(1), Num(9))))

    // Continue with the loops
    for (i in 1..100)  // this includes 100
        print(fizzBuzz(i))

    // Do 100 to 1 with step 2
    println()
    for (i in 100 downTo 1 step 2)
        print(fizzBuzz(i))

    println()
    // Don't want to include the end point use until
    for (x in 1 until 100 step 2)
        print(fizzBuzz(x))

    // Some kind of enemuration
    println()
    val list = listOf("10", "11", "12")
    for ((index, element) in list.withIndex())
        println("$index => $element")

    println(isLetter('x'))
    println(isNotDigit('9'))

    println("Recognizing stuff")
    println(recognize('9'))
    println(recognize('Y'))

    // Range (in) is not restricted to primitives, it can work with anything that implements Comparable interface
    // Exceptions in Kotlin

    // Here we have used the extension function from Chapter3, String -> receiver type, GUNJ -> receiver object
    println("GUNJ".lastChar())

}