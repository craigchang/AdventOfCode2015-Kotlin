package util

import java.io.File

fun readFile(fileName: String): String {
    return File(fileName).readText()
}

fun readFileByLines(fileName: String): List<String> {
    return File(fileName).readLines()
}