package day02

import Util

fun main() {
    val input = Util().readFile("day02/input.txt")
    val validPasswordCount = input.split("\n").map {
        Password(it.split("-", " ", ": "))
    }.filter { it.hasValidPositions() }.count()
    println(validPasswordCount)
}

fun Password.hasValidPositions(): Boolean {
    return (this.password[this.lowest - 1] == this.letter) xor (this.password[this.highest - 1] == this.letter)
}