package Chapter5

data class Dish(val name: String, val isVegetarian: Boolean, val calories: Int, val type: TYPE)
enum class CaloricLevel { DIET, NORMAL, FAT }

// https://programmer.help/blogs/functions-in-kotlin.html
// groupBy vs groupingBy vs groupByTo
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
    // Total calories if you eat everything
    val totalCalories = menu.sumBy { it.calories }  // sumBy also accepts a (Object) -> Int/Double , In JAVA it was summingInt
    println("TOTAL CALORIES IN MENU $totalCalories")

    // Try to get all menu items in one big string,
    // joinToString has last parameter transform (That is to transform type T to string)
    // As it is a last parameter it can be outside of the function call in {}, Now everything seems okay
    val allNames = menu.joinToString(separator = ",") { it.name }  // menu.map { it.name }.joinToString(separator = ",")
    println(allNames)

    println("-----------------------------GROUPING------------------------------------")
    val dishByType = menu.groupBy{it.type}
    println(dishByType)

//    menu.groupBy { dish: Dish -> {if (dish.calories < 400) CaloricLevel.DIET
//        else if (dish.calories < 700) CaloricLevel.NORMAL
//        else CaloricLevel.FAT
//    } }

    // groupBy has keySelector the idea of that is it shoild be able to select the key
    // So either it can be normal it.attribute or some complex structure using when, in
    val dishesByCaloricLevel = menu.groupBy { when(it.calories) {
        in 0..400 -> CaloricLevel.DIET
        in 400..700 -> CaloricLevel.NORMAL
        else -> CaloricLevel.FAT
        }
    }
    println(dishesByCaloricLevel)

    println("----------------------------MULTI LEVEL GROUPING---------------------------")

    val dishByTypeCaloricLevel = menu.groupBy { it.type }.mapValues { ld ->
        ld.value.groupBy { when(it.calories) {
            in 0..400 -> CaloricLevel.DIET
            in 400..700 -> CaloricLevel.NORMAL
            else -> CaloricLevel.FAT
            }
        }
    }
    println(dishByTypeCaloricLevel)

    println("-------------SUM -------------- COUNT -----------------MAX ------------------")
    val typeSum = menu.groupBy { it.type }.mapValues { it.value.sumBy { it.calories } }
    println(typeSum)

    // The valueTransform you are thinking about transforms the values as whole
    // So it transforms "Ishan" -> 5, "Gunjan" ->6 , I doubt whether it will be able to aggregate that
    // Putting a count at the end would not help as it will count the hashMap's length not individual
    // groupBy is overloaded so both variant works perfect
    val typeCount = menu.groupBy(keySelector = {dish: Dish ->  dish.type}).mapValues { it.value.size }
    println(typeCount)

    val typeWiseMaxCalorie = menu.groupBy { it.type }.mapValues { it.value.maxBy { it.calories } }
    println(typeWiseMaxCalorie)

    val typeWithMaxOptions = menu.groupBy { it.type }.mapValues { it.value.size }.maxBy { it.value }?.key
    println(typeWithMaxOptions)

}