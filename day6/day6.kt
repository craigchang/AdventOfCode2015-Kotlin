package day6

import util.readFileByLines

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFileByLines(fileName = args[0])
    part1(input)
    part2(input)

}

fun calcBrightness(grid: Array<Array<Int>>): Int {
    var count = 0
    for (i in 0..999) {
        for (j in 0..999) {
            count += grid[i][j]
        }
    }
    return count
}

fun part1(input: List<String>) {
    var grid = Array(1000) { Array(1000) {0} }

    for (l in input) {
        var coords = "\\d*,\\d*".toRegex().findAll(l).toList()
        val (sX, sY) = coords[0].value.split(",").map { it.toInt() }
        val (eX, eY) = coords[1].value.split(",").map { it.toInt() }

        when {
            l.startsWith("turn on") -> {
                for (i in sX..eX) {
                    for (j in sY..eY) {
                        grid[i][j] = 1
                    }
                }
            }
            l.startsWith("turn off") -> {
                for (i in sX..eX) {
                    for (j in sY..eY) {
                        grid[i][j] = 0
                    }
                }
            }
            else -> { // toggle
                for (i in sX..eX) {
                    for (j in sY..eY) {
                        grid[i][j] = if (grid[i][j] == 0) 1 else 0
                    }
                }
            }
        }

    }

    var count = calcBrightness(grid)
    println(count)
}

fun part2(input: List<String>) {
    var grid = Array(1000) { Array(1000) {0} }

    for (l in input) {
        var coords = "\\d*,\\d*".toRegex().findAll(l).toList()
        val (sX, sY) = coords[0].value.split(",").map { it.toInt() }
        val (eX, eY) = coords[1].value.split(",").map { it.toInt() }

        when {
            l.startsWith("turn on") -> {
                for (i in sX..eX) {
                    for (j in sY..eY) {
                        grid[i][j] += 1
                    }
                }
            }
            l.startsWith("turn off") -> {
                for (i in sX..eX) {
                    for (j in sY..eY) {
                        grid[i][j] += if (grid[i][j] == 0) 0 else -1
                    }
                }
            }
            else -> { // toggle
                for (i in sX..eX) {
                    for (j in sY..eY) {
                        grid[i][j] += 2
                    }
                }
            }
        }

    }

    var count = calcBrightness(grid)
    println(count)
}

