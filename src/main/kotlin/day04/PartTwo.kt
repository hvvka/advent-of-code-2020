package day04

import Util

fun main() {
    val passports = Util().readFile("day04/input.txt").split("\n\n")
    val mandatoryFields = arrayOf("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt")
    val validPassports =
        passports.filter { passport -> mandatoryFields.none { field -> !passport.contains("$field:") } }

    println(validPassports.filter { Password(it).validate() }.count())
}

class Password(string: String) {
    private val fields = string.split(" ", "\n")

    private val mandatoryFieldsWithRules = mapOf(
        "byr:" to { byr: String -> byr.isNotBlank() && byr.toInt() >= 1920 && byr.toInt() <= 2002 },
        "iyr:" to { iyr: String -> iyr.isNotBlank() && iyr.toInt() >= 2010 && iyr.toInt() <= 2020 },
        "eyr:" to { eyr: String -> eyr.isNotBlank() && eyr.toInt() >= 2020 && eyr.toInt() <= 2030 },
        "hgt:" to { hgt: String ->
            (hgt.contains("cm") && hgt.split("cm")[0].toInt() >= 150 && hgt.split("cm")[0].toInt() <= 193)
                    || (hgt.contains("in") && hgt.split("in")[0].toInt() >= 59 && hgt.split("in")[0].toInt() <= 76)
        },
        "hcl:" to { hcl: String -> hcl matches "#[0-9a-f]{6}".toRegex() },
        "ecl:" to { ecl: String -> ecl matches "(amb|blu|brn|gry|grn|hzl|oth)".toRegex() },
        "pid:" to { pid: String -> pid matches ("[0-9]{9}".toRegex()) },
    )

    fun validate(): Boolean {
        val test = fields.flatMap { field ->
            mandatoryFieldsWithRules.entries.filter { entry -> field.contains(entry.key) }
                .map { entry ->
                    println("$field ${entry.value(field.split(entry.key).last())}")
                    entry.value(field.split(entry.key).last())
                }
        }
        return !test.contains(false)
    }

}
