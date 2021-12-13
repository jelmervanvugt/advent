package day12;

import day10.Syntax;
import parser.AdventParser;

import java.util.*;


public class Day12 {

    private static AdventParser ap = new AdventParser("src/day12/input.txt");

    public static void main(String[] args) {

        Graph graph = ap.day12();

        System.out.println("Solution day12 (part 1): " + part1(graph));
        System.out.println("Solution day12 (part 2): " + part2(graph));
    }

    private static int part1(Graph graph) {
        Paths paths = graph.findPaths(true);
        return calcValidPaths(paths);
    }

    private static int calcValidPaths(Paths paths) {
        if (paths.paths.size() == 0) {
            if (paths.cNode.end) {
                return 1;
            } else {
                return 0;
            }

        }

        int total = 0;

        for (Paths p : paths.paths) {
            total += calcValidPaths(p);
        }

        return total;
    }


    private static int part2(Graph graph) {
        Paths paths = graph.findPaths(false);
        return calcValidPaths(paths);
    }


}
