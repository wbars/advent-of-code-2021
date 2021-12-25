fun main() {
    val cordsSize = 1004
    val initialY = 447 * 2 + 1
    val initialX = 655 * 2 + 1
    fun buildSheet(i: Int, input: List<String>): Array<BooleanArray> {
        val a = Array(initialY) { BooleanArray(initialX) }
        (0 until i)
            .map { input[it].split(",") }
            .forEach { a[it[1].toInt()][it[0].toInt()] = true }
        return a
    }

    fun foldX(a: Array<BooleanArray>): Array<BooleanArray> {
        val newA = Array(a.size) { BooleanArray((a[0].size - 1) / 2) }
        for (y in newA.indices) {
            val row = newA[y]
            for (x in row.indices) {
                if (a[y][x] || a[y][row.size - x + row.size]) {
                    newA[y][x] = true
                }
            }
        }
        return newA
    }

    fun foldY(a: Array<BooleanArray>): Array<BooleanArray> {
        val newA = Array((a.size - 1) / 2) { BooleanArray(a[0].size) }
        for (y in newA.indices) {
            for (x in newA[y].indices) {
                if (a[y][x] || a[newA.size - y + newA.size][x]) {
                    newA[y][x] = true
                }
            }
        }
        return newA

    }

    fun part1(input: List<String>): Int {
        var a = buildSheet(cordsSize, input)

        a = foldX (a)

        var r = 0
        for (row in a) {
            for (col in row) {
                if (col) r++
            }
        }
        return r
    }

    fun buildString(a: Array<BooleanArray>): String {
        return a.joinToString("\n") { row ->
            row.joinToString("") { if (it) "#" else " " }
        }
    }

    fun part2(input: List<String>) {
        var a = buildSheet(cordsSize, input)
        for (i in (cordsSize until input.size)) {
            if (input[i] == "x") {
                a = foldX(a);
            } else {
                a = foldY(a);
            }
        }
        println(buildString(a))

    }

    val input = readInput("Data")
    println(part1(input))
    part2(input)
}


