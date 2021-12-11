package day10;

import parser.AdventParser;

import java.util.List;


public class Day10 {

    private static AdventParser ap = new AdventParser("src/day10/input.txt");
    private static List<Character> openingChars = List.of('[', '(', '{', '<');

    public static void main(String[] args) {

        List<Syntax> input = ap.day10();

        System.out.println("Solution day10 (part 1): " + part1(input));
//        System.out.println("Solution day9 (part 2): " + part2(input));
    }

    private static int part1(List<Syntax> input) {


        return 0;
    }

    private boolean isValid(char[] syntax, int cIndex, int pIndex) {

        if(pIndex == syntax.length - 1) return true;

        return false;
    }

    private static int part2() {

        return 0;
    }

}
