package day1;

import parser.AdventParser;

import java.util.List;

public class Day1 {

    private static AdventParser ap = new AdventParser("src/day1/input.txt");

    public static void main(String[] args) {

        List<Integer> input = ap.day1();

        System.out.println("Solution day1 (part 1): " + part1(input));
        System.out.println("Solution day1 (part 2): " + part2(input));
    }

    private static int part1(List<Integer> l) {
        int pDepth = l.get(0);
        int nIncrements = 0;

        for (Integer i : l) {
            if (i > pDepth) {
                nIncrements++;
            }
            pDepth = i;
        }
        return nIncrements;
    }

    private static int part2(List<Integer> l) {

        int pSum = l.get(0) + l.get(1) + l.get(2);
        int nIncrements = 0;
        int tmpSum;

        for (int i = 1; i < l.size(); i++) {

            if (l.size() < i + 3) {
                return nIncrements;
            }

            tmpSum = l.get(i) + l.get(i + 1) + l.get(i + 2);

            if (tmpSum > pSum) {
                nIncrements++;
            }
            pSum = tmpSum;
        }

        return nIncrements;
    }

}
