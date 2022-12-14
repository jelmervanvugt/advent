package day08

import util.Day
import util.Parser

class Day08(
    private val parser: Parser = Parser("day08"),
    private val input: List<List<Int>> = parser.day08()
) : Day {
    override fun solvePt1() {
        println(traverseTree(true))
    }

    override fun solvePt2() {
        println(traverseTree(false))
    }

    private fun traverseTree(pt1: Boolean): Int {

        val visible: ArrayList<String> = ArrayList()
        var scenicScore = 0

        for (y in 1 until input.size - 1) {
            for (x in 1 until input.size - 1) {

                val vertical = mutableListOf<Int>()
                val treeHeight = input[y][x]

                for (v in input.indices) {
                    vertical.add(input[v][x])
                }

                val directions = listOf(
                    vertical.subList(0, y),
                    vertical.subList(y + 1, vertical.size),
                    input[y].subList(0, x),
                    input[y].subList(x + 1, input.size)
                )

                if (pt1 && (!directions[0].any { it >= treeHeight } || !directions[1].any { it >= treeHeight } || !directions[2].any { it >= treeHeight } || !directions[3].any { it >= treeHeight })) {
                    if (!visible.contains("$x,$y")) visible.add("$x,$y")
                } else {

                    val correctedDirections = listOf(
                        directions[0].asReversed(),
                        directions[1],
                        directions[2].asReversed(),
                        directions[3]
                    )

                    val scenicScores = ArrayList<Int>()

                    correctedDirections.forEach {
                        val index = it.indexOfFirst { s -> s >= treeHeight }
                        if (index == -1) {
                            scenicScores.add(it.size)
                        } else {
                            scenicScores.add(index + 1)
                        }
                    }
                    val score = scenicScores[0] * scenicScores[1] * scenicScores[2] * scenicScores[3]

                    if (score > scenicScore) {
                        scenicScore = score
                    }
                }
            }
        }

        if (pt1) return input.size * 4 - 4 + visible.size
        return scenicScore
    }

}