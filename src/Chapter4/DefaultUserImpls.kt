package Chapter4

class PrivateUser(override val nickName: String) : DefaultUser  // Primary Constructor Property

// This property doesn’t have a backing field to store its value; it has only a getter
// that calculates a nickname from the email on every invocation.
class SubscribingUser(val email: String) : DefaultUser {

    override val nickName: String
        get() = email.substringBefore('@')  // Using custom getter
}

// the property in FacebookUser has a backing field that stores the data computed during the class initialization.
class FacebookUser(private val accountId: Int): DefaultUser {
    override val nickName = "USER-$accountId"
}

fun main() {

    println(PrivateUser("test@kotlinlang.org").nickName)
    println(SubscribingUser("test@kotlinlang.org").nickName)
    println(FacebookUser(187).nickName)

}