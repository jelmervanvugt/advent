package day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SegmentSolver {

    private Segment segment;
    private int[] uniqueValues;
    private HashMap<Integer, String> mapping;

    public SegmentSolver() {
        uniqueValues = new int[]{2, 4, 3, 7};
        mapping = new HashMap<>();
    }

    public int solvePart1() {

        int nOccurence = 0;

        for (String s : segment.output) {
            for (int i : uniqueValues) {
                if (s.length() == i) nOccurence++;
            }
        }

        return nOccurence;
    }

    // TODO: needs refactoring
    public int solvePart2() {

        initMapping();

        for (String s : segment.patterns) {
            if (containsCharacters(s, mapping.get(4))) {
                mapping.put(9, s);
                segment.patterns.remove(s);
                break;
            }
        }

        for (String s : segment.patterns) {
            if (s.length() == 6 && containsCharacters(s, mapping.get(1))) {
                mapping.put(0, s);
                segment.patterns.remove(s);
                break;
            }
        }

        for (String s : segment.patterns) {
            if (s.length() == 6) {
                mapping.put(6, s);
                segment.patterns.remove(s);
                break;
            }
        }

        for (String s : segment.patterns) {
            if (containsCharacters(s, mapping.get(7))) {
                mapping.put(3, s);
                segment.patterns.remove(s);
                break;
            }
        }

        for (String s : segment.patterns) {
            if (containsCharacters(mapping.get(9), s)) {
                mapping.put(5, s);
                segment.patterns.remove(s);
                break;
            }
        }

        mapping.put(2, segment.patterns.get(0));

        return calcOutput();
    }

    private void initMapping() {

        int[] uniqueNums = {1, 4, 7, 8};
        List<String> patterns = new ArrayList<>(segment.patterns);

        for (String s : patterns) {
            for (int i = 0; i < uniqueValues.length; i++) {
                if (s.length() == uniqueValues[i]) {
                    mapping.put(uniqueNums[i], s);
                    segment.patterns.remove(s);
                }
            }
        }
    }

    private boolean containsCharacters(String str, String subStr) {

        List<String> chars = Arrays.stream(subStr.split("(?!^)")).collect(Collectors.toList());
        int nFound = 0;

        for (String c : chars) {
            if (str.contains(c)) nFound++;
        }

        return nFound == chars.size();
    }

    private int calcOutput() {

        StringBuilder output = new StringBuilder();

        for (String s : segment.output) {

            char[] sa = s.toCharArray();
            Arrays.sort(sa);

            for (int i : mapping.keySet()) {

                char[] ma = mapping.get(i).toCharArray();
                Arrays.sort(ma);

                if (String.valueOf(sa).equals(String.valueOf(ma))) {
                    output.append(i);
                    break;
                }
            }
        }
        return Integer.parseInt(output.toString());
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

}
