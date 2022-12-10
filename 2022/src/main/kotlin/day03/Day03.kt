package day03

import util.Day
import util.Parser

class Day03(private val parser: Parser = Parser("day03"), private val input: List<String> = parser.day3()) : Day {

    private val alphabet = 'a'..'z'

    override fun solvePt1() {

        var sum = 0

        input.forEach {

            val middle = it.length / 2
            val compartment1 = it.subSequence(0, middle)
            val compartment2 = it.subSequence(middle, it.length)

            sum += calcPriority(compartment1.filter { c -> compartment2.contains(c) }[0])
        }
        println(sum)
    }

    override fun solvePt2() {

        var sum = 0;

        for (i in input.indices step 3) {

            val item1 = input[i].toCharArray()
            val item2 = input[i + 1].toCharArray()
            val item3 = input[i + 2].toCharArray()

            sum += calcPriority(item1.filter { c1 -> item2.filter { c2 -> item3.contains(c2) }.contains(c1) }[0])
        }
        println(sum)
    }

    private fun calcPriority(item: Char): Int {
        var sum = 0

        if (!item.isLowerCase()) sum += 26
        sum += alphabet.indexOf(item.toLowerCase()) + 1

        return sum
    }

}