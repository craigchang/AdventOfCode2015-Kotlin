package day8

import util.readFileByLines

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFileByLines(fileName = args[0])

    // part 1
    println(calculateDifference1(input))

    // part 2
    println(calculateDifference2(input))
}

fun calculateDifference1(input: List<String>): Int {
    var countUnencoded = 0
    var countEncoded = 0

    for (l in input) {
        countUnencoded += l.count()

        var str = l.removeSurrounding("\"")
        str = str.replace("\\\\{2}".toRegex(), ".")
        str = str.replace("\\\\\"".toRegex(), ".")
        str = str.replace("\\\\x[a-fA-Z0-9]{2}".toRegex(), ".")

        countEncoded += str.count()
    }

    return countUnencoded - countEncoded
}

fun calculateDifference2(input: List<String>): Int {
    var countUnencoded = 0
    var countEncoded = 0

    for (l in input) {
        countUnencoded += l.count()

        var str = l
        str = str.replace("\\", "\\\\")
        str = str.replace("\"", "\\\"")
        str = "\"${str}\""

        countEncoded += str.count()
    }

    return countEncoded - countUnencoded
}