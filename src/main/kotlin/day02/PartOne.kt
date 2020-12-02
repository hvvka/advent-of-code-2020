package day02

import Util

fun main() {
    val input = Util().readFile("day02/input.txt")
    val validPasswordCount = input.split("\n").map {
        Password(it.split("-", " ", ": "))
    }.filter { it.isValid() }.count()
    println(validPasswordCount)
}

data class Password(val lowest: Int, val highest: Int, val letter: Char, val password: String) {
    constructor(columns: List<String>) : this(
        columns[0].toInt(), columns[1].toInt(), columns[2].toCharArray()[0], columns[3]
    )

    fun isValid(): Boolean {
        val occurrences = countCharacterOccurrences(this.password, this.letter)
        return occurrences in lowest..highest
    }

    private fun countCharacterOccurrences(string: String, character: Char): Int {
        return string.filter { it == character }.count()
    }
}
