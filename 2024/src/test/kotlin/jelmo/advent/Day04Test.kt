package jelmo.advent

import jelmo.advent.day04.Day04
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class Day04Test {

    @Autowired
    private lateinit var sut: Day04

    @Test
    fun `solve part one`() =
        assertEquals(2543, sut.solvePart1())

    @Test
    fun `solve part two`() =
        assertEquals(9, sut.solvePart2())
}
