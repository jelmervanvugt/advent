package day09

import util.Day
import util.Parser
import kotlin.math.absoluteValue

class Day09(
    private val parser: Parser = Parser("day09"),
    private val input: List<List<String>> = parser.day09(),
    private var knots: MutableList<IntArray> = mutableListOf(),
    private val path: MutableList<IntArray> = mutableListOf()
) : Day {

    override fun solvePt1() {
        init(2)

        input.forEach { move ->
            repeat(move[1].toInt()) {
                doMove(move[0])
            }
        }

        println(path.size)
    }

    override fun solvePt2() {
        init(10)

        input.forEach { move ->
            repeat(move[1].toInt()) {
                doMove(move[0])
            }
        }

        println(path.size)
    }


    private fun doMove(dir: String) {

        when (dir) {
            "U" -> knots[0][1]++
            "D" -> knots[0][1]--
            "L" -> knots[0][0]--
            "R" -> knots[0][0]++
        }

        for (i in 0 until knots.size - 1) {

            if (!outOfReach(knots[i], knots[i + 1])) {
                break
            }
            doMove(knots[i], knots[i + 1])
        }

        val tail = intArrayOf(
            knots.last()[0],
            knots.last()[1]
        )

        if (path.find { it[0] == tail[0] && it[1] == tail[1] } == null) {
            path.add(tail)
        }
    }

    private fun doMove(knot1: IntArray, knot2: IntArray) {
        if (knot1[0] == knot2[0]) {
            knot2[1] += if (knot1[1] > knot2[1]) 1 else -1
        } else if (knot1[1] == knot2[1]) {
            knot2[0] += if (knot1[0] > knot2[0]) 1 else -1
        } else {
            knot2[0] += if (knot1[0] > knot2[0]) 1 else -1
            knot2[1] += if (knot1[1] > knot2[1]) 1 else -1
        }
    }

    private fun init(knotAmount: Int) {
        path.add(intArrayOf(0, 0))

        repeat(knotAmount) {
            knots.add(intArrayOf(0, 0))
        }
    }

    private fun outOfReach(knot1: IntArray, knot2: IntArray): Boolean {
        return (knot1[0] - knot2[0]).absoluteValue > 1 || (knot1[1] - knot2[1]).absoluteValue > 1
    }
}