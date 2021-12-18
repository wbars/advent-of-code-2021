import java.lang.Integer.max
import java.lang.Math.abs
import kotlin.math.min


fun main() {
    fun part1(input: List<String>): Int {
        val r = Array(1000) { IntArray(1000){0} }
        for (s in input) {
            val cords = s.replace(" -> ", ",").split(",").map { it.toInt() }
            val x1 = cords[0]
            val y1 = cords[1]
            val x2 = cords[2]
            val y2 = cords[3]
            if (x1 == x2) {
                for (i in min(y1, y2) .. max(y1, y2)) r[i][x1]++
            } else if (y1 == y2) {
                for (j in min(x1, x2) .. max(x1, x2)) r[y1][j]++
            } else if (abs(x1 - x2) == abs(y1 - y2)) {
                // 2 part
                var ci = y1
                var cj = x1
                r[ci][cj]++
                repeat(abs(x1 - x2)) {
                    if (x1 < x2) cj++ else cj--
                    if (y1 < y2) ci++ else ci--
                    r[ci][cj]++
                }
                assert(ci == y2 && cj == x2)
            }
        }
        var rr = 0
        for (i in r.indices) {
            for (j in r[i].indices) {
                if (r[i][j] > 1) rr++
            }
        }
        return rr
    }

//    fun part2(input: List<String>): Int {
//        val boards = createBoards(input)
//        var rr = -1
//        for (c in input[0].split(',')) {
//            for (board in boards) {
//                if (board.move(c.toInt())) {
//                    rr = board.score
//                }
//            }
//        }
//        if (rr >= 0) {
//            return rr
//        }
//        throw java.lang.IllegalStateException()
//    }

    val input = readInput("Data")
    println(part1(input))
//    println(part2(input))
}

