import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    val list = ArrayList<String>();
    reader.lines().forEach {
        list.add(it)
    }
    task1(list)
    task2(list)
}

fun task1(list: ArrayList<String>) {

    var sum = 0
    for (line in list) {
        val winNumbers = Regex("\\d+").findAll(line.substring(line.indexOf(':'), line.indexOf('|')))
        val myNumbers = Regex("\\d+").findAll(line.substring(line.indexOf('|')))
        var matchNumbersCount = -1
        for (n1 in winNumbers) {
            for (n2 in myNumbers) {
                if (n1.value.equals(n2.value)) {
                    matchNumbersCount++
                }
            }
        }
        if (matchNumbersCount >= 0)
            sum += Math.pow(2.0, matchNumbersCount.toDouble()).toInt()

    }
    println(sum)
}


fun task2(list: ArrayList<String>) {
    var sum = 0
    val cardList = IntArray(list.size) { 1 }
    for (line in list) {
        val winNumbers = Regex("\\d+").findAll(line.substring(line.indexOf(':'), line.indexOf('|')))
        val myNumbers = Regex("\\d+").findAll(line.substring(line.indexOf('|')))
        var matchNumbersCount = 0
        for (n1 in winNumbers) {
            for (n2 in myNumbers) {
                if (n1.value.equals(n2.value)) {
                    for (i in 0..cardList[list.indexOf(line)] - 1)
                        cardList[list.indexOf(line) + matchNumbersCount + 1]++
                    matchNumbersCount++
                }
            }
        }
    }
    for (n in cardList) {
        sum += n
    }
    println(sum)
}


