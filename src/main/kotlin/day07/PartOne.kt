package day07

import Util

fun main() {
    val rules = Util().readFile("day07/input.txt").reader().readLines()

    val extractedRules =
        rules.map { rule ->
            rule.split(" bags contain ", "no other", " bags", " bag", ", ", ".").filter { it.isNotBlank() }
        }

    val allInnerBags = extractedRules.map { it.subList(1, it.size) }.joinToString("")
    val rootBag: List<String> = extractedRules.find { rule -> !allInnerBags.contains(rule[0]) }!!

    val bags = extractedRules.map {
        Bag(it[0], it.subList(1, it.size))
    }.toMutableList()
    val myBag = bags.find { it.name == "shiny gold" }!!

    var currentBag: Bag = myBag
    val bagsThatCanContainMyBag = mutableListOf<Bag>()
    var index = 0
    while (currentBag.name != rootBag[0]) {
        bagsThatCanContainMyBag.addAll(
            bags.filter { bag ->
                bag.innerBags.any { innerBag ->
                    innerBag.name == currentBag.name && !bagsThatCanContainMyBag.map { it.name }.contains(bag.name)
                }
            })
        currentBag = bagsThatCanContainMyBag[index++]
        if (index >= bagsThatCanContainMyBag.size) break
    }

    println(bagsThatCanContainMyBag.count() + 1)
}

class Bag(val name: String, val quantity: Int) {

    var innerBags: MutableList<Bag> = mutableListOf()

    constructor(name: String, innerBags: List<String>) : this(name, 1) {
        this.innerBags = innerBags.map { description ->
            val bag = description.substringAfter(' ')
            val number = description.substringBefore(' ').toInt()
            Bag(bag, number)
        }.toMutableList()
    }

    override fun toString(): String {
        return "'$name'=$innerBags"
    }
}