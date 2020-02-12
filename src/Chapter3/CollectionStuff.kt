package Chapter3

// In Kotlin we do not need to create that Util class and put methods over there as static methods
// Some classes will be produced because JVM can only execute code in classes.
// If we have only Kotlin application, no need to worry, but if you have mixed Java and Kotlin
// You need to use following snipped
/*
* import Chapter3.CollectionStuffKt; CollectionStuffKt will be your class name
* CollectionStuffKt.joinToString() Use like normal methods
* */
// We can define properties on top level too, const val UNIX_LINE_SEP = "\n"
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// Extension function, Remember the implicit function you loved of Scala, very similar
// We just added a lastChar() method to String class,
// String - Reveiver Type, this - Receiver Object the value on which you’re calling the extension function
fun String.lastChar(): Char = this[this.length - 1]
/*
In a sense, you’ve added your own method to the String class. Even though
String isn’t part of your code, and you may not even have the source code to that
class, you can still extend it with the methods you need in your project. It doesn't even
matter whether String is written in Java, Kotlin, or some other JVM language, such as
Groovy. As long as it’s compiled to a Java class, you can add your own extensions to
that class.
* */

/*
    joinToString function as an extension function.
    A lot of things are going on here, that needs attention.
    1) As functions being first class objects they can be passed around
    2) We have omitted this keyword from RHS, because we are doing the operation on Collection<String> on which we are going to call the function.
    3) See the parameter list, The first parameter of joinToString is that Collection<String> on which the join() will be called
    4) The extension functions can not be overridden in subclasses
*/
fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)

// Extension functions with properties
// In the case below we can not use set property as strings are immutable
// Also note that lastCharacter is a an extension property not an extension method
val String.lastCharacter: Char
    get() = get(length - 1)

// An extension property with get and set, As you should have noticed we changed val to var so that it can have setter
var StringBuilder.lastCharacter: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length -1, value)
    }


fun main() {

    val set = hashSetOf(1,7,9)
    val map = hashMapOf(1 to "One", 2 to "Two", 3 to "Three")

    println(set)
    println(map)

    // Finding the class
    println(set.javaClass)
    println(map.javaClass)

    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())
    println(strings.max())

    // Compiler infers <T> as string based on strings , note that we never specified <String> like we did in Java
    println(joinToString(strings, "; ", "(", ")"))

    // Named arguments
    println(joinToString(strings, separator = "-", prefix = "[", postfix = "]"))

    // Here String is receiver type and IshaN is receiver object
    // On the call site, extension functions are indistinguishable from members, and
    // often it doesn’t matter whether the particular method is a member or an extension.
    println("IshaN".lastChar())

    println(listOf("one", "two", "eight").join(" "))

    // Extension Properties
    println("KOTLIN".lastCharacter)
    val sb = java.lang.StringBuilder("Kotlin?")
    sb.lastCharacter = '*'
    println(sb)

    /*
    * Varargs - Just like java but the argument is not specified using ... but vararg keyword
    * val list = listOf("args: ", *args) is also possible, * is called the spread operator
    * */

    // Infix call
    /* Remember 1 to "One", 2 to "Two"
    * Here to is an infix call, that takes one argument and returns a Pair
    * Infix calls can be used with regular methods and extension functions that have one REQUIRED PARAMETER.
    * Syntax of such functions would be
    * infix fun Any.to(other: Any) = Pair(this, other)
    * */
}