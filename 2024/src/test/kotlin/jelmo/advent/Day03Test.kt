package jelmo.advent

import jelmo.advent.day03.Day03
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class Day03Test {

    @Autowired
    private lateinit var sut: Day03

    @Test
    fun `solve part one`() =
        assertEquals(188741603, sut.solvePart1())

    @Test
    fun `solve part two`() =
        assertEquals(67269798, sut.solvePart2())
}
