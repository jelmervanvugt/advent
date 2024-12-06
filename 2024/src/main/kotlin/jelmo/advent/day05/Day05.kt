package jelmo.advent.day05

import jelmo.advent.util.Day
import jelmo.advent.util.Util
import org.springframework.stereotype.Service

@Service
class Day05 : Day {

    val input = readInput()

    override fun solvePart1(): Int =
        parseRules().let { rules ->
            parseUpdates().filter { checkRulesForUpdate(it, rules) }.sumOf { it[it.size / 2] }
        }

    override fun solvePart2(): Int = parseRules().let { rules ->
        parseUpdates().filter { !checkRulesForUpdate(it, rules) }.map { fixUpdate(it.toMutableList(), rules) }
            .sumOf { it[it.size / 2] }
    }

    private fun fixUpdate(update: MutableList<Int>, rules: List<Pair<Int, Int>>): List<Int> {
        var fixed = false
        do {
            rules.forEach { rule ->
                val positions = update.withIndex().associate { it.value to it.index }
                val a = positions[rule.first] ?: return@forEach
                val b = positions[rule.second] ?: return@forEach

                if (a > b) {
                    update.add(a + 1, rule.second)
                    update.removeAt(b)
                }
            }

            if (checkRulesForUpdate(update, rules)) fixed = true
        } while (!fixed)
        return update
    }

    private fun checkRulesForUpdate(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
        rules.forEach { rule ->
            val positions = update.withIndex().associate { it.value to it.index }
            val a = positions[rule.first] ?: return@forEach
            val b = positions[rule.second] ?: return@forEach

            if (a > b) {
                return false
            }
        }
        return true
    }

    private fun parseUpdates() = input
        .filter { it.contains(",") }
        .map {
            it.split(",").map(String::toInt)
        }

    private fun parseRules() = input
        .filter { it.contains("|") }
        .map {
            val (first, second) = it.split("|").map(String::toInt)
            first to second
        }

    override fun readInput() = Util.converInputToStringList(Util.getInputStream("day05"))
}
