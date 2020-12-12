package day10

import Util

fun main() {
    val joltage = Util().readFile("day10/input.txt").reader().readLines().map { it.toInt() }.toMutableList()
    joltage.add(0)
    joltage.add(joltage.maxOrNull()!! + 3)
    val sortedJoltage = joltage.sorted().toMutableList()

    var arrangements = 1L
    var combo = 0
    for (i in 1..sortedJoltage.size - 2) {
        val neighbourDifference = sortedJoltage[i + 1] - sortedJoltage[i - 1]
        if (neighbourDifference == 2) {
            combo++
        } else if (combo != 0) {
            when (combo) {
                3 -> arrangements *= 7
                2 -> arrangements *= 4
                1 -> arrangements *= 2
            }
            combo = 0
        }
    }
    println(arrangements)
}
