package model;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<Node, Set<Edge>> adjacencies;

    private final boolean directed;


    public Graph() {
        this(true);
    }

    public Graph(boolean directed) {
        this.directed = directed;

        this.adjacencies = new HashMap<>();
    }

    public Map<Node, Set<Edge>> getAdjacencies() {
        return adjacencies;
    }

    public Set<Node> getNodes() {
        return adjacencies.keySet();
    }

    public Set<Edge> getEdges() {
        List<Set<Edge>> setList = new ArrayList<>(adjacencies.values());

        if (setList.size() == 0) {
            return new HashSet<>();
        }

        return setList.stream().collect(() -> new HashSet<>(setList.get(0)), Set::addAll, Set::addAll);
    }

    public boolean isDirected() {
        return directed;
    }

    public Node getNode(String label) {
        return getNode(new Node(label));
    }

    public Node getNode(Node node) {
        Optional<Node> optionalNode = getNodes().stream().filter(n -> n.equals(node)).findAny();
        return optionalNode.orElse(null);
    }

    public Node addNode(String label) {
        return addNode(new Node(label));
    }

    public Node addNode(Node node) {
        if (adjacencies.containsKey(node)) {
            return null;
        }

        adjacencies.put(node, new HashSet<>());

        return node;
    }

    public boolean removeNode(String label) {
        return removeNode(new Node(label));
    }

    public boolean removeNode(Node node) {
        if (!adjacencies.containsKey(node)) {
            return false;
        }

        adjacencies.remove(node);
        for (Node n : adjacencies.keySet()) {
           adjacencies.put(n, adjacencies.get(n).stream().filter(e -> !e.getN2().equals(node))
                           .collect(Collectors.toSet()));
        }

        return true;
    }

    public Edge addEdge(String label1, String label2) {
        Node node1 = getNode(label1);
        Node node2 = getNode(label2);

        if (node1 == null || node2 == null) {
            return null;
        }

        return addEdge(node1, node2);
    }

    public Edge addEdge(Node n1, Node n2) {
        return addEdge(new Edge(n1, n2));
    }

    public Edge addEdge(Edge edge) {
        if (!adjacencies.get(edge.getN1()).add(edge)) {
            return null;
        }
        if (!directed) {
            if (!adjacencies.get(edge.getN2()).add(edge.getInverted())) {
                return null;
            }
        }

        return edge;
    }

    public boolean removeEdge(String label1, String label2) {
        return removeEdge(new Node(label1), new Node(label2));
    }

    public boolean removeEdge(Node n1, Node n2) {
        return removeEdge(new Edge(n1, n2));
    }

    public boolean removeEdge(Edge edge) {
        if (!adjacencies.get(edge.getN1()).remove(edge)) {
            return false;
        }
        if (!directed) {
            return adjacencies.get(edge.getN2()).remove(edge.getInverted());
        }

        return true;
    }
}
