package day1

import util.readFile

fun main(args: Array<String>) {
    val input: String = readFile(fileName = args[0])

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: String): Int {
    var totalSteps = 0

    for(char in input)
        totalSteps += (if (char == '(') 1 else -1)
    return totalSteps
}

fun part2(input: String): Int {
    var totalSteps = 0
    var count = 0

    for(char in input) {
        totalSteps += (if (char == '(') 1 else -1)
        count++
        if (totalSteps < 0)
            break
    }

    return count
}