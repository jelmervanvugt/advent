package jelmo.advent.day01

import jelmo.advent.Day
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class Day01 : Day {

    private val input = readInput()

    override fun solvePart1(): Int {
        val locations = getSortedLocations()
        return calculateTotalDistance(locations.first, locations.second)
    }

    override fun solvePart2(): Int {
        val locations = getSortedLocations()
        val frequencies = locations.second.groupingBy { it }.eachCount()

        return locations.first
            .filter { frequencies.containsKey(it) }
            .sumOf { it * frequencies.getValue(it) }
    }

    private fun getSortedLocations(): Pair<List<Int>, List<Int>> {
        val (oddIndices, evenIndices) = input.withIndex().partition { it.index % 2 == 0 }
        return Pair(oddIndices.map { it.value }.sorted(), evenIndices.map { it.value }.sorted())
    }


    private fun calculateTotalDistance(locations1: List<Int>, locations2: List<Int>): Int =
        locations1.zip(locations2).sumOf { (loc1, loc2) -> abs(loc1 - loc2) }


    override fun readInput(): List<Int> {
        val resource = javaClass.classLoader.getResourceAsStream("input/day01")
            ?: throw IllegalArgumentException("Input file not found.")

        return resource.bufferedReader().useLines { lines ->
            lines.flatMap { line ->
                line.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }
            }.toList()
        }
    }
}
