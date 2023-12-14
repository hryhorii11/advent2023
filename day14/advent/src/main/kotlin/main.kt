import java.io.File

fun main() {
    val input = File("inputData.txt").readLines()

    println("task1:" + calculateTotalLoad(turnToNorth(input.toMutableList())))
    var data = input.toMutableList()
    calculateTotalLoad(data)
    data = input.toMutableList()
    for (i in 1..1000) {
        data = turnToNorth(data)
        data = turnToWest(data)
        data = turnToSouth(data)
        data = turnToEast(data)
    }
    println("task2:" + calculateTotalLoad(data))

}

fun turnToSouth(input: MutableList<String>): MutableList<String> {
    for (i in 0..input.size - 1) {
        for (j in 0..input.size - 2) {
            for (k in 0..input[j].length - 1) {
                var line = input[j + 1]
                var line1 = input[j]
                if (input[j + 1][k] == '.' && input[j][k] == 'O') {
                    line = line.substring(0, k) + 'O' + line.substring(k + 1)
                    line1 = line1.substring(0, k) + '.' + line1.substring(k + 1)
                }
                input[j + 1] = line
                input[j] = line1
            }
        }
    }

    return input
}


fun calculateTotalLoad(turnedToNorth: MutableList<String>): Int {
    var sum = 0
    for (i in 0 until turnedToNorth.size) {
        sum += Regex("O").findAll(turnedToNorth[i]).toList().size * (turnedToNorth.size - i)
    }
    return sum
}

fun turnToEast(input: MutableList<String>): MutableList<String> {
    for (i in 0 until input.size) {
        var index = input[i].length - 1
        for (j in input[i].length - 1 downTo 0) {
            if (input[i][j] == 'O' && index > j) {
                input[i] = input[i].substring(0, index) + 'O' + input[i].substring(index + 1)
                input[i] = input[i].substring(0, j) + '.' + input[i].substring(j + 1)
                index--
            }
            if (input[i][j] == 'O' || input[i][j] == '#') index = j - 1

        }
    }


    return input
}

fun turnToWest(input: MutableList<String>): MutableList<String> {
    for (i in 0 until input.size) {
        var index = 0
        for (j in 0 until input[i].length) {
            if (input[i][j] == 'O' && index < j) {
                input[i] = input[i].substring(0, index) + 'O' + input[i].substring(index + 1)
                input[i] = input[i].substring(0, j) + '.' + input[i].substring(j + 1)
                index++
            }
            if (input[i][j] == 'O' || input[i][j] == '#') index = j + 1

        }
    }

    return input
}

fun turnToNorth(input: MutableList<String>): MutableList<String> {
    for (i in 0..input.size - 1) {
        for (j in 0..input.size - 1) {
            if (j > 0) {
                for (k in 0..input[j].length - 1) {
                    var line = input[j - 1]
                    var line1 = input[j]
                    if (input[j - 1][k] == '.' && input[j][k] == 'O') {
                        line = line.substring(0, k) + 'O' + line.substring(k + 1)
                        line1 = line1.substring(0, k) + '.' + line1.substring(k + 1)
                    }
                    input[j - 1] = line
                    input[j] = line1
                }
            }
        }
    }
    return input
}

