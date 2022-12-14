package day06

import util.Day
import util.Parser

class Day06(
    private val parser: Parser = Parser("day06"),
    private val input: String = parser.day06()
) : Day {

    override fun solvePt1() {
        println(findStartOfMessage(4, input))
    }

    override fun solvePt2() {
        println(findStartOfMessage(14, input))
    }

    private fun findStartOfMessage(chunkSize: Int, datastream: String): Int {
        val arr = datastream.windowed(chunkSize, 1)
        var index = -1

        do {
            index++
        } while (arr[index].toCharArray().distinct().size != chunkSize)

        return index + chunkSize
    }
}