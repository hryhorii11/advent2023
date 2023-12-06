import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Long.parseLong

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    var lines = reader.readLines()

    task1(lines.toMutableList())
    task2(lines)
}

fun task1(lines: MutableList<String>) {
    lines.add(":")
    var seeds = Regex("\\d+").findAll(lines[0]).map { parseLong(it.value) }.toMutableList()
    var numbersMap = mutableListOf<MutableList<Long>>()
    for (it in lines) {

        val soils = ArrayList<Long>()
        Regex("\\d+").findAll(it).forEach {
            soils.add(parseLong(it.value))
        }
        if (soils.size == 3) {
            numbersMap.add(soils)
        }
        if (it.contains(":")) {
            for (i in 0..seeds.size - 1)
                seeds[i] = processSeed(seeds[i], numbersMap)
            numbersMap = mutableListOf<MutableList<Long>>()

        }


    }
    val min = seeds.minOfOrNull { it }
    println(" min:$min")
}


fun processSeed(seed: Long, numbersMap: MutableList<MutableList<Long>>): Long {
    for (list in numbersMap) {
        if (seed >= list[1] && seed < list[2] + list[1]) {
            return list[0] + (seed - list[1])
        }
    }
    return seed
}

fun task2(lines: List<String>) {
    var ranges = arrayListOf<Pair<Long, Long>>()
    var updateRanges = ArrayList<Pair<Long, Long>>()
    var numbersMap = mutableListOf<MutableList<Long>>()
    var index = 0
    var prev = 0L
    Regex("\\d+").findAll(lines[0]).forEach {
        if (index % 2 == 1) {
            ranges.add(Pair(prev, parseLong(it.value) + prev))
        }
        prev = parseLong(it.value)
        index++
    }
    for (line in lines) {
        if (line.isEmpty() || line.contains("seeds:")) continue
        else if (!line.contains("map")) {
            val numbers = Regex("\\d++").findAll(line).map { parseLong(it.value) }.toList()
            var mapFrom = Pair(numbers[1], numbers[1] + numbers[2])
            val mappingChange = numbers[0] - numbers[1]
            var i = 0
            while (i < ranges.size) {
                val currentRange = ranges[i]
                if (mapFrom.first <= currentRange.second && mapFrom.second > currentRange.first) {
                    ranges.removeAt(i--)
                    val rangeToMap =
                        Pair(Math.max(currentRange.first, mapFrom.first), Math.min(currentRange.second, mapFrom.second))
                    val mappedRange = Pair(rangeToMap.first + mappingChange, rangeToMap.second + mappingChange);
                    updateRanges.add(mappedRange)

                    if (currentRange.first < rangeToMap.first)
                        ranges.add(Pair(currentRange.first, rangeToMap.first - 1))
                    if (currentRange.second > rangeToMap.second) {
                        ranges.add(Pair(rangeToMap.second, currentRange.second - 1))
                    }
                }
                i++
            }
        } else {
            ranges.addAll(updateRanges)
            updateRanges.clear()
        }
    }
    ranges.addAll(updateRanges)
    println(ranges.map { it.first }.min())


}


