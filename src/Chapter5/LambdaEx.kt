package Chapter5

data class NewPerson(val name: String, val age: Int)

// The following two functions specify the accessibility rules inside the lambda function
fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach{
        println("$prefix -> $it")  // This Lambda can use prefix and message anything
    }
}

// As you can see Lambdas can modify variables from within a Lambda
// In JAVA it was not possible, as lambdas could access only final
fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4"))
            clientErrors++
        else if (it.startsWith("5"))
            serverErrors++
    }
    println("$clientErrors CLIENT ERRORS")
    println("$serverErrors SERVER ERRORS")
}

fun convertor(x: Double, convert: (Double) -> Double): Double {
    return convert(x)
}

fun main() {
    val people = listOf(NewPerson("Ishan", 31), NewPerson("Gunjan", 29),
                        NewPerson("Vanita", 65), NewPerson("Jiya", 10)
    )

    // All the following are the same, In Scala it was _ but here we have it
    println(people.maxBy { it.age })  // code in {} is a lambda implementing logic
    println(people.maxBy(NewPerson::age))  // If a lambda just delegates to a function or property, it can be replaced by a member reference.
    println(people.maxBy { p: NewPerson -> p.age})  // Without using any syntax sugar, like dumb
    println(people.maxBy { p -> p.age })  // Type of p is inferred
    // As with local variables, if the type of a lambda parameter can be inferred, you don’t need to specify it explicitly.
    // When the lambda is the only argument to a function, you can also remove the empty parentheses from the call



    // Store a lambda in a val and treat it like a function
    val sum = {x: Int, y: Int -> x + y}
    println(sum(3,4))

    // Accessibility rules check
    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessageWithPrefix(errors, "ERROR")

    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printProblemCounts(responses)

    // Member reference
    val getAge = NewPerson::age
    println(getAge(NewPerson("I",30)))

    // Although isAdult isn’t a member of the Person class, you can access it via reference,
    fun NewPerson.isAdult() = age > 18
    val predicate = NewPerson::isAdult

    // Both of the lines are same
    println(NewPerson("Gunjan", 29).isAdult())
    println(predicate(NewPerson("Gunjan", 29)))

    println("Passing function to function")
    val result = convertor(5.0, {d ->  d*d})  // convertor(x) {d -> d*d}
    println("THe result is $result")

}