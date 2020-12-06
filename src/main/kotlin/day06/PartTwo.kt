package day06

import Util

fun main() {
    val answers = Util().readFile("day06/input.txt").split("\n\n")

    val commonYesAnswerPerGroupSum = answers.map { groupAnswers ->
        val allYesAnswers = groupAnswers.split("\n")
        var sameAnswersForGroup = allYesAnswers[0].toCharArray().toMutableSet()
        allYesAnswers.forEach { personAnswer ->
            sameAnswersForGroup =
                sameAnswersForGroup.intersect(personAnswer.toCharArray().toMutableSet()).toMutableSet()
        }
        sameAnswersForGroup.count()
    }.sum()

    println(commonYesAnswerPerGroupSum)
}

