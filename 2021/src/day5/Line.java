package day5;

public class Line {

    public int xStart, yStart, xEnd, yEnd;

    public Line(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;

        orderPoints();
    }

    private void orderPoints() {
        if(xStart > xEnd) {

         int tmpX = xStart;
         int tmpY = yStart;

        xStart = xEnd;
        yStart = yEnd;
        xEnd = tmpX;
        yEnd = tmpY;
        }
    }
}
