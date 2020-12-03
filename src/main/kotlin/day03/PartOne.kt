package day03

import Util

fun main() {
    val fileLines = Util().readFile("day03/input.txt").reader().readLines()

    val map: List<List<Char>> = fileLines.map { it.toList() }
    val answer = Map(map, Location(0, 0, 3, 1)).countHitTrees()
    println(answer)

}

class Map(private val map: List<List<Char>>, private val visitor: Location) {
    fun countHitTrees(): Long {
        var hitTreeCounter = 0L
        while (this.visitor.y < this.map.size) {
            val symbol = this.map[this.visitor.y][this.visitor.x % this.map[0].size]
            if (symbol == '#') {
                hitTreeCounter++
            }
            this.visitor.move()
        }
        return hitTreeCounter
    }
}

data class Location(var x: Int, var y: Int, val moveX: Int, val moveY: Int) {
    fun move() {
        this.x += this.moveX
        this.y += this.moveY
    }
}
