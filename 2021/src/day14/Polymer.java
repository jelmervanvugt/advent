package day14;

import java.util.HashMap;

public class Polymer {

    public String polymer;
    public HashMap<String, String> rules;

    public Polymer(String polymer) {
        this.polymer = polymer;
        this.rules = new HashMap<>();
    }

    public void doSteps(int nSteps) {

        int step = 0;

        while (step < nSteps) {
            polymer = doStep(polymer);
            step++;
            System.out.println(step);
        }
    }

    private String doStep(String str) {

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < str.length() + 1; i++) {

            String subStr = str.substring(i - 2, i);

            if (rules.containsKey(subStr)) {
                subStr = subStr.charAt(0) + rules.get(subStr) + subStr.charAt(1);
            }

            if (i != 2) {
                sb.append(subStr.substring(1));
            } else {
                sb.append(subStr);
            }
        }
        return sb.toString();
    }

}
