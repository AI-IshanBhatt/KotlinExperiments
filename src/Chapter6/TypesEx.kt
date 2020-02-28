package Chapter6


interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    // The following implementation will not work with Java's void, which requires you to explicitly return something
    override fun process() {
        println("Printing but the return type is Unit so it works")
        // Here we did not require explicit return stmt, in Java you would have done return null;
    }
}

fun fail(message: String) : Nothing {
    throw IllegalStateException(message)
}

fun main() {

    val nrp = NoResultProcessor()
    nrp.process()

    val x = null
    val attrib = x ?: fail("YOU RETARD")  // This will throw an exception and here Nothing return type helps
}