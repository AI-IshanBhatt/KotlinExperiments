package Chapter5

data class Trader(val name: String, val city: String)

data class Transaction(val trader: Trader, val year: Int, val value: Int)

fun main() {

    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")

    val transactions = listOf(
        Transaction(brian, 2011, 300),
        Transaction(raoul, 2012, 1000),
        Transaction(raoul, 2011, 400),
        Transaction(mario, 2012, 710),
        Transaction(mario, 2012, 700),
        Transaction(alan, 2012, 950)
    )
    // Press ctrl + alt + v after you type in the expression
    println("1.Find all transactions in the year 2011 and sort them by value (small to high).")
    val ans1 = transactions.filter { it.year==2011 }.sortedBy { it.value }
//    ans1.forEach { println(it) }
    println(ans1)

    println("2. What are all the unique cities where the traders work?")
    val ans2 = transactions.map { it.trader }.map { it.city }.distinct()
    println(ans2)

    println("3. Find all traders from Cambridge and sort them by name")
    val ans3 = transactions.map { it.trader }.filter { it.city == "Cambridge" }.distinct().sortedBy { it.name }
    println(ans3)

    println("4. Return a string of all traders’ names sorted alphabetically");
    val ans4 = transactions.map { it.trader }.map { it.name }.distinct().sorted()
    println(ans4)

    println("5. Are any traders based in Milan?")
    val ans5 = transactions.map { it.trader }.distinct().any { it.city == "Milan" }  // In java we had anyMatch, In Kotlin any function is overloaded
    println(ans5)

    println("6. Print all transactions’ values from the traders living in Cambridge")
    val ans6 = transactions.filter { it.trader.city == "Cambridge" }.map { it.value }
    println(ans6)

    println("7. What’s the highest value of all the transactions?")
    val ans7 = transactions.map { it.value }.max()
    println(ans7)

    println("8. Find the transaction with the smallest value");
    val ans8 = transactions.minBy { it.value }
    println(ans8)

    println("9. Sum of all trades by mario");
    val ans9 = transactions.filter { it.trader.name == "Mario" }.sumBy { it.value }  // Kotlin is so smart it suggests whether it is sum() or sumBy() to be called
    println(ans9)
}