package day01

import util.Day
import util.Parser

class Day01(private val parser: Parser = Parser("day01"), private val input: List<List<Int>> = parser.day1()) : Day {

    override fun solvePt1() {

        var max = input[0].sum()

        input.forEach {
            val sum = it.sum()
            if (sum > max) {
                max = sum
            }
        }
        println(max)
    }

    override fun solvePt2() {
        val caloriesTotal = ArrayList<Int>()

        input.forEach {
            caloriesTotal.add(it.sum())
        }
        caloriesTotal.sortDescending()
        println(caloriesTotal.subList(0, 3).sum())

    }
}