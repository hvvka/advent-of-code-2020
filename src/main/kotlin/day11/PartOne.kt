package day11

import Util

fun main() {
    val seats =
        Util().readFile("day11/input.txt").reader().readLines()
            .map { it.toCharArray().toMutableList() }.toMutableList()
    val seatLayout = SeatLayout(seats)
    seatLayout.repeatUntilNoSeatsChangeState()
    println(seatLayout.getOccupiedSeatCount())
}

class SeatLayout(val seats: MutableList<MutableList<Char>>) {

    private var previousSeatState = listOf<List<Char>>()

    fun getOccupiedSeatCount(): Int {
        return this.seats.map { row -> row.filter { it == '#' }.count() }.sum()
    }

    fun repeatUntilNoSeatsChangeState() {
        do {
            applySeatingRules()
        } while (seats != previousSeatState)
    }

    private fun applySeatingRules() {
        this.previousSeatState = seats.map { it.map { it } }
        for ((y, row) in previousSeatState.withIndex()) {
            for ((x, seat) in row.withIndex()) {
                if (seat != '.') {
                    val occupiedSeats = getOccupiedAdjacentSeatCount(x, y)
                    if (seat == 'L' && occupiedSeats == 0) seats[y][x] = '#'
                    else if (seat == '#' && occupiedSeats >= 4) seats[y][x] = 'L'
                }
            }
        }
    }

    private fun getOccupiedAdjacentSeatCount(x: Int, y: Int): Int {
        var occupied = 0
        occupied += getAdjacentRowOccupiedCount(y - 1, x)
        occupied += isOccupied(this.previousSeatState[y].getOrNull(x - 1))
        occupied += isOccupied(this.previousSeatState[y].getOrNull(x + 1))
        occupied += getAdjacentRowOccupiedCount(y + 1, x)
        return occupied
    }

    private fun getAdjacentRowOccupiedCount(y: Int, x: Int): Int {
        val row = this.previousSeatState.getOrNull(y)
        var occupied = 0
        if (row != null) {
            occupied += isOccupied(row.getOrNull(x - 1))
            occupied += isOccupied(row.getOrNull(x))
            occupied += isOccupied(row.getOrNull(x + 1))
        }
        return occupied
    }


    private fun isOccupied(seat: Char?): Int {
        return if (seat == '#') 1 else 0
    }

    override fun toString(): String {
        return "SeatLayout(seats=$seats)"
    }
}

