package day12;

import java.util.ArrayList;
import java.util.List;

public class Paths {

    public List<Paths> paths;
    public Node cNode;

    public Paths() {
        this.paths = new ArrayList<>();
    }

    public Paths(Node cNode) {
        this.cNode = cNode;
        this.paths = new ArrayList<>();
    }

}
