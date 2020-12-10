package day09

import Util

const val preambleLength = 25

fun main() {
    val numbers = Util().readFile("day09/input.txt").reader().readLines().map { it.toLong() }
    println(findFirstInvalidNumber(numbers))
}

fun findFirstInvalidNumber(numbers: List<Long>): Long {
    var queuePointer = 0
    var sum = numbers[preambleLength]
    while (findPair(
            numbers.subList(queuePointer, queuePointer + preambleLength),
            sum
        )
    ) {
        queuePointer++
        sum = numbers[queuePointer + preambleLength]
    }
    return sum
}

fun findPair(numbers: List<Long>, sum: Long): Boolean {
    val map: MutableMap<Long, Long> = HashMap()
    for (i in numbers.indices) {
        if (map.containsKey(sum - numbers[i])) {
//            println("${sum - numbers[i]} + ${numbers[i]} = $sum")
            return true
        }
        map[numbers[i]] = i.toLong()
    }
//    println("Pair not found for $sum")
    return false
}
