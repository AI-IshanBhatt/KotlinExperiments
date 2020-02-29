package Chapter6

import java.io.BufferedReader
import java.io.StringReader
import javax.print.attribute.standard.Destination

// Note that List can hold nullable Int,
// List<Int?> = List having either Int or null, but the list itself can not be null
// List<Int>? = list itself can either be null or a list of Int
// It allows List<Int?>? - You know what it means
fun readNumbers(reader: BufferedReader) : List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence())
    {
        result.add(line.toIntOrNull())  // String.toIntOrNull(), If it can't be parsed to string it will return null
    }
    return result
}

fun addValidNumbers(list: List<Int?>) {
    val validNumbers = list.filterNotNull()  // How cool it is to remove all the nulls from the list
    // ALso note that validNumbers is a List<Int> NOT List<Int?>
    println("SUM of valid numbers ${validNumbers.sum()}")
    println("Invalid numbers count is ${list.size - validNumbers.size}")
}

fun <T> copyElements(source: Collection<T>, destination: MutableCollection<T>) {
    destination.addAll(source)
}

fun main() {
    // It could have been on a FileReader as well, but this one gives a BufferedReader on a String
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)

    println("NUMBERS ARE $numbers")
    addValidNumbers(numbers)

    // See the following patterns
    println("READ ONLY COLLECTIONS AND MUTABILITY")
    val source: Collection<Int> = arrayListOf(1,2,3,4)
    val destination: MutableCollection<Int> = arrayListOf(10)
    copyElements(source, destination)
    println(destination)

    // This one shows the important part readonly and immutability, THEY ARE NOT THE SAME
    val x = arrayListOf(1,2,3,4)
    val imutX: Collection<Int> = x
    val mutX: MutableCollection<Int> = x
    mutX.add(5)
    println(imutX)

    println("===================SOME FUN WITH ARRAY============================")
    val letters = Array<String>(26) {i ->  ('a'+i).toString()}  // Here i is the index, type String can be inferred
    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))
    // The spread operator (*) is used to pass an array when vararg parameter is expected.

    val fiveZeros = intArrayOf(0)
    val fiveSquares = IntArray(5) {i -> i*i }  // Could have used it, but this is for the first timers
    println(fiveZeros)
    println(fiveSquares)

    val fiveQuards = fiveSquares.map { it*it }
    println(fiveQuards)

    // Python's enumeration stuff
    fiveQuards.forEachIndexed {index, i ->  println("FOurth Power of $index is $i")}

}