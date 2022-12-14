package day07

import util.Day
import util.Parser

class Day07(
    private val parser: Parser = Parser("day07"),
    private val input: List<List<String>> = parser.day07(),
    private val root: Directory = Directory("root", null)

) : Day {

    init {
        buildDirectories(root)
        calcSizes(root)
    }

    override fun solvePt1() {
        println(findDirectories(root, 100000))
    }

    override fun solvePt2() {
        println(deleteDirectory(root, root.size - 40000000))
    }

    private fun buildDirectories(root: Directory) {
        var current = root

        input.forEach {

            if (it.contains("cd")) {
                current = if (it[2] == "/") {
                    root
                } else if (it[2] == "..") {
                    current.previous!!
                } else {
                    current.directories.first { dir -> dir.name == it[2] }
                }
            } else if (it.contains("dir")) {
                current.directories.add(Directory(it[1], current))
            } else if (!it.contains("ls")) {
                current.files.add(File(it[1], it[0].toDouble()))
            }
        }
    }

    private fun calcSizes(current: Directory): Double {
        var size = 0.0

        current.files.forEach { size += it.size }
        current.directories.forEach { size += calcSizes(it) }

        current.size = size
        return size
    }

    private fun findDirectories(current: Directory, maxSize: Int): Double {

        var size = 0.0

        if (current.directories.size != 0) {
            current.directories.forEach {
                size += findDirectories(it, maxSize)
            }
        }

        if (current.size < maxSize) {
            size += current.size
        }
        return size
    }

    private fun deleteDirectory(current: Directory, space: Double): Double? {

        if (current.size < space) return -1.0
        val dirs = mutableListOf(current.size)

        current.directories.forEach {
            deleteDirectory(it, space)?.let { it1 -> dirs.add(it1) }
        }
        return dirs.filter { it != -1.0 }.minOrNull()
    }
}