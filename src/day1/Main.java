package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            File f = new File("src/day1/input.txt");
            Scanner s = new Scanner(f);
            List<Integer> l = new ArrayList<>();

            while (s.hasNextLine()) {
                l.add(Integer.parseInt(s.nextLine()));
            }

            System.out.println("Solution day1 (part 1): " + part1(l));
            System.out.println("Solution day1 (part 2): " + part2(l));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int part1(List<Integer> l) {
        int pDepth = l.get(0);
        int nIncrements = 0;

        for (Integer i : l) {
            if (i > pDepth) {
                nIncrements++;
            }
            pDepth = i;
        }
        return nIncrements;
    }

    private static int part2(List<Integer> l) {

        int pSum = l.get(0) + l.get(1) + l.get(2);
        int nIncrements = 0;
        int tmpSum;

        for (int i = 1; i < l.size(); i++) {

            if (l.size() < i + 3) {
                return nIncrements;
            }

            tmpSum = l.get(i) + l.get(i + 1) + l.get(i + 2);

            if (tmpSum > pSum) {
                nIncrements++;
            }
            pSum = tmpSum;
        }

        return nIncrements;
    }

}
