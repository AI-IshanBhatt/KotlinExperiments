package Chapter8

data class SiteVisit (val path: String, val duration: Double, val os: OS)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID}

fun main() {

    val log = listOf (
        SiteVisit("/", 34.0, OS.WINDOWS),
        SiteVisit("/", 22.0, OS.MAC),
        SiteVisit("/login", 12.0, OS.WINDOWS),
        SiteVisit("/signup", 8.0, OS.IOS),
        SiteVisit("/", 16.3, OS.ANDROID)
    )

    val naiveWindowsDuration = log.filter { it.os == OS.WINDOWS }.map { it.duration }.average()
    println("AVERAGE DURATION ON WINDOWS MACHINE $naiveWindowsDuration")

    // Ypu may want similar stats for other as well, I am sure you will not repeat it
    // If you had used map() -> Use this :: style, for map {} , use that it. style
    fun List<SiteVisit>.averageDurationFor(os: OS) = filter { it.os == os }.map(SiteVisit::duration).average()

    println("MAC AVG ${log.averageDurationFor(OS.MAC)}")

    // Let's assume you want average duration for signup page OR mobile devices etc
    fun List<SiteVisit>.genericAverage(predicate: (SiteVisit) -> Boolean) =
        filter(predicate).map(SiteVisit::duration).average()

    println("AVERAGE DURATION FOR MOBILE ${log.genericAverage { it.os in setOf(OS.ANDROID, OS.IOS) }}")
    println("AVERAGE DURATION FOR ROOT PAGE ${log.genericAverage { it.path == "/" }}")

}