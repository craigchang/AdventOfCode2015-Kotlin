package day2

import util.readFileByLines
import kotlin.math.min

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFileByLines(fileName = args[0])
    var boxes = createBoxesList(input)

    println("Part 1: ${part1(boxes)}")
    println("Part 2: ${part2(boxes)}")
}

fun createBoxesList(input: List<String>): MutableList<List<Int>> {
    val boxes : MutableList<List<Int>> = mutableListOf()
    for (line in input) {
        val (l, w, h) = line.split("x")
        boxes.add(listOf(l.toInt(),w.toInt(),h.toInt()))
    }
    return boxes
}

fun part1(boxes: MutableList<List<Int>>): Int {
    var total = 0
    for (box in boxes) {
        var (l, w, h) = box
        var (a1, a2, a3) = listOf(l * w, w * h, h * l)
        total += (2 * a1 + 2 * a2 + 2 * a3) + minOf(a1, a2, a3)
    }
    return total
}

fun part2(boxes: MutableList<List<Int>>): Int {
    var total = 0
    for (box in boxes) {
        var (l, w, h) = box
        var (s1, s2, s3) = box.sorted()
        total += (l * w * h) + (2 * s1 + 2 * s2)
    }
    return total
}