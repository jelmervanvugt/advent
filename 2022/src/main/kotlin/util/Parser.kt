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
}