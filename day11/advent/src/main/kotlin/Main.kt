import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.math.abs

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    val lines = reader.readLines()
    findShortestPath(lines)
}


fun findShortestPath(input: List<String>) {
    val rowsWithoutGalaxies = mutableListOf<Int>()
    val columnsWithoutGalaxies = mutableListOf<Int>()

    for (i in input.indices) {
        if (!input[i].contains('#')) {
            rowsWithoutGalaxies.add(i)
        }
    }
    for (j in input[0].indices) {
        var hasGalaxy = false
        for (i in input.indices) {
            if (input[i][j] == '#') {
                hasGalaxy = true
                break
            }
        }
        if (!hasGalaxy) {
            columnsWithoutGalaxies.add(j)
        }
    }

    val galaxyLocations = mutableListOf<Pair<Int, Int>>()
    for (i in input.indices) {
        for (j in input[0].indices) {
            if (input[i][j] == '#') {
                galaxyLocations.add(Pair(i, j))
            }
        }
    }
    var sum1 = 0L
    var sum2 = 0L
    var multiplyCoff = 0

    for (i in 0 until galaxyLocations.size) {
        for (j in i + 1 until galaxyLocations.size) {
            for (k in galaxyLocations[i].first..galaxyLocations[j].first) {
                if (rowsWithoutGalaxies.contains(k)) multiplyCoff++
            }
            for (k in galaxyLocations[i].second.coerceAtMost(galaxyLocations[j].second)..galaxyLocations[i].second.coerceAtLeast(
                galaxyLocations[j].second
            )) {
                if (columnsWithoutGalaxies.contains(k)) multiplyCoff++
            }
            val path1 =
                galaxyLocations[j].first - galaxyLocations[i].first + abs(galaxyLocations[i].second - galaxyLocations[j].second) + multiplyCoff
            val path2 =
                galaxyLocations[j].first - galaxyLocations[i].first + abs(galaxyLocations[i].second - galaxyLocations[j].second) + multiplyCoff * 1000000 - multiplyCoff
            sum1 += path1
            multiplyCoff = 0
            sum2 += path2
        }
    }
    println("task1:$sum1")
    println("task2:$sum2")

}

