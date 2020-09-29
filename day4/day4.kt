package day4

import java.security.MessageDigest

fun main(args: Array<String>) {
    println("Part 1: ${part1("yzbqklnj")}")
    println("Part 2: ${part2("yzbqklnj")}")
}

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun part1(key: String): Int {
    var num = -1
    var hex = ""

    do {
        num++
        hex = (key + num.toString()).toMD5()
    } while(!hex.startsWith("00000"))

    return num
}

fun part2(key: String): Int {
    var num = -1
    var hex = ""

    do {
        num++
        hex = (key + num.toString()).toMD5()
    } while(!hex.startsWith("000000"))

    return num
}