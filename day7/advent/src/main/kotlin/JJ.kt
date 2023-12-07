fun main() {
    val data = listOf(
        listOf("32T3K", "765"),
        listOf("T55J5", "684"),
        listOf("KK677", "28"),
        listOf("KTJJT", "220"),
        listOf("QQQJA", "483")
    )

    // Сортування за першими числами у стрічках
    val sortedData = data.sortedWith(compareBy({ myFunc(it[1].toInt()) }, { it[0] }))

    // Вивід відсортованого списку
    sortedData.forEach { println(it) }
}

fun myFunc(value: Int): Int {
    // Реалізація вашої функції
    return value
}