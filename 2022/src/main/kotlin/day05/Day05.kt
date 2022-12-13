package day05

import util.Day
import util.Parser
import java.util.*
import kotlin.collections.ArrayList

class Day05(
    private val parser: Parser = Parser("day05"),
    private val input: List<String> = parser.day05(),
    private val crates: ArrayList<Stack<Char>> = ArrayList(),
    private var moves: ArrayList<List<Int>> = ArrayList()
) : Day {

    override fun solvePt1() {
        convertInput()

        moves.forEach {
            moveCrate(it[0], it[1], it[2])
        }

        crates.forEach {
            print("[${it.peek()}] ")
        }

    }

    override fun solvePt2() {
        convertInput()

        moves.forEach {

            if (it[0] > 1) {
                moveMultipleCrates(it[0], it[1], it[2])
            } else {
                moveCrate(it[0], it[1], it[2])
            }


        }

        crates.forEach {
            print("[${it.peek()}] ")
        }
    }

    private fun convertInput() {

        for (x in 1 until 34 step 4) {

            val stack = Stack<Char>()

            for (y in 7 downTo 0) {
                if (input[y][x] != ' ') stack.push(input[y][x])
            }
            crates.add(stack)
        }

        for (i in 10 until input.size) {

            val move = input[i].split(" ")

            moves.add(
                listOf(move[1].toInt(), move[3].toInt(), move[5].toInt())
            )
        }
    }

    private fun moveCrate(amount: Int, from: Int, to: Int) {
        for (i in 0 until amount) {
            crates[to - 1].push(crates[from - 1].pop())
        }
    }

    private fun moveMultipleCrates(amount: Int, from: Int, to: Int) {

        val toMove = ArrayList<Char>()

        for (i in 0 until amount) {
            toMove.add(crates[from - 1].pop())
        }
        toMove.reverse()

        toMove.forEach {
            crates[to - 1].push(it)
        }
    }

}