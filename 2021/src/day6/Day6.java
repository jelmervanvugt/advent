package day6;

import parser.AdventParser;

import java.util.HashMap;


public class Day6 {

    private static AdventParser ap = new AdventParser("src/day6/input.txt");

    public static void main(String[] args) {

        HashMap<Integer, Long> input = ap.day6();

        System.out.println("Solution day6 (part 1): " + part1(input, 80));
        System.out.println("Solution day6 (part 2): " + part2(input));
    }

    private static long part1(HashMap<Integer, Long> input, int days) {

        HashMap<Integer, Long> hm = input;

        int day = 0;

        do {
            hm = incrementFish(hm);
            day++;

        } while (day < days);

        return calcTotal(hm);
    }

    private static HashMap<Integer, Long> incrementFish(HashMap<Integer, Long> input) {

        HashMap<Integer, Long> hm = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            if (i != 0 && i != 7) {
                hm.put(i - 1, input.get(i));
            }
        }
        hm.put(8, input.get(0));
        hm.put(6, input.get(7) + input.get(0));

        return hm;
    }

    private static long calcTotal(HashMap<Integer, Long> input) {
        return input.get(0) +
                input.get(1) +
                input.get(2) +
                input.get(3) +
                input.get(4) +
                input.get(5) +
                input.get(6) +
                input.get(7) +
                input.get(8);
    }

    private static long part2(HashMap<Integer, Long> input) {
        return part1(input, 256);
    }
}
