package Chapter4

/*
* Just like Java8, Interface can have default methods but you do not have to explicitely declare them default
* It can not contain any state, so you can't give any attributes there.
* */

interface Clickable {
    fun click()
    fun showoff() = println("I am clickable")
}
