package Chapter4

import java.io.File

object Numbers {
    val allNumbers = arrayListOf<Int>()

    fun calculateAverage() = allNumbers.average()
}

// Object as interface implementation
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path,
            ignoreCase = true)
    }
}

// Implementing comparator with nested object
// Such objects also have just a single instance; they don’t have a separate instance per instance of the containing class.
data class Person(val name: String) {
    object NameComparator: Comparator<Person> {
        // If it was a block body, return expression is a must, unlike Scala
        override fun compare(p1: Person, p2: Person): Int  = p1.name.compareTo(p2.name)
    }
}

fun main() {
    Numbers.allNumbers.addAll(arrayListOf(1,2,3,4,5))
    println(Numbers.calculateAverage()) // See that we are calling directly with Numbers

    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))

    val persons = listOf(Person("bob"), Person("alice"))
    println(persons.sortedWith(Person.NameComparator))  // Calling directly ClassName.ObjectName

}