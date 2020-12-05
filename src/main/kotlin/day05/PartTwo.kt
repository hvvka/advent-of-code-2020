package day05

import Util

fun main() {
    val rawSeats = Util().readFile("day05/input.txt").reader().readLines()

    val seats = rawSeats.map { Seat(it).calculateSeatId() }
    val minSeatId = seats.minOrNull()
    val maxSeatId = seats.max()
    val seatSum = seats.sum()
    val expectedSum = (minSeatId!! + maxSeatId!!) * (seats.size + 1) / 2
    println(expectedSum - seatSum)
}