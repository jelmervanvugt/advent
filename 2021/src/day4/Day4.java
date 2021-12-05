package day4;

import parser.AdventParser;

import java.util.List;

public class Day4 {

    private static AdventParser ap = new AdventParser("src/day4/input.txt");

    public static void main(String[] args) {

        List<Integer> drawOrder = ap.day4DrawOrder();
        List<BingoChart> charts = ap.day4BingoCharts();

        System.out.println(charts);

        System.out.println("Solution day4 (part 1): " + part1(drawOrder, charts));
        System.out.println("Solution day4 (part 2): " + part2(drawOrder, charts));
    }

    private static int part1(List<Integer> drawOrder, List<BingoChart> charts) {

        int score = 0;

        for (Integer i : drawOrder) {
            for (BingoChart b : charts) {
                score = b.mark(i);
                if (score != 0) {
                    return score;
                }
            }
        }

        return 0;
    }

    private static int part2(List<Integer> drawOrder, List<BingoChart> charts) {

        int score = 0;
        int finished = 0;

        for (Integer i : drawOrder) {
            for (BingoChart b : charts) {
                if (!b.getIsFinished()) {

                    score = b.mark(i);
                    if (score != 0) {
                        finished++;
                        if (finished == charts.size() - 1) {
                            return score;
                        }
                    }
                }
            }
        }

        return 0;
    }


}
