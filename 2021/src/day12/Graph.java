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

    public Paths findPaths(boolean part1) {
        return buildPath2(new Paths(), getNode("start"), new ArrayList<>(), part1);
    }

    private Paths buildPath(Paths p, Node cNode, List<String> preVisited) {

        if (cNode.end) {
            p.cNode = cNode;
            return p;
        }

        List<String> visited = new ArrayList<>(preVisited);
        visited.add(cNode.name);

        for (Node n : cNode.adjNodes) {
            if (!n.start && !(!n.bigNode && visited.contains(n.name))) p.paths.add(buildPath(new Paths(n), n, visited));
        }

        p.cNode = cNode;

        return p;
    }

    //TODO: merge functions
    private Paths buildPath2(Paths p, Node cNode, List<String> preVisited, boolean visitedTwice) {

        if (cNode.end) {
            p.cNode = cNode;
            return p;
        }

        List<String> visited = new ArrayList<>(preVisited);
        visited.add(cNode.name);

        for (Node n : cNode.adjNodes) {
            if (!n.start) {
                if (n.bigNode || !visited.contains(n.name)) {
                    p.paths.add(buildPath2(new Paths(n), n, visited, visitedTwice));
                } else if (visited.contains(n.name) && !visitedTwice) {
                    p.paths.add(buildPath2(new Paths(n), n, visited, true));
                }
            }
        }
        p.cNode = cNode;

        return p;
    }

}
