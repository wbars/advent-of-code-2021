fun main() {
    fun part1(input: List<Int>): Int {
        return (1 until input.size).count { input[it] > input[it - 1] }
    }

    fun List<Int>.window(base: Int) = this[base] + this[base + 1] + this[base + 2]

    fun part2(input: List<Int>): Int {
        return (0 until input.size - 3).count { input.window(it) < input.window(it + 1) }
    }

    val input = readIntInput("Data")
    println(part1(input))
    println(part2(input))
}
