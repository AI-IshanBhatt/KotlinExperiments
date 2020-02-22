package Chapter5

data class Book(val title: String, val authors: List<String>)

fun main() {

    // flatMap does 2 things
    // At first it transforms (or maps) each element to a collection according to the function given as an argument.
    // And then it combines (or flattens) several lists into one.
    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val books = listOf(Book("Thursday Next", listOf("Jasper Fforde")),
                        Book("Mort", listOf("Terry Pratchett")),
                        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))

    println("FIND UNIQUE AUTHORS")
    // it.title will not work it has to be some iterable, if you do it.title.toList(), then it is okay
    // map would have returned here list of list on which calling toSet is useless
    val uniqueAuthors = books.flatMap { it.authors }.toSet()
    println(uniqueAuthors)

    // Note that if you don’t need to transform anything and just need to flatten such a collection.
    // You can use the flatten function: listOfLists.flatten().

}