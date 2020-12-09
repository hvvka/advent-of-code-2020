package day08

import Util

fun main() {
    val bootCodeLines = Util().readFile("day08/input.txt").reader().readLines()
    val program = Program()
    val instructions = bootCodeLines.map { Instruction(it) }
    var changeLineIndex = 0
    var result: Int
    do {
        while (instructions[changeLineIndex].operation == "acc") {
            changeLineIndex++
        }
        val instructionsCopy = instructions.map { it.copy() }
        if (instructions[changeLineIndex].operation == "jmp") instructionsCopy[changeLineIndex].operation = "nop"
        else if (instructions[changeLineIndex].operation == "nop") instructionsCopy[changeLineIndex].operation = "jmp"
        changeLineIndex++
        result = program.runProgram(instructionsCopy)
    } while (!program.hasTerminatedGracefully())
    println(result)
}

fun Program.hasTerminatedGracefully(): Boolean {
    val result = !instructionsDone.contains(instructionPointer)
    this.resetRegisters()
    return result
}
