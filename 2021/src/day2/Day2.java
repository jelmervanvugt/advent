package day2;

import parser.AdventParser;

import java.util.List;

public class Day2 {

    private static AdventParser ap = new AdventParser("src/day2/input.txt");

    public static void main(String[] args) {

        List<Command> input = ap.day2();

        System.out.println("Solution day2 (part 1): " + part1(input));
        System.out.println("Solution day2 (part 2): " + part2(input));
    }

    private static int part1(List<Command> l) {

        int depth = 0;
        int horizontalPos = 0;

        for (Command obj : l) {
            switch (obj.direction) {
                case "forward":
                    horizontalPos += obj.amount;
                    break;
                case "up":
                    depth -= obj.amount;
                    break;
                case "down":
                    depth += obj.amount;
                    break;
            }
        }

        return horizontalPos * depth;
    }

    private static int part2(List<Command> l) {

        int depth = 0;
        int horizontalPos = 0;
        int aim = 0;

        for (Command obj : l) {
            switch (obj.direction) {
                case "forward":
                    horizontalPos += obj.amount;
                    depth += aim * obj.amount;
                    break;
                case "up":
                    aim -= obj.amount;
                    break;
                case "down":
                    aim += obj.amount;
                    break;
            }
        }

        return horizontalPos * depth;
    }

}
