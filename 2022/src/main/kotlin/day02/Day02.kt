package day02

import util.Day
import util.Parser

class Day02(private val parser: Parser = Parser("day02"), private val input: List<String> = parser.day02()) : Day {

    private val scores = mapOf(
        "AY" to 6,
        "BZ" to 6,
        "CX" to 6,
        "AX" to 3,
        "BY" to 3,
        "CZ" to 3,
        "AZ" to 0,
        "BX" to 0,
        "CY" to 0,
        "X" to 1,
        "Y" to 2,
        "Z" to 3
    )

    private val logic = mapOf(
        "AX" to "Z",
        "AY" to "X",
        "AZ" to "Y",
        "BX" to "X",
        "BY" to "Y",
        "BZ" to "Z",
        "CX" to "Y",
        "CY" to "Z",
        "CZ" to "X"
    )

    override fun solvePt1() {
        println(calcScore(input))
    }

    override fun solvePt2() {

        val outcome = ArrayList<String>()

        input.forEach {
            outcome.add(it[0] + logic[it].toString())
        }
        println(calcScore(outcome))
    }

    private fun calcScore(moves: List<String>): Int {

        var score = 0

        moves.forEach {
            score += scores[it]!!
            score += scores[it[1].toString()]!!
        }
        return score
    }

}