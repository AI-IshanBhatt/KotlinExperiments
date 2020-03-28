package Chapter9

// Very naive implementation, the types are invariant
// Also, source is just used for reading, destination just for writing
// It is very much valid to add strings in destination list of Any
fun <T> copyDataSimple(source: MutableList<T>, destination: MutableList<T>) {
    for (item in source)
        destination.add(item)
}

fun <T:R, R> copyTwoTypes(source: MutableList<T>, destination: MutableList<R>) {
    for (item in source)
        destination.add(item)
}

fun <T> copyVar(source: MutableList<out T>, destination: MutableList<in T>) {
    source.forEach { destination.add(it) }
}

fun main() {

    val ints = mutableListOf(1,2,3,4)
    val anyItems = mutableListOf<Any>()
    copyTwoTypes(ints, anyItems)
    println(anyItems)
    copyVar(ints, anyItems)
    println(anyItems)
}