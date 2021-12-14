package parser;

import day10.Syntax;
import day12.Graph;
import day12.Node;
import day13.Fold;
import day13.Origami;
import day14.Polymer;
import day2.Command;
import day4.BingoChart;
import day5.Line;
import day8.Segment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class AdventParser {

    private File f;
    private Scanner s;

    public AdventParser(String fileName) {
        try {

            f = new File(fileName);
            s = new Scanner(f);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> day1() {

        List<Integer> l = new ArrayList<>();

        while (s.hasNextLine()) {
            l.add(Integer.parseInt(s.nextLine()));
        }

        return l;
    }

    public List<Command> day2() {

        List<Command> l = new ArrayList<>();

        while (s.hasNextLine()) {

            String[] tmp = s.nextLine().split("\\s+");

            l.add(new Command(
                    tmp[0],
                    Integer.parseInt(tmp[1])
            ));
        }

        return l;
    }

    public List<String> day3() {

        List<String> l = new ArrayList<>();

        while (s.hasNextLine()) {
            l.add(s.nextLine());
        }

        return l;
    }

    public List<Integer> day4DrawOrder() {

        List<Integer> drawOrder = new ArrayList<>();
        Arrays.stream(s.nextLine().split(",")).mapToInt(num -> Integer.parseInt(num)).forEach(num -> drawOrder.add(num));

        return drawOrder;
    }

    public List<BingoChart> day4BingoCharts() {

        List<BingoChart> charts = new ArrayList<>();
        int size = 5;
        String tmp;

        while (s.hasNextLine()) {

            tmp = s.nextLine();

            if (!tmp.equals("")) {

                int[][] plane = new int[5][5];
                plane[0] = Arrays.stream(tmp.split("\\s+")).filter(num -> !num.equals("")).mapToInt(Integer::parseInt).toArray();

                for (int i = 1; i < size; i++) {
                    plane[i] = Arrays.stream(s.nextLine().split("\\s+")).filter(num -> !num.equals("")).mapToInt(Integer::parseInt).toArray();
                }
                charts.add(new BingoChart(plane));
            }
        }

        return charts;
    }

    public List<Line> day5() {

        List<Line> lines = new ArrayList<>();
        int[] tmp;

        while (s.hasNextLine()) {

            tmp = Arrays.stream(s.nextLine().split(",|->")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            lines.add(new Line(tmp[0], tmp[1], tmp[2], tmp[3]));
        }

        return lines;
    }

    public HashMap<Integer, Long> day6() {

        List<Integer> input = new ArrayList<>();
        Arrays.stream(s.nextLine().split(",")).mapToInt(Integer::parseInt).forEach(input::add);

        HashMap<Integer, Long> hm = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            hm.put(i, 0L);
        }

        for (Integer i : input) {
            hm.put(i, hm.get(i) + 1);
        }
        return hm;
    }

    public HashMap<Integer, Integer> day7() {

        HashMap<Integer, Integer> hm = new HashMap<>();
        int[] tmp = Arrays.stream(s.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        for (int i : tmp) {
            hm.merge(i, 1, Integer::sum);
        }
        return hm;
    }

    public List<Segment> day8() {

        List<Segment> input = new ArrayList<>();
        String[] tmp;

        while (s.hasNextLine()) {
            tmp = s.nextLine().split("\\|");
            input.add(
                    new Segment(
                            Arrays.stream(tmp[0].split("\\s+")).collect(Collectors.toList()),
                            Arrays.stream(tmp[1].split("\\s+")).filter(str -> !str.equals("")).collect(Collectors.toList())
                    )
            );
        }
        return input;
    }

    public int[][] day9() {

        int[][] input = new int[100][100];
        int x = 0;

        while (s.hasNextLine()) {

            char[] tmp = s.nextLine().toCharArray();

            for (int y = 0; y < input[x].length; y++) {
                input[x][y] = Integer.parseInt(String.valueOf(tmp[y]));
            }

            x++;
        }
        return input;
    }

    public List<Syntax> day10() {

        List<Syntax> input = new ArrayList<>();

        while (s.hasNextLine()) {
            input.add(new Syntax(s.nextLine().toCharArray()));
        }
        return input;
    }

    public int[][] day11() {

        int[][] input = new int[10][10];
        int x = 0;

        while (s.hasNextLine()) {

            char[] tmp = s.nextLine().toCharArray();

            for (int y = 0; y < tmp.length; y++) {
                input[x][y] = Integer.parseInt(String.valueOf(tmp[y]));
            }
            x++;
        }
        return input;
    }

    public Graph day12() {

        Graph graph = new Graph();

        while (s.hasNextLine()) {
            String[] tmp = s.nextLine().split("-");
            graph.addEdge(tmp[0], tmp[1]);
        }
        return graph;
    }

    public Origami day13() {

        Origami origami = new Origami();
        String input = "";

        while (s.hasNextLine()) {

            input = s.nextLine();

            if (input.equals("")) break;

            int[] tmp = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt).toArray();
            origami.plane[tmp[1]][tmp[0]] = 1;
        }

        while (s.hasNextLine()) {

            String[] tmp = Arrays.stream(s.nextLine().split("\\s+")[2].split("=")).toArray(String[]::new);
            origami.folds.add(new Fold(tmp[0], Integer.parseInt(tmp[1])));
        }
        return origami;
    }

    public Polymer day14() {

        Polymer polymer = new Polymer(s.nextLine());
        s.nextLine();

        while (s.hasNextLine()) {
            String[] tmp = Arrays.stream(s.nextLine().split("->")).map(String::trim).toArray(String[]::new);
            polymer.rules.put(tmp[0], tmp[1]);
        }
        return polymer;
    }

}
