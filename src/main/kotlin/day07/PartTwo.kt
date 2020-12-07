package day07

import Util

fun main() {
    val rules = Util().readFile("day07/input.txt").reader().readLines()
    val extractedRules =
        rules.map { rule ->
            rule.split(" bags contain ", "no other", " bags", " bag", ", ", ".").filter { it.isNotBlank() }
        }
    val bags = extractedRules.map {
        Bag(it[0], it.subList(1, it.size))
    }.toMutableList()
    val myBag = bags.find { it.name == "shiny gold" }!!

    getInnerBag(myBag, bags, 1)
    println(counter)
}

var counter: Int = 0

fun getInnerBag(bag: Bag, bags: List<Bag>, bagsCount: Int) {
    bag.innerBags.forEach { innerBag ->
        val fullInnerBag = bags.find { it.name == innerBag.name }
        val accumulatedInnerBagsCount = bagsCount * innerBag.quantity
        counter += accumulatedInnerBagsCount
        getInnerBag(fullInnerBag!!, bags, accumulatedInnerBagsCount)
    }
}