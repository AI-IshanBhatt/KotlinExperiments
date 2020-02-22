package Chapter5

fun main() {
    val list = listOf(1,2,3,4,5)
    println(list.filter { it%2 == 0 })

    val people = listOf(NewPerson("Ishan", 31), NewPerson("Gunjan", 29),
        NewPerson("Vanita", 65), NewPerson("Jiya", 10)
    )
    println(people.filter { it.age > 30 })  // it filters for transform , we use map

    println(list.map { it*it })
    println(people.map {it.name})

    // Name of all aged people
    println("---------------------------------------------------------------------------------")
    println(people.filter { it.age > 30 }.map { it.name })

    println("FIND PERSON WITH MAX AGE")
    println(people.maxBy { it.age }?.name)  // ? has to be there because maxBy can endup returning null so null check is mandatory here

    val canBeInClub30 = {p: NewPerson -> p.age<=30}
    println(people.filter(canBeInClub30))  // See how smart the compiler is it knows that this predicate can go in filter
                                            // As it's signature is NewPerson->boolean so it's perfect for people with filter

    println(people.all(canBeInClub30))
    println(people.any(canBeInClub30))
    println(people.count(canBeInClub30))
    println(people.find { it.age > 50 })  // All can have predicate and returns something, YOy can use find/firstOrNull
}