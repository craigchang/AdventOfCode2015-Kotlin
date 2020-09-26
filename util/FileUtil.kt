package util

import java.io.File

fun readFile(fileName: String): String {
    return File(fileName).readText()
}