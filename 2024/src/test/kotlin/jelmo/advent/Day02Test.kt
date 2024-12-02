package jelmo.advent

import jelmo.advent.day02.Day02
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class Day02Test {

    @Autowired
    private lateinit var sut: Day02

    @Test
    fun `solve part one`() =
        assertEquals(369, sut.solvePart1())

    @Test
    fun `solve part two`() =
        assertEquals(428, sut.solvePart2())
}
