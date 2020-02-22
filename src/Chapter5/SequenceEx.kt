package Chapter5

// They are JAVA's streams, basically they are lazy Nothing will be computed until a terminal operation is performed.
// You can convert any collection to a sequence by calling the extension function asSequence.
// You call toList for backward conversion.

/*
NOTE As a rule, use a sequence whenever you have a chain of operations on a
large collection. In section 8.2, we’ll discuss why eager operations on regular
collections are efficient in Kotlin, in spite of creating intermediate collections.
But if the collection contains a large number of elements, the intermediate
rearranging of elements costs a lot, so lazy evaluation is preferable.

Intermediate - map, filter, flatMap
Terminal - toList, first(), any(), find()

In collection, everything is first mapped, then filtered
In Sequence, first element is mapped, filtered, then 2nd and so on.
So if the desired result is returned after only 2 elements, rest will never be mapped
PARALLEL SEQUENCE IS NOT YET IMPLEMENTED
* */

fun main() {

    val people = listOf(NewPerson("Ishan", 31), NewPerson("Gunjan", 29),
        NewPerson("Vanita", 65), NewPerson("Jiya", 10))

    val x = people.asSequence().map { it.name }.filter { it.startsWith("J") }
    println(x)  // this is a sequence as toList will convert it into the resulting list, so you can't access element by index
    println(x.toList())

    println("USING GENERATE SEQUENCE")
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())


}