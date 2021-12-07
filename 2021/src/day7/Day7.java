package day7;

import parser.AdventParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Day7 {

    private static AdventParser ap = new AdventParser("src/day7/input.txt");

    public static void main(String[] args) {

        HashMap<Integer, Integer> input = ap.day7();

        System.out.println("Solution day7 (part 1): " + part1(input));
        System.out.println("Solution day7 (part 2): " + part2(input));
    }

    private static int part1(HashMap<Integer, Integer> input) {

        int nFuel = 0;

        List<Integer> inputList = makeList(input);
        int median = inputList.get(inputList.size() / 2);

        for (Integer k : input.keySet()) {
            if (k > median) {
                nFuel += (k - median) * input.get(k);
            } else {
                nFuel += (median - k) * input.get(k);
            }
        }

        return nFuel;
    }

    private static List<Integer> makeList(HashMap<Integer, Integer> input) {

        List<Integer> output = new ArrayList<>();

        for (Integer k : input.keySet()) {
            for (int i = 0; i < input.get(k); i++) {
                output.add(k);
            }
        }

        return output.stream().sorted().collect(Collectors.toList());
    }

    private static int part2(HashMap<Integer, Integer> input) {

        int avg = calcAvg(input);
        int nFuel = 0;

        for (Integer k : input.keySet()) {
            if (k > avg) {
                nFuel += calcFuelCost(avg, k, 1) * input.get(k);
            } else {
                nFuel += calcFuelCost(k, avg, 1) * input.get(k);
            }
        }

        return nFuel;
    }

    private static int calcAvg(HashMap<Integer, Integer> input) {

        int total = 0;
        int hmLength = 0;

        for (Integer k : input.keySet()) {
            total += k * input.get(k);
            hmLength += input.get(k);
        }

        return total / hmLength;
    }

    private static int calcFuelCost(int x, int y, int cost) {
        if (x < y) {
            return cost + calcFuelCost(x + 1, y, cost + 1);
        }
        return 0;
    }

}
