package day5

import util.readFileByLines

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFileByLines(fileName = args[0])

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun part1(input: List<String>): Int {
    var count = 0
    val vowels = "(.*[aeiou]){3}".toRegex() // at least 3 vowels
    val twice = "([a-z])\\1".toRegex() // at least 1 letter twice in a row
    val blacklist = "^((?!ab|cd|pq|xy).)*\$".toRegex() // exclude ab, cd, pq, xy

    for (line in input) {
        if (vowels.find(line) != null && twice.find(line) != null && blacklist.find(line) != null) {
            count++
        }
    }

    return count
}

fun part2(input: List<String>): Int {
    var count = 0
    val pair = "(..).*\\1".toRegex() // at least a pair of any two letters
    val twice = "(.).\\1".toRegex() // at least one letter that repeats with one letter in between

    for (line in input) {
        if (pair.find(line) != null && twice.find(line) != null) {
            count++
        }
    }

    return count
}