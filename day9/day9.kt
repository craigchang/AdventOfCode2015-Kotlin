package day9

import com.marcinmoskala.math.permutations
import util.readFileByLines

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFileByLines(fileName = args[0])
    val (locations, locationMap) = createMap(input)

    val allRoutes = locations.permutations()
    val distances = mutableListOf<Int>()

    for (r in allRoutes) {
        var distance = 0
        for (l in 0 until r.count()-1) {
            distance += locationMap["${r[l]}|${r[l+1]}"]!!
        }
        distances.add(distance)
    }

    // part1
    println( distances.min() )

    // part2
    println( distances.max() )
}

private fun createMap(input: List<String>): Pair<MutableSet<String>, MutableMap<String, Int>> {
    val locationSet = mutableSetOf<String>()
    val locationMap = mutableMapOf<String, Int>()

    for (l in input) {
        val (loc1, loc2, dist) = l.split("( to | = )".toRegex())
        locationSet.addAll(listOf(loc1,loc2))
        locationMap["${loc1}|${loc2}"] = dist.toInt()
        locationMap["${loc2}|${loc1}"] = dist.toInt() // store the reverse direction
    }
    return Pair(locationSet, locationMap)
}
