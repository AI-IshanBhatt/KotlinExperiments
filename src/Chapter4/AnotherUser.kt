package Chapter4

class AnotherUser(val name: String) {  // name surely has a backing field
    var address: String = "unspecified" // Property must be specified here or keep it abstract
        set(value: String) {
            println("Address was changed for $name : $field -> $value")
            field = value  // If you do not provide , compiler will warn Existing backing field is not set by setter
                           // This updates the backing field value, special identifier "field" to access the value of the backing field
        }
}

fun main() {
    val alice = AnotherUser("Alice")
    alice.address = "Elsenheimerstrasse 47, 80687 Muenchen"
}