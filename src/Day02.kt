data class Position(var x: Int, var depth: Int) {
    fun move(command: String) {
        val p = command.split(" ")
        val diff = p[1].toInt()
        when (p[0]) {
            "forward" -> x += diff
            "down" -> depth += diff
            "up" -> depth -= diff
        }
    }
}

data class AimedPosition(var x: Long, var depth: Long, var aim: Long) {
    fun move(command: String) {
        val p = command.split(" ")
        val diff = p[1].toInt()
        when (p[0]) {
            "forward" -> {
                x += diff
                depth += aim * diff
            }
            "down" -> aim += diff
            "up" -> aim -= diff
        }
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        val position = Position(0, 0)
        for (s in input) {
            position.move(s)
        }
        return position.x * position.depth
    }

    fun part2(input: List<String>): Long {
        val position = AimedPosition(0, 0, 0)
        for (s in input) {
            position.move(s)
        }
        return position.x * position.depth
    }

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
