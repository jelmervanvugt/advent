package day04

import util.Day
import util.Parser

class Day04(private val parser: Parser = Parser("day04"), private val input: List<List<Int>> = parser.day04()) : Day {

    override fun solvePt1() {

        var overlap = 0

        input.forEach {
            if (((it[0] <= it[2] && it[1] >= it[3]) || (it[0] >= it[2] && it[1] <= it[3]))) overlap++
        }
        println(overlap)
    }

    override fun solvePt2() {
        var overlap = 0

        input.forEach {
            if (!(it[0] > it[3] || it[2] > it[1])) overlap++
        }
        println(overlap)
    }

}