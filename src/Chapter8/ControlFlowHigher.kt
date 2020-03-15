package Chapter8

data class Person(val name: String, val age: Int)

fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found")
            return  // nonlocal return as it returns from the enclosing function not only from lambda
        }
        println("Alice Not FOund")
    }
}

// Just return from the lambda do not return from the function itself
fun localLookForAlice(people: List<Person>) {
    people.forEach label@{
        if (it.name == "Alice") {
            println("Found")
            return@label
        }
        println("Might be lost in there")  // This will always be printed
    }

}

fun main() {

    val people = listOf(Person("Alice", 29), Person("Ishan", 31))
    lookForAlice(people)
    localLookForAlice(people)
}