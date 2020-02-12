package Chapter3



/*
* This file explains the usage and patterns for local functions and using them as extension function
* You can assume local functions as function within function of Python
* */

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {

    // Don't worry about the use field used in IllegalAE, validate has access to user argument of outer function
    // local functions have access to all parameters and variables of the enclosing function. (closure?)
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty())
            throw IllegalArgumentException("Can't save user ${user.id} : empty $fieldName")
    }

    validate(user.name, "name")
    validate(user.address, "Address")

    println("User Saved of ID ${user.id}")
}

fun User.saveUserObject() = saveUser(this)

fun main() {
    val user1 = User(1, "A", "B")
//    val user2 = User(2, "", "")

    // It was there when saveUser was not an extension function
//    saveUser(user1)
//    saveUser(user2)
    user1.saveUserObject()
}