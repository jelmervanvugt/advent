package util

import java.io.File
import java.util.*

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
}