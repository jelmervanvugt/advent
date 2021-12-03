package day3;

import parser.AdventParser;

import java.util.ArrayList;
import java.util.List;

public class Day3 {

    private static AdventParser ap = new AdventParser("src/day3/input.txt");

    public static void main(String[] args) {

        List<String> input = ap.day3();

        System.out.println("Solution day3 (part 1): " + part1(input));
        System.out.println("Solution day3 (part 2): " + part2(input));
    }

    private static int part1(List<String> l) {

        int[] bytes = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (String s : l) {
            for (int i = 0; i < bytes.length; i++) {
                if (s.charAt(i) == '0') {
                    bytes[i]--;
                } else {
                    bytes[i]++;
                }
            }
        }

        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] > 0) {
                gamma += '1';
                epsilon += '0';
            } else {
                gamma += '0';
                epsilon += '1';
            }
        }

        return byteToInteger(gamma, 1, gamma.length()) * byteToInteger(epsilon, 1, gamma.length());
    }

    private static int part2(List<String> l) {
        String oxygen = findRating(l, true);
        String co2 = findRating(l, false);

        return byteToInteger(oxygen, 1, oxygen.length()) * byteToInteger(co2, 1, co2.length());
    }

    private static int byteToInteger(String b, int val, int i) {
        if (i == 0) {
            return 0;
        } else if (b.charAt(i - 1) == '0') {
            return byteToInteger(b, val * 2, i - 1);
        }
        return val + byteToInteger(b, val * 2, i - 1);
    }

    private static String findRating(List<String> input, boolean findOxygen) {

        int i = 0;
        List<String> l = new ArrayList<>(input);
        List<String> zeroes = new ArrayList<>();
        List<String> ones = new ArrayList<>();

        while (l.size() > 1) {

            for (String s : l) {
                if (s.charAt(i) == '1') {
                    ones.add(s);
                } else {
                    zeroes.add(s);
                }
            }

            if (findOxygen) {
                if (zeroes.size() <= ones.size()) {
                    l.clear();
                    l.addAll(ones);
                } else {
                    l.clear();
                    l.addAll(zeroes);
                }
            } else {
                if (zeroes.size() <= ones.size()) {
                    l.clear();
                    l.addAll(zeroes);
                } else {
                    l.clear();
                    l.addAll(ones);
                }
            }

            zeroes.clear();
            ones.clear();
            i++;
        }
        return l.get(0);
    }

}
