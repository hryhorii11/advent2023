import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    val lines = reader.readLines()
    day10(lines.toMutableList())

}

fun day10(input: MutableList<String>) {
    val labyrinth = input.map { it.toCharArray() }.toTypedArray()

    fun findStartPosition(): Pair<Int, Int> {
        for (i in labyrinth.indices) {
            for (j in labyrinth[i].indices) {
                if (labyrinth[i][j] == 'S') {
                    return Pair(i, j)
                }
            }
        }
        return Pair(-1, -1)
    }

    val startPosition = findStartPosition()

    val distances = Array(labyrinth.size) { IntArray(labyrinth[0].size) { -1 } }

    fun checkNextStep(distances: Array<IntArray>, x: Int, y: Int, i: Int, queue: ArrayDeque<Pair<Int, Int>>) {
        if (distances[y][x] == -1) {
            distances[y][x] = i
            queue.add(Pair(y, x))
        }
    }

    fun bfs() {
        val queue = ArrayDeque<Pair<Int, Int>>()

        queue.add(startPosition)
        distances[startPosition.first][startPosition.second] = 0
        val directions = mapOf(
            '|' to listOf(Pair(0, 1), Pair(0, -1)),
            '-' to listOf(Pair(1, 0), Pair(-1, 0)),
            'L' to listOf(Pair(0, -1), Pair(1, 0)),
            'J' to listOf(Pair(0, -1), Pair(-1, 0)),
            '7' to listOf(Pair(0, 1), Pair(-1, 0)),
            'F' to listOf(Pair(1, 0), Pair(0, 1)),
            'S' to listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
        )
        while (queue.isNotEmpty()) {
            val current = queue.poll()

            val currentCell = labyrinth[current.first][current.second]
            if (currentCell != '.') {
                for (dir in directions[currentCell]!!) {
                    val nx = current.second
                    val ny = current.first

                    if (nx + dir.first >= 0 && ny + dir.second >= 0) {
                        val x = nx + dir.first
                        val y = ny + dir.second
                        if (currentCell == 'L') {
                            if (dir.second == -1 && (labyrinth[y][x] == '7' || labyrinth[y][x] == 'F' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                            if (dir.first == 1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '7')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                        }
                        if (currentCell == '|') {
                            if (dir.second == -1 && (labyrinth[y][x] == '7' || labyrinth[y][x] == 'F' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                            if (dir.second == 1 && (labyrinth[y][x] == 'L' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                        }
                        if (currentCell == '-') {
                            if (dir.first == 1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '7')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                            if (dir.first == -1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'F' || labyrinth[y][x] == 'L')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                        }
                        if (currentCell == 'J') {
                            if (dir.first == -1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'L' || labyrinth[y][x] == 'F')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                            if (dir.second == -1 && (labyrinth[y][x] == '|' || labyrinth[y][x] == '7' || labyrinth[y][x] == 'F')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                        }
                        if (currentCell == '7') {
                            if (dir.first == -1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'F' || labyrinth[y][x] == 'L')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                            if (dir.second == 1 && (labyrinth[y][x] == 'L' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                        }
                        if (currentCell == 'F') {
                            if (dir.first == 1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == '7' || labyrinth[y][x] == 'J')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                            if (dir.second == 1 && (labyrinth[y][x] == 'L' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                        }
                        if (currentCell == 'S') {
                            if (dir.second == -1 && (labyrinth[y][x] == '7' || labyrinth[y][x] == 'F' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                            if (dir.second == 1 && (labyrinth[y][x] == 'L' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '|')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                            if (dir.first == 1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'J' || labyrinth[y][x] == '7')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }

                            if (dir.first == -1 && (labyrinth[y][x] == '-' || labyrinth[y][x] == 'F' || labyrinth[y][x] == 'L')) {
                                checkNextStep(distances, x, y, distances[current.first][current.second] + 1, queue)
                            }
                        }
                    }
                }
            }

        }
    }
    bfs()
    var maxDistance = 0
    for (row in distances) {
        for (distance in row) {
            print(distance.toString() + "\t")
            if (distance > maxDistance) {
                maxDistance = distance
            }
        }
        println()
    }
    println(countInsidePile(distances, input))
    println(maxDistance)

}


fun countInsidePile(distances: Array<IntArray>, input: List<String>): Int {
    var sum = 0
    for (i in distances.indices) {
        for (j in distances[0].indices) {
            if (distances[i][j] == -1) {
                if (check(distances, i, j, input.toMutableList())) sum++
            }
        }
    }
    return sum
}

fun check(value: Array<IntArray>, i: Int, j: Int, input: MutableList<String>): Boolean {
    for (k in input[0].indices) {
        if (value[i][k] == -1 || value[i][k] == 0) input[i] = input[i].substring(0, k) + '.' + input[i].substring(k + 1)
    }
    var line = input.get(i).substring(j + 1)
    line = line.replace(Regex("[F]-*[J]"), "|")
    line = line.replace(Regex("[L]-*[7]"), "|")
    line = line.replace(Regex("F-*7"), "")
    line = line.replace(Regex("L-*J"), "")
    return Regex("[7JFL|]").findAll(line).toList().size % 2 == 1


}
