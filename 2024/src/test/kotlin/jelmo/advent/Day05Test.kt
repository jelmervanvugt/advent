package jelmo.advent

import jelmo.advent.day05.Day05
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class Day05Test {

    @Autowired
    private lateinit var sut: Day05

    @Test
    fun `solve part one`() =
        assertEquals(6498, sut.solvePart1())

    @Test
    fun `solve part two`() =
        assertEquals(5017, sut.solvePart2())
}
