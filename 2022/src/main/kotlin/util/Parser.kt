package util

import java.io.File
import java.util.*

class Parser(
    private val dayName: String,
    private val path: String = "src/main/kotlin/${dayName}/input.txt",
    private val file: File = File(path),
    private val scanner: Scanner = Scanner(file)
) {

    fun day1(): List<List<Int>> {
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
}