fun main() {
    val cache = mutableMapOf<Pair<Int, Int>, Long>()
    fun estimate(timer: Int, days: Int = 256): Long {
        val key = Pair(timer, days)
        if (cache[key] != null) {
            return cache[key]!!
        }
        val remainingDays = days - timer - 1
        if (remainingDays >= 0) {
            val r = estimate(6, remainingDays) + estimate(8, remainingDays)
            cache[key] = r
            return r
        }
        return 1L
    }
    for (d in 0 until 256) {
        for (t in 1 until 9) {
            estimate(t, d)
        }
    }
    fun solve(input: List<String>): Long {
        return input[0].split(",").map { estimate(it.toInt()) }.sum()
    }

    val input = readInput("Data")
    println(solve(input))
}

