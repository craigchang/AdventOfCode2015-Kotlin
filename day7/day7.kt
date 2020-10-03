package day7

import util.readFileByLines

class Expression(op: String, arg1: String, arg2: String) {
    var op : String = op ?: ""
    var arg1 : String = arg1 ?: ""
    var arg2 : String = arg2 ?: ""
}

fun executeExpression(f: String, v1: Int, v2: Int): Int {
    fun AND(a: Int, b: Int) = a and b
    fun OR(a: Int, b: Int) = a or b
    fun LSHIFT(a: Int, b: Int) = a shl b
    fun RSHIFT(a: Int, b: Int) = a shr b
    fun NOT(a: Int) = a.inv()

    return when(f) {
        "AND" -> AND(v1,v2)
        "OR" -> OR(v1,v2)
        "LSHIFT" -> LSHIFT(v1,v2)
        "RSHIFT" -> RSHIFT(v1,v2)
        "NOT" -> NOT(v1)
        else -> v1
    }
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Provide a file input.")
        return
    }

    val input = readFileByLines(fileName = args[0])
    var signalMap : MutableMap<String, Expression> = createMap(input)

    // part 1
    var signalA = executeWire("a", signalMap)
    println(signalA)

    // part 2
    signalMap = createMap(input) // reset signalMap
    signalMap["b"] = Expression("", signalA.toString(), "") // set signalA to b
    signalA = executeWire("a", signalMap)
    println(signalA)
}

fun executeWire(key: String, signalMap: MutableMap<String, Expression>): Int {
    var expr: Expression = signalMap[key]!!

    if (expr.op == "done") { // already calculated to a number
        return expr.arg1.toInt()
    } else if (expr.op == "") { // assignment
        signalMap[key] = Expression("done", getValue(expr.arg1, signalMap).toString(), "")
    } else { // operations
        var arg1 = getValue(expr.arg1, signalMap)
        var arg2 = if (expr.arg2 != "") getValue(expr.arg2, signalMap) else 0
        signalMap[key] = Expression("done", executeExpression(expr.op, arg1, arg2).toString(), "")
    }

    return getValue(key, signalMap)
}

fun getValue(arg: String, signalMap: MutableMap<String, Expression>): Int =
        if (arg.matches("-?\\d+(\\.\\d+)?".toRegex())) arg.toInt() else executeWire(arg, signalMap)

fun createMap(input: List<String>): MutableMap<String, Expression> {
    var signalMap = mutableMapOf<String, Expression>()
    for (l in input) {
        var op = "[A-Z]+".toRegex().find(l)?.value ?: ""
        var tokens = l.split(" -> ")
        var args = "[a-z0-9]+".toRegex().findAll(tokens[0]).toList().map { it.value }
        var arg1 = args[0]
        var arg2 = if (op != "NOT" && op != "") args[1] else ""

        signalMap[tokens[1]] = Expression(op, arg1, arg2)
    }
    return signalMap
}

fun printMap(signalMap: Map<String, Expression>) {
    println("${signalMap.count()}")
    for ((k, v) in signalMap) {
        println("${k}:${v.op},${v.arg1},${v.arg2}")
    }
}