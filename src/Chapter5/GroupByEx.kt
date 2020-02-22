package Chapter5

data class Dish(val name: String, val isVegetarian: Boolean, val calories: Int, val type: TYPE)

fun main() {
    
    // Ctrl + R is for replace
    val menu = listOf(
        Dish("pork", false, 800, TYPE.MEAT),
        Dish("beef", false, 700, TYPE.MEAT),
        Dish("chicken", false, 400, TYPE.MEAT),
        Dish("french fries", true, 530, TYPE.OTHER),
        Dish("rice", true, 350, TYPE.OTHER),
        Dish("season fruit", true, 120, TYPE.OTHER),
        Dish("pizza", true, 550, TYPE.OTHER),
        Dish("prawns", false, 300, TYPE.FISH),
        Dish("salmon", false, 450, TYPE.FISH)
    )

    val anyVeg = menu.any { it.isVegetarian }
    println("Status of VEG DIshes $anyVeg")

    val allLowCal = menu.all { it.calories < 1000 }
    println("Are all dishes low calorie $allLowCal")

    val oneToTen = 1..10
    val divisibleSquare = oneToTen.map { it*it }.first { it%8==0 }  // Even Kotlin's first has predicate, In java we needed to do filter and first
    println("FIRST SQUARE WHICH IS DIVISIBLE BY 8 -> $divisibleSquare")

//    val x = oneToTen.first {(it*it)%8 == 0}
    

}