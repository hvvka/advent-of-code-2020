package day08

import Util

fun main() {
    val bootCodeLines = Util().readFile("day08/input.txt").reader().readLines()
    val instructions = bootCodeLines.map { Instruction(it) }
    val program = Program()
    val result = program.runProgram(instructions)
    println(result)
}

class Program() {
    private var accumulator: Int = 0
    internal var instructionPointer: Int = 0
    private val interpreter = mapOf(
        "acc" to { value: Int ->
            accumulator += value
            instructionPointer++
        },
        "jmp" to { value: Int -> instructionPointer += value },
        "nop" to { instructionPointer++ }
    )
    internal var instructionsDone: MutableSet<Int> = mutableSetOf()

    fun runProgram(instructions: List<Instruction>): Int {
        do {
            instructionsDone.add(instructionPointer)
            val instruction = instructions[instructionPointer]
            interpreter[instruction.operation]?.let { it(instruction.argument) }
        } while (!instructionsDone.contains(instructionPointer) && instructionPointer < instructions.size)
        return accumulator
    }

    fun resetRegisters() {
        accumulator = 0
        instructionPointer = 0
        instructionsDone = mutableSetOf()
    }
}

data class Instruction(var operation: String, val argument: Int) {
    constructor(line: String) : this(line.split(" ")[0], line.split(" ")[1].toInt())
}