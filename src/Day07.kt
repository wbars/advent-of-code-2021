import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val a = input[0].split(",").map { it.toInt() }
        return a.minOf { b -> a.sumOf { abs(it - b) } }
    }
    fun part2(input: List<String>): Int {
        val a = input[0].split(",").map { it.toInt() }
        return (0 until 2000).minOf { b -> a.sumOf {
            val diff = abs(it - b)
            (1 + diff) * diff / 2
        } }
    }


    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}

