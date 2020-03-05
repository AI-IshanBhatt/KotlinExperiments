package Chapter8

import java.lang.StringBuilder

/*  Remember that joinToString ? , Now you need to implement the same function but with a twist
    In that you just did sb.append(element) , now StringBuilder's append with append only string so in you case
    If the element is not string, It's toString method will be called but sometimes you need another type of behaviour
    So, you can specify how to transform that element to String, giving a lambda function.
    But most of the time your callers will not care about this so you should not force them to provide a lambda,
    So your function type should have a default value (just like default values to your parameters)
 */

fun <T> Collection<T>.joiningString(
    seperator: String = ",", prefix: String="", postfix:String = "",
    transform: (T) -> String = { it.toString() }  // Parameter of function type with a lambda as a default value
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex())  { // Here this is some collection, and compiler infers that
        if (index > 0) result.append(seperator)
        result.append(transform(element))  // How fucking elegant this looks like
    }
    result.append(postfix)
    return result.toString()
}


fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2,3)  // Doing the operation passed on arguments 2 and 3
    println("The Result is $result")

}

// Pass a predicate that takes a single character of string and return Boolean based on some condition, and filter function returns String
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (letter in this) {  // Don't use that Book's for loop which want s to use length and get on implicit variable, iterate over this(the receiver object)
        if (predicate(letter)) sb.append(letter)
    }
    return sb.toString()
}

// RETURNING A FUNCTION FROM FUNCTION
enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

// It will return a function that takes Order and returns it's shippingCost in double
fun getShippingCostCalculator(delivery: Delivery) : (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) return { order ->  6 + 4.0*order.itemCount}
    else return { order -> 2.0*order.itemCount }
}

// Suppose for a contact list, you want to check if the person name starts with a prefix and/or he has number or not
data class UIPerson(val firstName: String, val lastName: String, val phoneNumber: String?)

// When a user types D to see the contacts whose first or last name starts with D, the prefix value is updated.
class ContactListFilters {
    var prefix: String = ""
    var onlyWithNumber: Boolean = false

    fun getPredicate(): (UIPerson) -> Boolean {
        val startsWithPrefix = { p: UIPerson -> p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix) }

        if (!onlyWithNumber) return startsWithPrefix  // Returns a variable of a function type
        return {
            startsWithPrefix(it) && it.phoneNumber!=null  // Returns a Lambda, but it is smart enough to figure out that it is a UIPerson
                                                          // As we had specified the return type as (UIPerson) -> Boolean, it infers that the lambda would be of same signature
                                                          // So, it has to be UIPerson (captured variable)
        }
    }
}


fun main() {
    twoAndThree {a, b ->  a+b}
    twoAndThree {a, b -> a-b}

    println("a1b2c3d4".filter { it in 'a'..'z' })

    val letters = listOf("Alpha", "Beta")
    println(letters.joiningString())
    println(letters.joiningString { it.toUpperCase() })  // Make sure this is function invocation, it looks like it is calling the function but it is not

    val numbers = listOf(1,2,3,4,5)
    println("Squares ${numbers.joiningString(seperator = "-"){ i ->  (i*i).toString()}}")

    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    val shippingCost = calculator(Order(10))

    println("Shipping cost of Expedited Delivery is $shippingCost")

    val contacts = listOf(UIPerson("Dmitry", "Jemerov", "123-4567"), UIPerson("Svetlana", "Isakova", null))

    val contactListFilters = ContactListFilters()
    with(contactListFilters) {
        prefix = "D"
        onlyWithNumber = true
    }

//    println(contacts.filter(contactListFilters.getPredicate()))  // Passing a function returned by getPredicate as an argument to filter
    println(contacts.filter { contactListFilters.getPredicate()(it) })  // Accept that above line looks better
}