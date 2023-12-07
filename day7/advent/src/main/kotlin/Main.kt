import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    var lines = reader.readLines()
    task2(lines.toMutableList())

}

fun task2(lines: MutableList<String>) {

    var combinations = lines.map { it.split(" ") }.toMutableList()
    val sortedCombination = combinations.sortedWith(
        compareBy(
            { combinationPower(it[0]) },
            { getCardPower(it[0][0]) },
            { getCardPower(it[0][1]) },
            { getCardPower(it[0][2]) },
            { getCardPower(it[0][3]) },
            { getCardPower(it[0][4]) },
        )
    )
    var sum = 0
    for (i in 0..sortedCombination.size - 1) {
        println(
            sortedCombination[i][0] + " " + sortedCombination[i][1] + " " + combinationPower(sortedCombination[i][0]) + " " + i
        )
        sum += sortedCombination[i][1].toInt() * (i + 1)
    }
    println(sum)
}
fun combinationPower(s: String): Long {
    var power = 0L
    val map = countSameCharacters(s).toList().sortedByDescending { it.second }.toMap().toMutableMap()
    var jokerCount = 0
    if (map.containsKey('J')) {
        jokerCount = map['J']!!
        if (jokerCount != 5)
            map['J'] = 0
    }
    if (map.size > 1) {
        val maxEntry = map.maxBy { it.value }
        map[maxEntry.key] = maxEntry.value + jokerCount
    }
    for (v in map) {
        if (v.value == 5)
            power += 9
        if (v.value == 4)
            power += 8
        if (v.value == 3) {
            power += 4
        }
        if (v.value == 2) {
            power += 1
        }
    }
    return power
}

fun getCardPower(c: Char): Int {

    return if (c == 'A') 14
    else if (c == 'J') 1
    else if (c == 'K') 13
    else if (c == 'Q') 12
    else if (c == 'T') 10
    else {
        return c.toString().toInt()
    }
}
fun countSameCharacters(input: String): Map<Char, Int> {
    val charCount = mutableMapOf<Char, Int>()

    for (char in input) {
        charCount[char] = charCount.getOrDefault(char, 0) + 1
    }

    return charCount
}



