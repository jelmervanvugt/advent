package day5;

import parser.AdventParser;

import java.util.List;

public class Day5 {

    private static AdventParser ap = new AdventParser("src/day5/input.txt");

    public static void main(String[] args) {

        List<Line> input = ap.day5();

        System.out.println("Solution day5 (part 1): " + part1(input, true));
        System.out.println("Solution day5 (part 2): " + part2(input));
    }

    private static int part1(List<Line> lines, boolean partOne) {

        int maxX = calcMaxCoordinate(true, lines);
        int maxY = calcMaxCoordinate(false, lines);
        int[][] plane = buildPlane(lines, maxX, maxY, partOne);
        int nIntersections = 0;

        for (int[] ints : plane) {
            for (int anInt : ints) {
                if (anInt > 1) nIntersections++;
            }
        }

        return nIntersections;
    }

    private static int calcMaxCoordinate(boolean x, List<Line> lines) {

        int max = 0;

        if (x) {
            for (Line l : lines) {
                if (l.xStart > max) max = l.xStart;
                if (l.xEnd > max) max = l.xEnd;
            }
        } else {
            for (Line l : lines) {
                if (l.yStart > max) max = l.yStart;
                if (l.yEnd > max) max = l.yEnd;
            }
        }
        return max;
    }


    private static int[][] buildPlane(List<Line> lines, int maxX, int maxY, boolean partOne) {

        int[][] plane = new int[maxX + 1][maxY + 1];

        for (Line l : lines) {

            if (l.xStart == l.xEnd || l.yStart == l.yEnd) {
                for (int x = l.xStart; x < l.xEnd + 1; x++) {
                    if (l.yStart < l.yEnd) {
                        for (int y = l.yStart; y < l.yEnd + 1; y++) {
                            plane[x][y] += 1;
                        }
                    } else {
                        for (int y = l.yEnd; y < l.yStart + 1; y++) {
                            plane[x][y] += 1;
                        }
                    }
                }
            } else if (!partOne) {
                int diff = l.xEnd - l.xStart + 1;

                if (l.yStart < l.yEnd) {
                    for (int i = 0; i < diff; i++) {
                        plane[l.xStart + i][l.yStart + i] += 1;
                    }
                } else {
                    for (int i = 0; i < diff; i++) {
                        plane[l.xStart + i][l.yStart - i] += 1;
                    }
                }
            }
        }

        return plane;
    }

    private static int part2(List<Line> lines) {

        return part1(lines, false);
    }


}
