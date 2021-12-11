package day11;

import parser.AdventParser;

public class Day11 {

    private static AdventParser ap = new AdventParser("src/day11/input.txt");

    public static void main(String[] args) {

        int[][] input = ap.day11();

        System.out.println("Solution day10 (part 1): " + part1(input));
        System.out.println("Solution day10 (part 2): " + part2(input));
    }

    private static int part1(int[][] input) {

        int nFlashes = 0;

        for (int i = 0; i < 100; i++) {
            nFlashes += doStep(input, true);
            resetGrid(input);
        }

        return nFlashes;
    }

    private static int doStep(int[][] input, boolean firstPass) {

        int nFlashes = 0;

        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input[x].length; y++) {
                if (firstPass) input[x][y] += 1;
                if (input[x][y] > 9) {
                    incNeighbours(input, x, y);
                    input[x][y] = -1;
                    nFlashes++;
                }
            }
        }

        if (nFlashes != 0) {
            return nFlashes + doStep(input, false);
        }
        return 0;
    }

    private static void resetGrid(int[][] input) {
        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input[x].length; y++) {
                if (input[x][y] == -1) input[x][y] = 0;
            }
        }
    }

    private static void incNeighbours(int[][] input, int x, int y) {
        if (x != 0 && input[x - 1][y] != -1) input[x - 1][y] += 1;
        if (x != 0 && y != input[y].length - 1 && input[x - 1][y + 1] != -1) input[x - 1][y + 1] += 1;
        if (y != input[y].length - 1 && input[x][y + 1] != -1) input[x][y + 1] += 1;
        if (x != input.length - 1 && y != input[y].length - 1 && input[x + 1][y + 1] != -1) input[x + 1][y + 1] += 1;
        if (x != input.length - 1 && input[x + 1][y] != -1) input[x + 1][y] += 1;
        if (x != input.length - 1 && y != 0 && input[x + 1][y - 1] != -1) input[x + 1][y - 1] += 1;
        if (y != 0 && input[x][y - 1] != -1) input[x][y - 1] += 1;
        if (x != 0 && y != 0 && input[x - 1][y - 1] != -1) input[x - 1][y - 1] += 1;
    }


    private static int part2(int[][] input) {

        int nFlashes = 0;
        int pass = 0;

        while (nFlashes != input.length * input.length) {
            nFlashes = 0;
            pass++;
            doStep(input, true);

            for (int[] ints : input) {
                for (int anInt : ints) {
                    if (anInt == -1) nFlashes++;
                }
            }
            resetGrid(input);
        }
        return pass;
    }

}
