package day13;

import java.util.ArrayList;
import java.util.List;

public class Origami {

    public int[][] plane;
    public List<Fold> folds;

    public Origami() {
//        plane = new int[15][11];
        plane = new int[894][1308];
        folds = new ArrayList<>();
    }

    public void doFolds() {
        for (Fold f : folds) {
            if (f.direction.equals("x")) {
                foldX(f);
            } else {
                foldY(f);
            }
        }
    }

    private void foldY(Fold fold) {
        for (int x = fold.coordinate; x < plane.length; x++) {
            for (int y = 0; y < plane[x].length; y++) {
                if (plane[x][y] == 1) {
                    plane[x][y] = 0;
                    plane[fold.coordinate - (x - fold.coordinate)][y] = 1;
                }
            }
        }
    }

    private void foldX(Fold fold) {
        for (int x = 0; x < plane.length; x++) {
            for (int y = fold.coordinate; y < plane[x].length; y++) {
                if (plane[x][y] == 1) {
                    plane[x][y] = 0;
                    plane[x][fold.coordinate - (y - fold.coordinate)] = 1;
                }
            }
        }
    }

    public int countNDots() {
        int total = 0;

        for (int[] ints : plane) {
            for (int anInt : ints) {
                if (anInt == 1) total++;
            }
        }
        return total;
    }

}
