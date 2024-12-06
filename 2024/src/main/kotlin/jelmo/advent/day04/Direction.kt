package jelmo.advent.day04

sealed class Direction(val x: Int, val y: Int) {
    class North : Direction(0, -1)
    class East : Direction(1, 0)
    class South : Direction(0, 1)
    class West : Direction(-1, 0)
    class NorthEast : Direction(1, -1)
    class SouthEast : Direction(1, 1)
    class SouthWest : Direction(-1, 1)
    class NorthWest : Direction(-1, -1)
}
