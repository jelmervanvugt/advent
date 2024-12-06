package jelmo.advent.day04

import jelmo.advent.util.Day
import jelmo.advent.util.Util
import org.springframework.stereotype.Service

@Service
class Day04 : Day {

    val input = readInput()

    override fun solvePart1(): Int {

        val letters = initLetters()
        val startingPoints = letters.filter { it.second == 'X' }

        val letterms = mutableListOf<Triple<Point, Direction, Char>>()

        startingPoints.forEach {
            letterms.addAll(getSecondLetters(letters, it))
        }

        return  letterms
            .filter { checkForLetter(it, 'A', letters) }
            .map {
                it.first.x += it.second.x
                it.first.y += it.second.y
                it
            }
            .filter { checkForLetter(it, 'S', letters) }
            .size
    }

    private fun checkForLetter(point: Triple<Point, Direction, Char>, letter: Char, letters: List<Pair<Point, Char>>): Boolean {

        val x = point.first.x + point.second.x
        val y = point.first.y + point.second.y

         return letters.firstOrNull { it.first.x == x && it.first.y == y && it.second == letter  } != null

    }

    override fun solvePart2(): Int {
        val letters = initLetters()
        val startingPoints = letters.filter { it.second == 'A' }.filter { isXMAS(it, letters) }



        return startingPoints.size
    }

    private fun getSecondLetters(letters: List<Pair<Point, Char>>, startingPoint: Pair<Point, Char>): List<Triple<Point, Direction, Char>> {

        val secondLetters = mutableListOf<Triple<Point, Direction, Char>>()

        val directions = listOf(
            Direction.North(),
            Direction.NorthEast(),
            Direction.East(),
            Direction.SouthEast(),
            Direction.South(),
            Direction.SouthWest(),
            Direction.West(),
            Direction.NorthWest()
        )

        directions.forEach {

            val x = startingPoint.first.x + it.x
            val y = startingPoint.first.y + it.y
            val toCheck = letters.firstOrNull { it.first.x == x && it.first.y == y }

            if(toCheck?.second == 'M') {
                secondLetters.add(
                    Triple(
                        Point(toCheck.first.x, toCheck.first.y), it, toCheck.second))
            }

        }

        return secondLetters
    }

    private fun initLetters(): List<Pair<Point, Char>> {
        val letters = mutableListOf<Pair<Point, Char>>()
        input.forEachIndexed { y, row ->
            row.toCharArray().forEachIndexed { x, letter ->
                letters.add(Pair(Point(x, y), letter))
            }
        }
        return letters
    }

    private fun isXMAS(startingPoint: Pair<Point, Char>, letters: List<Pair<Point, Char>>): Boolean {

        val directions = listOf(Direction.NorthEast(), Direction.SouthEast(), Direction.SouthWest(), Direction.NorthWest())
        val toCheck = mutableListOf<Pair<Point, Char>>()

        directions.forEach { direction ->
            letters.firstOrNull {
                it.first.x == startingPoint.first.x + direction.x && it.first.y == startingPoint.first.y + direction.y
            }?.let { toCheck.add(it) }
        }

        if(toCheck.size != 4) {
            return false
        }

        val word1 = (toCheck[0].second == 'S' && toCheck[2].second == 'M') || (toCheck[0].second == 'M' && toCheck[2].second == 'S')
        val word2 = (toCheck[1].second == 'S' && toCheck[3].second == 'M') || (toCheck[1].second == 'M' && toCheck[3].second == 'S')

        return word1 && word2
    }

    override fun readInput(): List<String> = Util.converInputToStringList(Util.getInputStream("day04"))

}
