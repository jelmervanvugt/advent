package day8;

import parser.AdventParser;

import java.util.List;


public class Day8 {

    private static AdventParser ap = new AdventParser("src/day8/input.txt");

    public static void main(String[] args) {

        List<Segment> input = ap.day8();

        System.out.println("Solution day8 (part 1): " + part1(input, true));
        System.out.println("Solution day8 (part 2): " + part2(input));
    }

    private static long part1(List<Segment> input, boolean pt1) {

        SegmentSolver ss = new SegmentSolver();
        int total = 0;

        for (Segment s : input) {
            ss.setSegment(s);
            if (pt1) {
                total += ss.solvePart1();
            } else {
                total += ss.solvePart2();
            }
        }

        return total;
    }

    private static long part2(List<Segment> input) {
        return part1(input, false);
    }

}
