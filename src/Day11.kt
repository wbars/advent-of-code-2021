fun flash(m: Array<IntArray>, f: Array<BooleanArray>): Int {
    var r = 0
    for (i in m.indices) {
        for (j in m[i].indices) {
            r += flash(f, i, j, m)
        }
    }
    return r

}

private fun flash(
    f: Array<BooleanArray>,
    i: Int,
    j: Int,
    m: Array<IntArray>
): Int {
    var r = 0
    if (!f[i][j] && m[i][j] > 9) {
        f[i][j] = true
        r++
        r += incNearby(m, f, i, j)
        m[i][j] = 0
    }
    return r
}

fun incNearby(m: Array<IntArray>, f: Array<BooleanArray>, i: Int, j: Int): Int {
    var r = 0
    val q = mutableListOf<Pair<Int, Int>>()
    for (x in listOf(-1, 0, 1)) {
        for (y in listOf(-1, 0, 1)) {
            if (x != 0 || y != 0) {
                val i1 = i + y
                val j1 = j + x
                if ((i1 in 0..9) && j1 in 0..9) {
                    m[i1][j1]++
                    q.add(Pair(i1, j1))
                }
            }
        }
    }
    for (pair in q) {
        r+=flash(f, pair.first, pair.second, m)
    }
    return r
}

fun inc(m: Array<IntArray>) {
    for (i in m.indices) {
        for (j in m[i].indices) {
            m[i][j]++
        }
    }
}

fun clearCache(m: Array<IntArray>, f: Array<BooleanArray>) {
    for (i in m.indices) {
        for (j in f[i].indices) {
            if (f[i][j]) {
                f[i][j] = false
                m[i][j] = 0
            }
        }
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        val m = input.map { l ->
            l.map { it.toString().toInt() }.toIntArray()
        }.toTypedArray()


        val f = Array(10) { BooleanArray(10) }

        var r = 0
        repeat(100) {
            inc(m)
            r += flash(m, f)
            clearCache(m, f)
        }
        return r
    }

    fun part2(input: List<String>): Int {
        val m = input.map { l ->
            l.map { it.toString().toInt() }.toIntArray()
        }.toTypedArray()


        val f = Array(10) { BooleanArray(10) }

        var r = 0
        while (true) {
            r++
            inc(m)
            flash(m, f)
            if (f.all { l -> l.all { it } }) {
                return r
            }
            clearCache(m, f)
        }
    }


    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}


