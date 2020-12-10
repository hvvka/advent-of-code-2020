package day09

import Util

fun main() {
    val numbers = Util().readFile("day09/input.txt").reader().readLines().map { it.toLong() }

    val invalidNumber = 18272118L
    val longestSubarray = subArraySum(numbers, invalidNumber)
    val min = longestSubarray.minOrNull()
    val max = longestSubarray.maxOrNull()
    println(min!! + max!!)
}

fun subArraySum(numbers: List<Long>, sum: Long): List<Long> {
    var currentSum = numbers[0]
    var start = 0

    var i = 1
    while (i <= numbers.size) {
        while (currentSum > sum && start < i - 1) {
            currentSum -= numbers[start]
            start++
        }

        if (currentSum == sum) {
            val end = i - 1
            println("Sum found between indexes $start and $end")
            println("Sum found between numbers ${numbers[start]} and ${numbers[end]}")
            return numbers.subList(start, end + 1)
        }

        if (i < numbers.size) {
            currentSum += numbers[i]
        }
        i++
    }
    println("No subarray found")
    return listOf()
}
