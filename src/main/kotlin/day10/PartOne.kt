package day10

import Util

fun main() {
    val joltage = Util().readFile("day10/input.txt").reader().readLines().map { it.toInt() }.toMutableList()
    joltage.add(0)
    joltage.add(joltage.maxOrNull()!! + 3)
    val sortedJoltage = joltage.sorted()
    println(sortedJoltage)

    val result = sortedJoltage.toIntArray().fold(listOf(0, 0, 0)) { acc, i ->
        listOf(if (i - acc[2] == 1) acc[0] + 1 else acc[0], if (i - acc[2] == 3) acc[1] + 1 else acc[1], i)
    }
    println(result[0] * result[1])
}
