package Chapter9

import java.lang.Appendable


// I knwo very vague example
// <T> is the type parameter, List<T> is a receiver type as well as the return type
fun <T> List<T>.takeLastFour() : List<T> {
    return this.takeLast(4)
}

// Ypu can also define the extension properties as gerneric as well
val <T> List<T>.penultimate : T
    get() = this[size - 2]  // This is not Python this[-2] will not work, size is available as property , use it

/*
* fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T>
* This is the implementation of filter, type T, receiver List<T>, type param is used in the predicte as well
* the predicate's argument has to be of type T, and returns Boolean
* */


// Upper Bounds
//fun List<Int>.summingDouble() : Int = this.sumBy { it*2 }, this one works with * operator
// In generic type compiler can not make up whether * is allowed or not
fun <T: Number> List<T>.xyz() : Unit {
    forEach { print(it) }
//    filter { it%2 == 0 }  this ca =n not work because some of Number's implementation do not have %
}

//fun List<Int>.df() : List<Int> = filter { it%2==0 }
// The above BS works as it knows for sure that Int can handle %
//fun <T> List<T>.ty() : T
//    where T: CharSequence, T: Appendable {
//    Here All can be interface, with at max only one class allowed , WHY ? Need to debug
//}



fun main() {

    val elements = ('a'..'z').toList()
    println(elements.takeLastFour())  // Here you do not need to provide the <Char> type param, compiler infers it

    println((1..10).toList().penultimate)
    println(elements.penultimate)

    (1..10).toList().xyz()  // This will work below line will not
//    ('a'..'z').toList().xyz()
}