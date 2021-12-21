fun main() {
    fun part1(input: List<String>): Int {
        val g = mutableMapOf<String, MutableCollection<String>>()
        for (s in input) {
            val l = s.split("-")
            g.computeIfAbsent(l[0]) { mutableSetOf() }.add(l[1])
            g.computeIfAbsent(l[1]) { mutableSetOf() }.add(l[0])
        }
        fun dfs(node: String, visited: Set<String>): Int {
            var r = 0
            val children = g[node] ?: emptyList()
            for (child in children) {
                if (child == "start") continue
                if (child == "end") {
                    r++
                    continue
                }
                if (visited.contains(child)) continue
                r += dfs(child, if (child.lowercase() == child) visited + setOf(child) else visited)
            }
            return r
        }
        return dfs("start", setOf())
    }

    fun part2(input: List<String>): Int {
        val g = mutableMapOf<String, MutableCollection<String>>()
        for (s in input) {
            val l = s.split("-")
            g.computeIfAbsent(l[0]) { mutableSetOf() }.add(l[1])
            g.computeIfAbsent(l[1]) { mutableSetOf() }.add(l[0])
        }
        fun dfs(node: String, visited: Set<String> = setOf(), pass: Boolean = false): Int {
            var r = 0
            val children = g[node] ?: emptyList()
            for (child in children) {
                if (child == "start") continue
                if (child == "end") {
                    r++
                    continue
                }
                if (!visited.contains(child)) {
                    r += dfs(child, if (child.lowercase() == child) visited + setOf(child) else visited, pass)
                } else if (!pass) {
                    r += dfs(child, visited, true)
                }
            }
            return r
        }
        return dfs("start")
    }

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}


