import java.util.*

private const val incomplete = '0'

fun main() {
    val p = mapOf(
        Pair(']', '['),
        Pair(')', '('),
        Pair('}', '{'),
        Pair('>', '<'),
    )
    val p1 = mapOf(
        Pair('[', ']'),
        Pair('(', ')'),
        Pair('{', '}'),
        Pair('<', '>'),
    )
    fun getInvalidChar(l: String, s: Stack<Char>): Char {
        for (ch in l) {
            if (p.values.contains(ch)) {
                s.push(ch)
                continue
            }
            else {
                val c = p[ch]!!
                if (s.isEmpty() || s.peek() != c) {
                    return ch
                }
                s.pop()
            }
        }
        return incomplete
    }

    fun part1(input: List<String>): Int {
        return input
            .map { getInvalidChar(it, Stack<Char>()) }
            .map { when(it) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0
            }
            }.sum()
    }

    fun computeCompletion(l: String):Long {
        val stack = Stack<Char>()
        val invalidChar = getInvalidChar(l, stack)
        if (invalidChar == incomplete) {
            var r = 0L
            while (!stack.isEmpty()) {
                val c = p1[stack.pop()]
                r *= 5
                r += when(c) {
                    ')' -> 1
                    ']' -> 2
                    '}' -> 3
                    '>' -> 4
                    else -> 0
                }
            }
            return r
        }
        return 0
    }

    fun part2(input: List<String>): Long {
        val s = input.map { computeCompletion(it) }.filter { it > 0 }.sorted()
        return s[s.size / 2]
    }


    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}


