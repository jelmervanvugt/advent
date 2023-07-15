package day10

import util.Day
import util.Parser

class Day10(
    private val parser: Parser = Parser("day10"),
    private val input: List<List<String>> = parser.day10(),
    private var cycle: Int = 1,
    private var register: Int = 1,
    private var signal: Int = 0,
    private var pixels: MutableList<Char> = ArrayList()
) : Day {

    override fun solvePt1() {
        solve(false)
    }


    override fun solvePt2() {

        solve(true)
        printPixels()

    }

    private fun solve(pt2: Boolean) {
        input.forEach { instruction ->
            startCycle(pt2)

            if (instruction[0] == "addx") {
                cycle++
                startCycle(pt2)
                register += instruction[1].toInt()
            }
            cycle++
        }
    }

    private fun startCycle(pt2: Boolean) {
        println("Cycle: $cycle register: $register")

        if (pt2) {
            drawPixel()
        } else {
            calcSignalStrength()
        }
    }

    private fun calcSignalStrength() {
        if (cycle >= 20) {
            val res = (cycle - 20) % 40
            if (res == 0) {
                signal += cycle * register
                println("Cycle $cycle signal strength: ${cycle * register}, sum: $signal")
            }
        }
    }

    private fun drawPixel() {

        val cyclePosition = calcCyclePosition()

        if (cyclePosition == register || cyclePosition == register + 1 || cyclePosition == register + 2) {
            pixels.add('#')
        } else {
            pixels.add('.')
        }
    }

    private fun calcCyclePosition(): Int {
        val multiplier = (cycle - 1) / 40

        return if (cycle <= 40) {
            cycle
        } else {
            cycle - (multiplier * 40)
        }
    }

    private fun printPixels() {
        val screen = pixels.chunked(40)

        for (row in screen) {
            for (char in row) {
                print(char)
            }
            println()
        }
    }
}