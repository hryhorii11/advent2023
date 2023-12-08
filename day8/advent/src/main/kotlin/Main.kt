import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    var lines = reader.readLines()
    task1(lines.toMutableList())
    task2(lines.toMutableList())

}

fun task1(lines: MutableList<String>) {
    val directions = lines[0].toCharArray()
    var i = 0
    var next = ""
    var stepCount = 0
    var isFind = false
    while (!isFind) {
        for (line in lines) {
            val nodes = Regex("\\w\\w\\w").findAll(line).map { it.value }.toList()
            if (nodes.size == 3) {
                val elementNum = if (directions[i] == 'R') 2
                else 1
                if ((next == "" || nodes[0] == next)) {
                    next = nodes[elementNum]
                    i++
                    stepCount++
                    if (nodes[elementNum] == "ZZZ") {
                        isFind = true
                        break
                    }
                }
            }
            if (i == directions.size) i = 0
        }
    }
    println(stepCount)
}

fun task2(lines: MutableList<String>) {
    val directions = lines[0].toCharArray()
    val nodes = lines.drop(2).associate { line ->
        val firstPart = line.substring(0, 3)
        val secondPart1 = Regex("[\\d\\w][\\d\\w][\\d\\w]").findAll(line).toList()[1].value
        val secondPart2 = Regex("[\\d\\w][\\d\\w][\\d\\w]").findAll(line).toList()[2].value
        firstPart to Pair(secondPart1, secondPart2)
    }
    val stepCounts = nodes.keys.filter { it.endsWith("A") }.map {
        var current = it
        var count = 0L
        while (!current.endsWith("Z")) {
            directions.forEach { current = if (it == 'R') nodes[current]!!.second else nodes[current]!!.first }
            count += directions.size
        }
        count
    }
    println(stepCounts.reduce { acc, i -> nsk(acc, i) })

}

fun nsk(a: Long, b: Long): Long {
    return a * b / nsd(a, b).toLong()
}

tailrec fun nsd(a: Long, b: Long): Number {
    return if (b.toInt() == 0) a
    else nsd(b, a % b)
}










