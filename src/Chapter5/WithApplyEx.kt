package Chapter5

// The below function has a lot of repetition, result is being used but we have to explicitly declare it and use it all the time
fun naiveAlphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z')
        result.append(letter)
    result.append("\n Now I know Alphabet")
    return result.toString()

}

fun alphabet() = with(java.lang.StringBuilder()) {
    for (letter in 'A'..'Z')
        append(letter)  // All operations are being performed on this (instance of StringBuilder)
    append("\n Now I know Alphabet")
    toString()  // This is the return statement
}

fun applyAlpha() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}  // the apply will always return  the receiver object that is StringBuilder()


fun main() {
    println("${naiveAlphabet()}")
    println("${alphabet()}")
    println(applyAlpha())
}