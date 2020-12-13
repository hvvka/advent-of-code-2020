package day11

import Util

fun main() {
    val seats =
        Util().readFile("day11/input.txt").reader().readLines().map { it.toCharArray().toMutableList() }.toMutableList()
    val seatLayout = SeatLayoutUpdated(seats)
    seatLayout.repeatUntilNoSeatsChangeState()
    println(seatLayout.getOccupiedSeatCount())
}

class SeatLayoutUpdated(val seats: MutableList<MutableList<Char>>) {

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
                    else if (seat == '#' && occupiedSeats >= 5) seats[y][x] = 'L'
                }
            }
        }
    }

    private fun getOccupiedAdjacentSeatCount(x: Int, y: Int): Int {
        var occupied = 0
        occupied += isFirstInDirectionOccupied(x, y, 1, 1)
        occupied += isFirstInDirectionOccupied(x, y, 1, 0)
        occupied += isFirstInDirectionOccupied(x, y, 1, -1)
        occupied += isFirstInDirectionOccupied(x, y, 0, 1)
        occupied += isFirstInDirectionOccupied(x, y, 0, -1)
        occupied += isFirstInDirectionOccupied(x, y, -1, 1)
        occupied += isFirstInDirectionOccupied(x, y, -1, 0)
        occupied += isFirstInDirectionOccupied(x, y, -1, -1)
        return occupied
    }

    private fun isFirstInDirectionOccupied(x: Int, y: Int, transformX: Int, transformY: Int): Int {
        val row = if (transformY == 0) this.previousSeatState[y]
        else this.previousSeatState.getOrNull(y + transformY) ?: return 0
        val seat = row.getOrNull(x + transformX)
        return when {
            seat == null -> 0
            isOccupied(seat) -> 1
            seat == 'L' -> 0
            else -> isFirstInDirectionOccupied(
                x,
                y,
                if (transformX == 0) transformX else if (transformX > 0) transformX + 1 else transformX - 1,
                if (transformY == 0) transformY else if (transformY > 0) transformY + 1 else transformY - 1
            )
        }
    }

    private fun isOccupied(seat: Char?): Boolean {
        return seat == '#'
    }

    override fun toString(): String {
        return "SeatLayout(seats=$seats)"
    }
}

