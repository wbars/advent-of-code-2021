

fun main() {
    fun d(input: List<String>, inverse: Boolean): String {
        val str = (input[0].indices).map { i ->
            val ones = (input.indices).count { input[it][i] == '1' }
            if (ones >= input.size - ones) '1' else '0'
        }.joinToString("")
        return if (inverse )str.inverse() else str
    }

    fun part1(input: List<String>): Int {
        val str = d(input, false)
        return str.toInt(2) * str.inverse().toInt(2)
    }

    fun reduce(
        input: List<String>,
        copy: List<String>,
        inverse: Boolean
    ): List<String> {
        var c = copy
        for (i in input[0].indices) {
            if (c.size == 1) break
            val dd = d(c, inverse)
            c = c.filter { it[i] == dd[i] }
        }
        assert(c.size == 1)
        return c
    }

    fun part2(input: List<String>): Int {
        val copy: List<String> = reduce(input, input.toMutableList(), true)
        val copy2: List<String> = reduce(input, input.toMutableList(), false)
        return copy[0].toInt(2) * copy2[0].toInt(2)
    }

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}

private fun String.inverse(): String {
    return map {if (it == '0') '1' else '0'}.joinToString("")
}
