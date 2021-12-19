fun main() {
    fun part1(input: List<String>): Int {
        return input.flatMap { it.split("|")[1].trim().split(" ") }
            .count { it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7 }
    }

    val m = mapOf(
        Pair("abcefg", 0),
        Pair("cf", 1),
        Pair("acdeg", 2),
        Pair("acdfg", 3),
        Pair("bcdf", 4),
        Pair("abdfg", 5),
        Pair("abdefg", 6),
        Pair("acf", 7),
        Pair("abcdefg", 8),
        Pair("abcdfg", 9),
    )
    fun subsitute(table: Map<Char, Char>, l: String): String {
        val substitutedSignal = l.map { table.get(it)!! }.sorted().joinToString("")
        return m.get(substitutedSignal)!!.toString()
    }

    fun deduce(list: List<String>): Map<Char, Char> {
        assert(list.size == 10)
        val r = mutableMapOf<Char, Char>()

        val one = list.find { it.length == 2 }!!
        val seven = list.find { it.length == 3 }!!

        val a = seven.find { !one.contains(it) }!!
        r[a] = 'a'

        val four = list.find { it.length == 4 }!!
        val nine = list.find { it.length == 6 && (four + a).all { c -> it.contains(c) } }!!
        val g = nine.find { !(four + a).contains(it) }!!
        r[g] = 'g'

        val three = list.find { it.length == 5 && one.all { c -> it.contains(c) } }!!
        val d = three.find { !(one + a + g).contains(it) }!!
        r[d] = 'd'

        val b = nine.find { !(one + a + g + d).contains(it) }!!
        r[b] = 'b'

        val five = list.find { it.length == 5 && (a.toString() + d + g + b).all { c -> it.contains(c) } }!!
        val f = five.find { !(a.toString() + d + g + b).contains(it) }!!
        r[f] = 'f'

        val c = one.find { it != f }!!
        r[c] = 'c'

        val e = "abcdefg".find { !nine.contains(it) }!!
        r[e] = 'e'

        return r
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split("|") }.sumOf {
            val table = deduce(it[0].trim().split(" "))
            it[1].trim().split(" ")
                .map { subsitute(table, it) }
                .joinToString("")
                .toInt()
        }


    }


    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}

