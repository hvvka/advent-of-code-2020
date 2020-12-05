package day05

import Util


fun main() {
    val seats = Util().readFile("day05/input.txt").reader().readLines()
    val maxSeatId = seats.map { Seat(it).calculateSeatId() }.max()
    println(maxSeatId)
}

class Seat(private val row: String, private val column: String) {
    constructor(seat: String) : this(seat.substring(0, 7), seat.substring(7))

    fun calculateSeatId(): Int {
        val row = findRow()
        val column = findColumn()
        return row * 8 + column
    }

    private fun findRow(): Int {
        return binarySearch(127, 0, this.row)
    }

    private fun findColumn(): Int {
        return binarySearch(8, 0, this.column)
    }

    private fun binarySearch(max: Int, min: Int, commands: String): Int {
        var high = max
        var low = min
        for (i in commands.indices) {
            when {
                commands[i] == 'F' || commands[i] == 'L' -> high -= (high - low + 1) / 2
                commands[i] == 'B' || commands[i] == 'R' -> low += (high - low + 1) / 2
            }
        }
        return low
    }
}