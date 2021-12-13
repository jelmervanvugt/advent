package day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {

    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public void addEdge(String name1, String name2) {

        if (!containsNode(name1)) addNode(name1);
        if (!containsNode(name2)) addNode(name2);

        Node node1 = getNode(name1);
        Node node2 = getNode(name2);

        if (node1 != null && node2 != null) {
            node1.addAdjNode(node2);
            node2.addAdjNode(node1);
        }
    }

    private boolean containsNode(String name) {
        for (Node n : nodes) {
            if (n.name.equals(name)) return true;
        }
        return false;
    }

    private void addNode(String name) {
        nodes.add(new Node(
                name.equals("start"),
                name.equals("end"),
                Character.getType(name.charAt(0)) == Character.UPPERCASE_LETTER,
                name));
    }

    private Node getNode(String name) {
        for (Node n : nodes) {
            if (n.name.equals(name)) return n;
        }
        return null;
    }

    public Paths findPaths() {
        HashMap<String, Integer> visited = new HashMap<>();
        return buildPath(new Paths(), getNode("start"), visited);
    }

    private Paths buildPath(Paths p, Node cNode, HashMap<String, Integer> hm) {

        if (cNode.end) {
            p.cNode = cNode;
            return p;
        }

        HashMap<String, Integer> visited = new HashMap<>(hm);
        if (visited.containsKey(cNode.name)) {
            visited.put(cNode.name, 2);
        } else {
            visited.put(cNode.name, 1);
        }

        for (Node n : cNode.adjNodes) {
            if (!visited.containsKey(n.name)) {

                p.paths.add(buildPath(new Paths(n), n, visited));

            } else {
                if (cNode.bigNode && visited.get(cNode.name) != 2) {
                    p.paths.add(buildPath(new Paths(n), n, visited));
                }
            }
        }

        return p;
    }


}
