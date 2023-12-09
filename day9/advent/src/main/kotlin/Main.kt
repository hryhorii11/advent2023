import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    var lines = reader.readLines()
    tasks(lines.toMutableList())

}

fun tasks(lines: MutableList<String>) {
    val map = lines.associate {
        val firstPart = lines.indexOf(it)
        val secondPart = mutableListOf<MutableList<Int>>()
        secondPart.add(it.split(" ").map { it.toInt() }.toMutableList())
        firstPart to secondPart
    } as MutableMap<Int, MutableList<MutableList<Int>>>

    for (m in map) {
        var sequence = m.value[0].toList()
        var i = 0
        while (sequence.any { it != 0 }) {
            val differences = mutableListOf<Int>()
            for (j in 1..m.value[i].size - 1)
                differences.add(m.value[i][j] - m.value[i][j - 1])
            m.value.add(differences)
            sequence = differences
            i++
        }
    }
    var sum = 0
    for (m in map) {
        for (i in 0..m.value.size-1) {
            sum+=m.value[i].last()
        }
    }
    println(sum)
    sum=0
    for (m in map) {
        var newVal = 0
        for (i in m.value.size - 2 downTo -1) {
            newVal = m.value[i + 1].first() - newVal
        }
        sum += newVal
    }
    println(sum)
}







