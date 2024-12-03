package jelmo.advent.day03

import jelmo.advent.util.Day
import jelmo.advent.util.Util
import org.springframework.stereotype.Service

@Service
class Day03 : Day {

    companion object {
        private const val DO = "do()"
        private const val DONT = "don't()"
    }

    private val input = readInput()

    override fun solvePart1(): Int = getMultiplications().calculateSum()

    override fun solvePart2(): Int {
        val ranges = createRanges(getDosAndDonts())
        return getMultiplications()
            .filter { mul -> !ranges.any { mul.first in it } }
            .calculateSum()
    }

    private fun createRanges(dosAndDonts: List<Pair<Int, String>>): List<IntRange> {

        val ranges = mutableListOf<IntRange>()
        var previousDont: Int? = null

        dosAndDonts.forEach {
            if (it.second == DONT && previousDont == null) {
                previousDont = it.first
            }
            if (it.second == DO && previousDont != null) {
                ranges.add(previousDont!!..it.first)
                previousDont = null
            }
        }

        if (previousDont != null) {
            ranges.add(previousDont!!..input.length)
        }

        return ranges
    }

    private fun getMultiplications() = Regex("mul\\((\\d{1,3},\\d{1,3})\\)")
        .findAll(input)
        .map { Pair(it.range.first, it.groupValues[1]) }
        .map { Pair(it.first, it.second.split(",").map { it.toInt() }) }

    private fun Sequence<Pair<Int, List<Int>>>.calculateSum() = this.map { it.second[0] * it.second[1] }.sum()

    private fun getDosAndDonts() =
        Regex("$DO\\(\\)|$DONT\\(\\)").findAll(input).map { Pair(it.range.first, it.groupValues[0]) }.toList()

    override fun readInput(): String =
        Util.getInputStream("day03").bufferedReader().use { it.readText() }
}
