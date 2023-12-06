import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.Long.parseLong
import kotlin.concurrent.timerTask

fun main() {
    val file =
        File("inputData.txt")
    val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
    val lines = reader.readLines().toList()
    val time = Regex("\\d+").findAll(lines[0]).map { parseLong(it.value) }.toList()
    val distance = Regex("\\d+").findAll(lines[1]).map { parseLong(it.value) }.toList()
    task1(time, distance)
    var timeTask2 = ""
    var distanceTask2 = ""
    for (i in time.indices) {
        timeTask2 += time[i].toString()
        distanceTask2 += distance[i].toString()
    }
    task2(parseLong(timeTask2), parseLong(distanceTask2))

}

fun task1(time: List<Long>, distance: List<Long>) {

    var sum = 1
    for (i in time.indices) {
        val count = getWays(time[i],distance[i])
        if (count > 0)
            sum *= count
    }
    println(sum)
}
fun task2(time: Long,distance: Long)
{
    println(getWays(time,distance))
}

fun getWays(time:Long ,distance:Long):Int {
   var count=0
    for (j in 0..time) {
        if ((time - j) * j > distance) {
            count++
        }
    }
    return count
}
