import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Integer.parseInt

fun main() {
    task1()
    task2()
}

fun task1() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    var sum = 0;
    reader.lines().forEach {

        var isOK=true
        var m = Regex("\\d+").find(it)
        var index= parseInt(m?.value)
        val game = m?.range?.last?.plus(2)?.let { it1 -> it.substring(it1) }
        val gameSets = game?.split(";")
        if (gameSets != null) {
            for (set in gameSets) {
                var redCount = 0
                var blueCount = 0
                var greenCount = 0
                m = Regex("\\d+").find(set)
                while (m != null) {
                    if (set[m.range.last.plus(2)] == 'b') {
                            blueCount = parseInt(m.value)
                    }
                    if (set[m.range.last.plus(2)] == 'r') {
                            redCount = parseInt(m.value)
                    }
                    if (set[m.range.last.plus(2)] == 'g') {
                            greenCount = parseInt(m.value)
                    }
                    m = m.next()
                }
                isOK=(redCount<=12 && greenCount<=13 && blueCount<=14)
                if(!isOK) break
            }
        }
        if(isOK)
        sum += index
    }
    println(sum)
}

fun task2() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    var sum = 0;
    reader.lines().forEach {
        var redCount = 1
        var blueCount = 1
        var greenCount = 1
        var m = Regex("\\d+").find(it)
        val game = m?.range?.last?.plus(2)?.let { it1 -> it.substring(it1) }
        val gameSets = game?.split(";")
        if (gameSets != null) {
            for (set in gameSets) {
                m = Regex("\\d+").find(set)
                while (m != null) {
                    if (set[m.range.last.plus(2)] == 'b') {
                        if (parseInt(m.value) > blueCount)
                            blueCount = parseInt(m.value)
                    }
                    if (set[m.range.last.plus(2)] == 'r') {
                        if (parseInt(m.value) > redCount)
                            redCount = parseInt(m.value)
                    }
                    if (set[m.range.last.plus(2)] == 'g') {
                        if (parseInt(m.value) > greenCount)
                            greenCount = parseInt(m.value)
                    }
                    m = m.next()
                }
            }
        }
        sum += (redCount * blueCount * greenCount)
    }
    println(sum)
}
