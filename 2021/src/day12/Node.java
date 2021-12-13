package day12;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public List<Node> adjNodes;
    public String name;
    public boolean start, end, bigNode;


    public Node(boolean start, boolean end, boolean bigNode, String name) {
        this.adjNodes = new ArrayList<>();
        this.start = start;
        this.end = end;
        this.bigNode = bigNode;
        this.name = name;
    }

    public void addAdjNode(Node node) {
        for (Node n : adjNodes) {
            if (n.name.equals(node.name)) {
                return;
            }
        }
        adjNodes.add(node);
    }

}
