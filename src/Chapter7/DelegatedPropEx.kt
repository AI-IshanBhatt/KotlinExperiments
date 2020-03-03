package Chapter7

import kotlin.math.round
import kotlin.reflect.KProperty

class Delegate {
    // Just remember getValue and setValue has exactly this syntax
    // Also, if you want to make it specific , you could have changed thisRef to Temperature
    // But with this, the same delegate class can be used for all, just add the expression in when WOW
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): Double {
        when (thisRef) {
            is Temperature -> return (thisRef.celsius * 9) /5 + 32  // Now this is a statement not expression so it is not complaining about exausting all paths
            else -> return 0.0
        }
        // It is giving some warning that return should be lifted out of when
        // Making it an expression will make it
        /*
            return when(thisRef) {
                is Temperature ->  (thisRef.celsius * 9) /5 + 32
                else -> 0.0
            }
        */
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: Double) {
        when(thisRef) {
            is Temperature -> thisRef.celsius = round((value - 32) * 5 /9)
        }
    }
}

// Assume that this is some complex process to fetch all the Temperature agencies
// we will keep it as lazy property
fun loadTempAgencies(temperature: Temperature): List<String> {
    println("LOADING HEAVY DUTY TEMPERATURE AGENCIES LIST")
    return listOf("ABC", "DEF", "GHI", "JKL")
}

data class Temperature(var celsius: Double) {
    var fahrenheit: Double by Delegate()
    val agencies by lazy { loadTempAgencies(this) }

    // Following has nothing to do with this class but explains important aspect
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }
    val name: String by _attributes  // It is basically saying that Temperature has name property and it's value comes from _attributes["name"]
                                     // If _attributes doesn't have "name" then NoSuchElementException

    // hashMapOf<Double,Double> does not have getValue setValue methods, we may add them as extension function
}

fun main() {
    println("Check if celsius to fahrenheit works")
    val t1 = Temperature(10.0)
    println("CELSIUS is ${t1.celsius} and FAHRENHEIT is ${t1.fahrenheit}")

    t1.fahrenheit = 80.6
    println("AFTER CHANGE CELSIUS is ${t1.celsius} and FAHRENHEIT is ${t1.fahrenheit}")

    println("If I hadn't added following line, loadTempAgencies will never be called")
    println(t1.agencies)

    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")

    for ((attrName, value) in data)
        t1.setAttribute(attrName, value)

    println("NAME ${t1.name} ")
}