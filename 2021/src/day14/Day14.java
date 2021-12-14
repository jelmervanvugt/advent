package day14;

import parser.AdventParser;

public class Day14 {

    private static AdventParser ap = new AdventParser("src/day14/input.txt");

    public static void main(String[] args) {

        Polymer input = ap.day14();
        String originalPolymer = String.valueOf(input.polymer);

        System.out.println("Solution day14 (part 1): " + part1(input, 10));
        System.out.println("Solution day14 (part 2): " + part2(input, originalPolymer));
    }

    private static long part1(Polymer input, int nDays) {

        input.doSteps(nDays);
        long occursMost = -1;
        long occursLeast = -1;

        for (String key : input.rules.keySet()) {

            String tmp = input.polymer;
            String element = input.rules.get(key);

            long count = tmp.length() - tmp.replaceAll(element, "").length();

            if (occursMost == -1 || count > occursMost) occursMost = count;
            if (occursLeast == -1 || count < occursLeast) occursLeast = count;
        }
        return occursMost - occursLeast;
    }


    private static long part2(Polymer input, String originalPolymer) {
        input.polymer = originalPolymer;
        return part1(input, 40);
    }

}
