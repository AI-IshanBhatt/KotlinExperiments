package Chapter4

// The interface doesn’t specify whether the value should be
//stored in a backing field or obtained through a getter. Therefore, the interface itself
// doesn’t contain any state, and only classes implementing the interface may store the value if they need to.

interface DefaultUser {
    val nickName: String  // This means classes implementing the User interface need to provide a way to obtain
                          // the value of nickname.

    // an interface can contain properties with getters and setters, as long as they don’t reference a backing field.
    // A backing field would require storing state in an interface, which is not allowed
    val anotherName: String
        get() = "Another Name"

}