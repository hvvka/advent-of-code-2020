package day04

import Util

fun main() {
    val passports = Util().readFile("day04/input.txt").split("\n\n")
    val mandatoryFields = arrayOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")
    val validPassportsCount =
        passports.filter { passport -> mandatoryFields.none { field -> !passport.contains("$field:") } }
            .count()
    println(validPassportsCount)
}
