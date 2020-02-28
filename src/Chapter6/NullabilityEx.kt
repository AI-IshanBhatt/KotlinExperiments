package Chapter6

import java.lang.IllegalArgumentException

// This function will not allow null to be passed as argument.
// The param is String, the compiler forces that you can not pass an argument containing null
// This gives you the guarantee that the strLen function will never throw a NullPointerException at runtime.
fun strLen(s: String) = s.length

// THis means you can pass String or null, The following function will not work because we haven't performed any nullcheck
// And started using the s variable
// val x: String? = null
// var y: String = x  This won't work either, Type mismatch: inferred type is String? but String was expected
//fun strLenSafe(s: String?) = s.length

fun printAllCaps(s: String?) {
    s?.toUpperCase()  // Safe call operator "?.", It calls the function if s is not null else returns null
    println(s)
}

class ManagedEmployee(val name: String, val manager: ManagedEmployee?)  // Be assured that default constructor is generated

fun managerName(employee: ManagedEmployee) = employee.manager?.name

// Chaining the safe call "?." operator, so that only last null check is needed not the ladder in Java
// From the following 3 classes we will try to find person's country
class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?) {
    override fun equals(other: Any?): Boolean {
        // as? Person will check if other is a Person if not then it will be null, from there Elvis will pick it up
        val otherPerson = other as? Person ?: return false  // Elvis operator with return how cool
        return otherPerson.name == name

    }
}

fun Person.countryName(): String {
    val country = this.company?.address?.country
//    return if (country != null) country else "Unknown" NAIVE IMPLEMENTATION, USE ELVIS
    return country ?: "UNKNOWN"  // If country is null, then return UNKNOWN or return country name
}

// Using throw in right hand of elvis operator
fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No Address")  // If you get null, you will throw exception
    with(address) {
        println(streetAddress)  // I hope you remember it is actually calling address.streetAddress
        println("$zipCode $city , $country")
    }

}

// Combine safe-call operator and elvis operator to be more cool
fun strLenSafe(s: String?) = s?.length ?: 0

fun ignoreNulls(s: String?) {
    val sNotNull = s !!  // I hope you get that thi is just a syntactic sugar, you can do the same with s ?: throw NPE
                         // Also, this exception is at Runtime and the exception will be thrown AT LINE NUMBER where s !! is there
                         // Unlike JAVA where the exception comes at the LINE NUMBER of usage
    println(sNotNull.length)
}

fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun String?.isNullOrBlank() = this?.isBlank() ?: true  // Previously it was this == null || this.isBlank()

// Here T is inferred as Any? ,
// If you wanted the type param to be non-null define it as <T: Any>, in that cast printHashCode(null) won't work
fun <T> printHashCode(t: T) {
    println(t?.hashCode())  // Need to use a safe call because t might be null
}

fun main() {

//    strLen(null) , this call will not be allowed. Null can not be a value of a non-null type String
//    val x = null , strLen(x) will not work either as the compiler knows that it is null and function wants String
    printAllCaps("absd")
    printAllCaps(null)

    val ceo = ManagedEmployee("Da Boss", null)
    val developer = ManagedEmployee("Bob Smith", ceo)

    println(managerName(developer))
    println(managerName(ceo))

    println("CHAINING SAFE OPERATOR")
    val person = Person("Dmitry", null)
    println(person.countryName())

    val address = Address("Maitry", 345, "ADI", "Ind")
    val company = Company("Tatv", address)
    val anotherPerson = Person("AB", company)

    println(anotherPerson.countryName())
    println("Safe length of null ${strLenSafe(null)}")
    println("Safe length of Ishan ${strLenSafe("Ishan")}")

    printShippingLabel(anotherPerson)
//    printShippingLabel(person)

    println(person == anotherPerson)
    println(person.equals(43))  // Need to do with equals and rather than giving some exceptions, it returns false
    println(person == person)

    println("USING THE LET KEYWORD")
    var email: String? = "ishan@bhatt.com"
    email?.let { sendEmailTo(it) }  // It turns email into it which can be used in {}
    email = null
    email?.let { sendEmailTo(it) }  // Here the email is null so nothing will happen, lambda will not be executed

    // Our extension function was defined on nullable type String? , So it can be called on either of them
    println(" ".isNullOrBlank())
    println(null.isNullOrBlank())

    printHashCode(42)
    printHashCode(null)


}