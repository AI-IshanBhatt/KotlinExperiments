package Chapter4

// by keyword helps you create decorator pattern, vastly different from python decorator
// Most of the inheritance stuff is being handled by the open class , abstraction etc.
// Sometimes you want to override classes which are not meant to be overridden () like Collection, Thread etc
// The essence of the pattern is that a new class is created, implementing the same
// interface as the original class and storing the instance of the original class as a field.
// Methods in which the behavior of the original class doesn't need to be modified are
// forwarded to the original class instance.
// This requires a huge amount of boilerplate, but NOT IN KOTLIN
// For functions it was fun <T>

class CountingSet<T> (val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    // Here innerSet is an argument specified only for delegation, It is not a class attribute.
    // Delegates tge MutableCollection implementation to innerSet (which is a hashSet)

    var objectsAdded = 0

    // Do not delegate but override it
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }

}
fun main() {

}