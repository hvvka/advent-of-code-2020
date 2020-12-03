package day03

import Util

fun main() {
    val fileLines = Util().readFile("day03/input.txt").reader().readLines()

    val map: List<List<Char>> = fileLines.map { it.toList() }
    val answer = longArrayOf(
        Map(map, Location(0, 0, 1, 1)).countHitTrees(),
        Map(map, Location(0, 0, 3, 1)).countHitTrees(),
        Map(map, Location(0, 0, 5, 1)).countHitTrees(),
        Map(map, Location(0, 0, 7, 1)).countHitTrees(),
        Map(map, Location(0, 0, 1, 2)).countHitTrees()
    )
    println(answer.reduce { acc, i -> acc * i })
}
