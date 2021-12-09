package day9;

import parser.AdventParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Day9 {

    private static AdventParser ap = new AdventParser("src/day9/input.txt");

    public static void main(String[] args) {

        int[][] input = ap.day9();

        System.out.println("Solution day9 (part 1): " + part1(input));
        System.out.println("Solution day9 (part 2): " + part2(input));
    }

    private static int part1(int[][] input) {

        List<Point> lowPoints = getLowPoints(input);
        int total = 0;

        for (Point p : lowPoints) {
            total += p.val + 1;
        }

        return total;
    }

    private static List<Point> getLowPoints(int[][] input) {

        List<Point> lowPoints = new ArrayList<>();

        for (int x = 0; x < input.length; x++) {
            for (int y = 0; y < input[0].length; y++) {
                Point tmp = new Point(x, y, input[x][y]);
                if (isLowestPoint(tmp, getNeighbours(input, x, y))) lowPoints.add(new Point(x, y, input[x][y]));
            }
        }
        return lowPoints;
    }

    private static List<Point> getNeighbours(int[][] input, int x, int y) {

        List<Point> points = new ArrayList<>();

        if (x == 0) {
            points.add(new Point(x + 1, y, input[x + 1][y]));
        } else if (x == input.length - 1) {
            points.add(new Point(x - 1, y, input[x - 1][y]));
        } else {
            points.add(new Point(x + 1, y, input[x + 1][y]));
            points.add(new Point(x - 1, y, input[x - 1][y]));
        }

        if (y == 0) {
            points.add(new Point(x, y + 1, input[x][y + 1]));
        } else if (y == input[x].length - 1) {
            points.add(new Point(x, y - 1, input[x][y - 1]));
        } else {
            points.add(new Point(x, y + 1, input[x][y + 1]));
            points.add(new Point(x, y - 1, input[x][y - 1]));
        }

        return points;
    }

    private static boolean isLowestPoint(Point point, List<Point> points) {
        for (Point p : points) {
            if (p.val <= point.val) return false;
        }
        return true;
    }

    private static int part2(int[][] input) {

        List<Basin> basins = new ArrayList<>();

        for (Point p : getLowPoints(input)) {
            basins.add(new Basin(p));
        }

        for (Basin b : basins) {
            buildBasin(b, b.getSink(), input);
        }

        while (basins.size() > 3) {

            Basin smallestBasin = basins.get(0);

            for (Basin b : basins) {
                if (b.getSize() < smallestBasin.getSize()) smallestBasin = b;
            }

            basins.remove(smallestBasin);
        }

        return basins.get(0).getSize() * basins.get(1).getSize() * basins.get(2).getSize();
    }

    private static void buildBasin(Basin b, Point point, int[][] input) {

        List<Point> points = getNeighbours(input, point.x, point.y);
        input[point.x][point.y] = 9;
        b.addPoint(point);
        points = points.stream().filter(p -> p.val != 9).collect(Collectors.toList());

        if (points.size() != 0) {
            for (Point neighbour : points) {
                if (neighbour.val > point.val && input[neighbour.x][neighbour.y] != 9) {
                    buildBasin(b, neighbour, input);
                }
            }
        }
    }

}
