import java.lang.Integer.max

class Board(val a: Array<IntArray>, val b: Array<BooleanArray> = Array(5, { BooleanArray(5) }), var score: Int = -1) {
    fun move(c: Int): Boolean {
        if (score >= 0) return false
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                if (a[i][j] == c) {
                    b[i][j] = true
                    if (b[i].all { it } || (0 until 5).all { b[it][j] }) {
                        score = computeScore() * c
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun computeScore(): Int {
        var r = 0;
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                if (!b[i][j]) {
                    r += a[i][j]
                }
            }
        }
        return r
    }
}

fun main() {
    fun createBoards(input: List<String>): MutableList<Board> {
        val boards = mutableListOf<Board>()
        for (i in 1 until input.size step 5) {
            val t: Array<IntArray> = Array(5) { IntArray(5) }
            for (ii in 0 until 5) {
                input[i + ii].trim().split(Regex("\\s+")).forEachIndexed { index, s -> t[ii][index] = s.toInt() }
            }
            boards.add(Board(t))
        }
        return boards
    }

    fun part1(input: List<String>): Int {
        val boards = createBoards(input)
        for (c in input[0].split(',')) {
            var rr = -1
            for (board in boards) {
                if (board.move(c.toInt())) {
                    rr = max(rr, board.score)
                }
            }
            if (rr >= 0) {
                return rr
            }
        }
        throw java.lang.IllegalStateException()
    }

    fun part2(input: List<String>): Int {
        val boards = createBoards(input)
        var rr = -1
        for (c in input[0].split(',')) {
            for (board in boards) {
                if (board.move(c.toInt())) {
                    rr = board.score
                }
            }
        }
        if (rr >= 0) {
            return rr
        }
        throw java.lang.IllegalStateException()
    }

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}

