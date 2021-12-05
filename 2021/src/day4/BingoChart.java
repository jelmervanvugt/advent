package day4;


public class BingoChart {

    private int[][] plane;
    private boolean[][] isMarked;
    private boolean isFinished;

    public BingoChart(int[][] plane) {
        this.plane = plane;
        this.isMarked = new boolean[5][5];
        this.isFinished = false;

        markInit();
    }

    private int calcScore(int lastGuessed) {
        int score = 0;

        for (int x = 0; x < isMarked.length; x++) {
            for (int y = 0; y < isMarked.length; y++) {
                if (!isMarked[x][y]) score += plane[x][y];
            }
        }
        return score * lastGuessed;
    }

    public int mark(int num) {

        for (int x = 0; x < isMarked.length; x++) {
            for (int y = 0; y < isMarked.length; y++) {
                if (plane[x][y] == num) {
                    isMarked[x][y] = true;
                    break;
                }
            }
        }

        if (checkBingo()) {
            isFinished = true;
            return calcScore(num);
        }
        return 0;
    }

    private void markInit() {
        for (int x = 0; x < isMarked.length; x++) {
            for (int y = 0; y < isMarked.length; y++) {
                isMarked[x][y] = false;
            }
        }
    }

    public boolean checkBingo() {

        int nMatched = 0;

        for (int x = 0; x < isMarked.length; x++) {
            for (int y = 0; y < isMarked.length; y++) {
                if (isMarked[x][y]) nMatched++;
            }
            if (nMatched == 5) return true;
            nMatched = 0;
        }

        nMatched = 0;

        for (int y = 0; y < isMarked.length; y++) {
            for (int x = 0; x < isMarked.length; x++) {
                if (isMarked[x][y]) nMatched++;
            }
            if (nMatched == 5) return true;
            nMatched = 0;
        }

        return false;
    }

    public boolean getIsFinished() {
        return isFinished;
    }
}
