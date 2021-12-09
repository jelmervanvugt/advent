package day9;

import java.util.ArrayList;
import java.util.List;

public class Basin {

    private Point sink;
    private List<Point> points;
    private int size;

    public Basin(Point sink) {
        this.sink = sink;
        this.points = new ArrayList<>();
        this.size = 0;
    }

    public void addPoint(Point p) {
        points.add(p);
        size++;
    }

    public int getSize() {
        return size;
    }

    public Point getSink() {
        return sink;
    }

}
