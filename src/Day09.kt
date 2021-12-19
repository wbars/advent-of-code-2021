data class LowPoint(val i: Int, val j: Int, val value: Int)

fun main() {
    fun getLowPoints(input: List<String>): List<LowPoint> {
        val a = input.map { c -> c.map { it.toString().toInt() } }
        val r = mutableListOf<LowPoint>()
        for (i in a.indices) {
            for (j in a[i].indices) {
                val c = a[i][j]
                if (c < a.safeGet(i - 1, j) &&
                    c < a.safeGet(i + 1, j) &&
                    c < a.safeGet(i, j - 1) &&
                    c < a.safeGet(i, j + 1)
                )
                    r.add(LowPoint(i, j, c))
            }
        }
        return r
    }

    fun part1(input: List<String>): Int {
        return getLowPoints(input).sumOf { it.value + 1 }
    }

    fun part2(input: List<String>): Int {
        val basinSizes = mutableListOf<Int>()
        val basins = Array(input.size) { BooleanArray(input[0].length) }
        fun markBasins(i: Int, j: Int): Int {
            if (i < 0 || i >= basins.size) return 0
            if (j < 0 || j >= basins[i].size) return 0
            if (basins[i][j]) return 0
            basins[i][j] = true
            if (input[i][j] == '9') return 0
            return 1 + markBasins(i - 1, j) + markBasins(i + 1, j) + markBasins(i, j - 1) + markBasins(i, j + 1)
        }
        for (lowPoint in getLowPoints(input)) {
            basinSizes.add(markBasins(lowPoint.i, lowPoint.j))
        }
        basinSizes.sortDescending()
        return basinSizes.subList(0, 3).reduce { a, b -> a * b }
    }


    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}

private fun List<List<Int>>.safeGet(i: Int, j: Int): Int {
    if (i < 0 || i >= size) return Int.MAX_VALUE
    val row = this[i]
    if (j < 0 || j >= row.size) return Int.MAX_VALUE
    return row[j]
}

