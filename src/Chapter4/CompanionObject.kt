package Chapter4


class CompanionUser private constructor(val nickName: String) {
    companion object {
        fun newSubscribingUnser(email: String) = User(email.substringBefore('@'))

        fun facebookUser(accountId: Int) = User("ACCOUNT $accountId")
    }
}

// Companion object as implementing interfaces, this one seems cool
// From jsonstring it will create an object of parameterized type T
interface JSONFactory<T> {
    fun fromJson(jsonString: String) : T
}

interface XMLFactory<T> {
    fun fromXML(xmlString: String): T
}

class AnotherPerson(val name: String, val address: String) {

    // How cool is this, you can create AnotherPerson object from either JSON or XML or any other representation
    // Just make companion object implement it, Also see the cool use of typed parameter
    companion object : JSONFactory<AnotherPerson>, XMLFactory<AnotherPerson> {
        override fun fromJson(jsonString: String): AnotherPerson =
            AnotherPerson(jsonString.substringBefore('.'), jsonString.substringAfter('.'))

        override fun fromXML(xmlString: String): AnotherPerson =
            AnotherPerson(xmlString.substringBefore('>'), xmlString.substringAfter('>'))
    }
}

fun main() {

    // The factory pattern in action, the best part is that the function can have names the way it is intended to use
    // Compare it with the implementations in DefaultUserImpls.kt , this one is very intuitive
    val subscribingUser = CompanionUser.newSubscribingUnser("ishan@bhatt.com")
    val facebookUser = CompanionUser.facebookUser(23)

    println(subscribingUser.nickname)
    println(facebookUser.nickname)

    val anotherPerson = AnotherPerson.fromJson("Ishan.KedarNath")
    println("${anotherPerson.name} -> ${anotherPerson.address}")

    val gunjanPerson = AnotherPerson.fromXML("GUNJAN>MAITRY")
    println("${gunjanPerson.name} -> ${gunjanPerson.address}")
}