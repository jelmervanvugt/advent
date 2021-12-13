package day13;

import parser.AdventParser;

public class Day13 {

    private static AdventParser ap = new AdventParser("src/day13/input.txt");

    public static void main(String[] args) {

        Origami input = ap.day13();

        System.out.println("Solution day13 (part 1): " + part1(input));
        System.out.println("Solution day13 (part 2): " + part2(input));
    }

    private static int part1(Origami input) {
        input.doFolds();
        return input.countNDots();
    }


    private static String part2(Origami input) {

        StringBuilder str = new StringBuilder("\n");

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < input.plane[x].length; y++) {
                if (input.plane[x][y] == 1) {
                    str.append("#");
                } else {
                    str.append(" ");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

}
