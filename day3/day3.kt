package day3

import util.readFile

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFile(fileName = args[0])

    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

fun direction(dir: Char, cX: Int, cY: Int): List<Int> {
    var x = cX
    var y = cY

    when (dir) {
        '>' -> x++
        '<' -> x--
        '^' -> y++
        'v' -> y--
    }

    return listOf(x,y)
}

fun part1(input: String): Int {
    var set = mutableSetOf(listOf(0,0))
    var cX = 0
    var cY = 0

    for (move in input) {
        var (x,y) = direction(move, cX, cY)

        cX = x
        cY = y

        var coord = listOf(cX,cY)

        if (!set.contains(coord))
            set.add(coord)
    }

    return set.count()
}

fun part2(input: String): Int {
    var set = mutableSetOf(listOf(0,0))
    var santaX = 0 // santa x,y
    var santaY = 0
    var robotX = 0 // robot x,y
    var robotY = 0
    var turn = false // false is santa, 1 is robot

    for (move in input) {
        if (!turn) {
            var (x,y) = direction(move, santaX, santaY)
            santaX = x
            santaY = y

            var santaCoord = listOf(santaX,santaY)

            if (!set.contains(santaCoord))
                set.add(santaCoord)
        } else {
            var (x2,y2) = direction(move, robotX, robotY)
            robotX = x2
            robotY = y2

            var robotCoord = listOf(robotX,robotY)

            if (!set.contains(robotCoord))
                set.add(robotCoord)
        }

        turn = !turn
    }

    return set.count()
}