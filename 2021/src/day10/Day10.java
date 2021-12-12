package day10;

import parser.AdventParser;

import java.util.*;


public class Day10 {

    private static AdventParser ap = new AdventParser("src/day10/input.txt");

    private static HashMap<Character, Character> tags = new HashMap<>();
    private static HashMap<Character, Integer> pIncorrect = new HashMap<>();
    private static HashMap<Character, Integer> pIncomplete = new HashMap<>();

    public static void main(String[] args) {

        List<Syntax> input = ap.day10();

        tags.put('(', ')');
        tags.put('[', ']');
        tags.put('{', '}');
        tags.put('<', '>');

        pIncorrect.put(')', 3);
        pIncorrect.put(']', 57);
        pIncorrect.put('}', 1197);
        pIncorrect.put('>', 25137);

        pIncomplete.put('(', 1);
        pIncomplete.put('[', 2);
        pIncomplete.put('{', 3);
        pIncomplete.put('<', 4);

        System.out.println("Solution day10 (part 1): " + part1(input));
        System.out.println("Solution day10 (part 2): " + part2(input));
    }

    private static int part1(List<Syntax> input) {

        int total = 0;

        for (Syntax s : input) {
            total += checkSyntax(s);
        }

        return total;
    }

    private static int checkSyntax(Syntax input) {

        Stack<Character> opening = new Stack<>();

        for (Character c : input.chunks) {
            if (tags.containsKey(c)) {
                opening.push(c);
            } else {
                Character tag = opening.pop();
                if (c != tags.get(tag)) return pIncorrect.get(c);
            }
        }

        return 0;
    }

    private static long part2(List<Syntax> input) {

        List<Syntax> incomplete = new ArrayList<>();

        for (Syntax s : input) {
            if (checkSyntax(s) == 0) incomplete.add(s);
        }

        List<Long> total = new ArrayList<>();

        for (Syntax s : incomplete) {
            total.add(fixSyntax(s));
        }
        Collections.sort(total);

        return total.get((total.size() - 1) / 2);
    }

    private static long fixSyntax(Syntax input) {

        Stack<Character> opening = new Stack<>();

        for (Character c : input.chunks) {
            if (tags.containsKey(c)) {
                opening.push(c);
            } else {
                opening.pop();
            }
        }

        long total = 0;
        Stack<Character> revOpening = new Stack();

        for (Character c : opening) {
            revOpening.push(c);
        }

        while (!revOpening.empty()) {
            total *= 5;
            total += pIncomplete.get(revOpening.pop());
        }

        return total;
    }

}
