package jelmo.advent

import jelmo.advent.day01.Day01
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class Day01Test {

    @Autowired
    private lateinit var sut: Day01

    @Test
    fun `solve part one`() =
        assertEquals(3574690, sut.solvePart1())

    @Test
    fun `solve part two`() =
        assertEquals(22565391, sut.solvePart2())
}
