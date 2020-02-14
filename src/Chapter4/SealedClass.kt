package Chapter4

/*
* Remember when you have to type every-time you used when.
* Especially during that Expr and it's implementation which you were trying to put in when cases.
* sealed classes avoid that.
* */

// All the direct subclasses must be nested in the superclass
sealed class Expr {
    class Num(val value: Int) : Expr()  // Hopefully you remember that it is a class so constructor is needed
    class Sum(val left: Expr, val right: Expr) : Expr()
}


// When you use when with sealed classes and add a new subclass, the when expression
// returning a value fails to compile, which points you to the code that must be changed.
fun eval(e: Expr): Int =
    when(e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }

// Also note that the arguments provided in class are the ones which will be used in default constructor
// With the help of default values and named parameters, different versions of constructor is not advisable
open class User(val nickname: String,
           val isSubscribed: Boolean = true)

// If your class has a superclass, the primary constructor also needs to initialize the superclass.
class TwitterUser(nickname: String) : User(nickname) {}

// You can define only private constructor, if you don't want it to be instantiated from outside
// Kind of singleton in Java
class Secrative private constructor() {}

fun main() {
    println(eval(Expr.Sum(Expr.Num(1), Expr.Num(7))))

    val alice = User("Alice")
    val bob = User("Bob", false)

    println(alice.isSubscribed)
    println(bob.isSubscribed)

    // Here the constructor of User will be called directly with Mark, In Java you need to do it with super
    // The reason it works because 1 argument primary constructor that User provides
    val mark = TwitterUser("Mark")
    println(mark.isSubscribed)
}