package day06

import Util

fun main() {
    val answers = Util().readFile("day06/input.txt").split("\n\n")

    val yesAnswersSum = answers.map { groupAnswers ->
        groupAnswers.split("\n", "").toSet().count() - 1
    }.sum()

    println(yesAnswersSum)
}
