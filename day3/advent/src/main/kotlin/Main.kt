import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Integer.parseInt

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
    for (i in 0..list.size - 1) {
        var m = Regex("\\d+").find(list.get(i))
        var isAdjacentNum = false
        while (m != null) {
            for (j in m.range.first - 1..m.range.last + 1) {
                for (k in i - 1..i + 1) {
                    if (isAdjacentNum) continue
                    if (k > 0 && j > 0 && k < list.size && j < list[k].length)
                        isAdjacentNum = checkNeighbor(j, list[k])
                }
            }
            if (isAdjacentNum) {
                sum += parseInt(m.value)
            }
            isAdjacentNum = false
            m = m.next()
        }
    }
    println(sum)
}

fun checkNeighbor(i: Int, s: String): Boolean {
    return s[i] != '.' && !s[i].isDigit()
}

fun task2(list: ArrayList<String>) {
    var sum = 0
    var num = "0"
    for (i in 0..list.size - 1) {
        var m = Regex("\\*").find(list[i])
        while (m != null) {
            for (k in i - 1..i + 1) {
                for (j in m.range.first - 1..m.range.last + 1) {
                    if (k >= 0 && j >= 0 && k < list.size && j < list[k].length) {
                        if (list[k][j].isDigit()) {
                            val temp = finNum(j, list[k])
                            if (num != temp) {
                                sum += (parseInt(num) * parseInt(temp))
                            }
                            num = temp

                        }
                    }
                }
            }
            m = m.next()
            num = "0"
        }
    }
    println(sum)
}

fun finNum(i: Int, s: String): String {
    var index = i
    var num = ""
    while (index - 1 >= 0 && s[index - 1].isDigit()) {
        index--
    }
    while (index < s.length && s[index].isDigit()) {
        num += s[index]
        index++
    }
    return num
}

