package jelmo.advent.util

import java.io.InputStream

class Util {

    companion object {

        fun getInputStream(day: String): InputStream =
            javaClass.classLoader.getResourceAsStream("input/$day")
                ?: throw IllegalArgumentException("Input file not found.")

        fun convertInputToIntList(inputStream: InputStream): List<Int> =
            inputStream.bufferedReader().useLines { lines ->
                lines.flatMap { line ->
                    line.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }
                }.toList()
            }

        fun convertInputToIntListList(inputStream: InputStream): List<List<Int>> =
            inputStream.bufferedReader().useLines { lines ->
                lines.map { line ->
                    line.split(" ")
                        .mapNotNull { it.toIntOrNull() }
                }.toList()
            }

        fun converInputToStringList(inputStream: InputStream): List<String> =
            inputStream.bufferedReader().lines().toList()
    }
}
