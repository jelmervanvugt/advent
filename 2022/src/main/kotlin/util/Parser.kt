package util

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Parser(
    private val dayName: String,
    private val path: String = "src/main/kotlin/${dayName}/input.txt",
    private val file: File = File(path),
    private val scanner: Scanner = Scanner(file)
) {

    fun day01(): List<List<Int>> {
        val elves = ArrayList<List<Int>>()
        var calories = ArrayList<Int>()

        while (scanner.hasNextLine()) {

            val line = scanner.nextLine()

            if (line.equals("")) {
                elves.add(calories)
                calories = ArrayList<Int>()
            } else {
                calories.add(line.toInt())
            }
        }
        return elves
    }

    fun day02(): List<String> {

        val strategy = ArrayList<String>()

        while (scanner.hasNextLine()) {
            strategy.add(scanner.nextLine().replace(" ", ""))
        }
        return strategy
    }

    fun day03(): List<String> {

        val rucksacks = ArrayList<String>()

        while (scanner.hasNextLine()) {
            rucksacks.add(scanner.nextLine())
        }
        return rucksacks
    }

    fun day04(): List<List<Int>> {

        val pairs = ArrayList<ArrayList<Int>>()

        while (scanner.hasNextLine()) {
            pairs.add(scanner.nextLine().split("-", ",").map { it.toInt() } as ArrayList<Int>)
        }
        return pairs
    }

    fun day05(): List<String> {

        val crateInput = ArrayList<String>()

        while (scanner.hasNextLine()) {
            crateInput.add(scanner.nextLine())
        }
        return crateInput
    }

    fun day06(): String {
        return scanner.nextLine()
    }

    fun day07(): List<List<String>> {

        val commands = ArrayList<List<String>>()

        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "))
        }
        return commands
    }

    fun day08(): List<List<Int>> {

        val grid = ArrayList<List<Int>>()

        while (scanner.hasNextLine()) {
            grid.add(scanner.nextLine().map { it.toString().toInt() })
        }
        return grid
    }

    fun day09(): List<List<String>> {

        val moves = ArrayList<List<String>>()

        while (scanner.hasNextLine()) {
            val move = scanner.nextLine().split(" ")
            moves.add(listOf(move[0], move[1]))
        }
        return moves
    }

    fun day10(): List<List<String>> {

        val instructions = ArrayList<List<String>>()

        while (scanner.hasNextLine()) {
            val instruction = scanner.nextLine().split(" ")
            if (instruction.size == 2) {
                instructions.add(listOf(instruction[0], instruction[1]))
            } else {
                instructions.add(listOf(instruction[0]))
            }
        }
        return instructions
    }
}