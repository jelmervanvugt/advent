package jelmo.advent.day02

import jelmo.advent.util.Day
import jelmo.advent.util.Util
import org.springframework.stereotype.Service
import kotlin.math.abs

@Service
class Day02 : Day {

    private val input = readInput()

    override fun solvePart1(): Int = input
        .filter { it.checkRule1() }
        .count { it.checkRule2() }

    override fun solvePart2(): Int = input
        .filter { !it.checkRule1() || !it.checkRule2() }
        .map { it.generateSubsetsExcludingOne() }
        .filter { subsets -> subsets.any { it.checkRule1() && it.checkRule2() } }
        .size + solvePart1()

    fun List<Int>.generateSubsetsExcludingOne(): List<List<Int>> =
        List(this.size) { index ->
            this.filterIndexed { i, _ -> i != index }
        }

    fun List<Int>.checkRule1(): Boolean = this
        .windowed(2)
        .map { (a, b) -> abs(a - b) }
        .all { gap -> gap in 1..3 }

    fun List<Int>.checkRule2(): Boolean = this == this.sorted() || this == this.sortedDescending()

    override fun readInput(): List<List<Int>> =
        Util.convertInputToIntListList(Util.getInputStream("day02"))
}
