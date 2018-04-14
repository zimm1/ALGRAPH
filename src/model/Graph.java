package model;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    public static final int MAX_NODES = 20;

    private Set<Node> nodes;
    private Set<Edge> edges;
    private Map<Node, Set<Edge>> adjacencies;

    private boolean oriented;


    public Graph() {
        this(true);
    }

    public Graph(boolean oriented) {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
        setOriented(oriented);

        this.adjacencies = new HashMap<>();
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public Map<Node, Set<Edge>> getAdjacencies() {
        return adjacencies;
    }

    public boolean isOriented() {
        return oriented;
    }

    public void setOriented(boolean oriented) {
        this.oriented = oriented;
    }

    public Node addNode(String label) {
        return addNode(new Node(label));
    }

    public Node addNode(Node node) {
        if (!nodes.add(node)) {
            return null;
        }

        adjacencies.put(node, new HashSet<>());

        return node;
    }

    public boolean removeNode(String label) {
        return removeNode(new Node(label));
    }

    public boolean removeNode(Node node) {
        if (!nodes.remove(node)) {
            return false;
        }

        Set<Edge> toRemove = new HashSet<>();

        for (Edge e : edges) {
            if (e.getN1().equals(node) || e.getN2().equals(node)) {
                toRemove.add(e);
            }
        }

        for (Edge e : toRemove) {
            removeEdge(e);
        }

        return true;
    }

    public boolean addEdge(String label1, String label2) {
        Optional<Node> node1 = this.nodes.stream().filter(n -> n.getLabel().equals(label1)).findAny();
        Optional<Node> node2 = this.nodes.stream().filter(n -> n.getLabel().equals(label2)).findAny();

        if (!(node1.isPresent() && node2.isPresent())) {
            return false;
        }

        return addEdge(node1.get(), node2.get());
    }

    public boolean addEdge(Node n1, Node n2) {
        return addEdge(new Edge(n1, n2));
    }

    public boolean addEdge(Edge edge) {
        if (!edges.add(edge)) {
            return false;
        }

        adjacencies.get(edge.getN1()).add(edge);
        if (!oriented) {
            adjacencies.get(edge.getN2()).add(edge);
        }

        return true;
    }

    public boolean removeEdge(String label1, String label2) {
        return removeEdge(new Node(label1), new Node(label2));
    }

    public boolean removeEdge(Node n1, Node n2) {
        return removeEdge(new Edge(n1, n2));
    }

    public boolean removeEdge(Edge edge) {
        if (!edges.remove(edge)) {
            return false;
        }

        adjacencies.get(edge.getN1()).remove(edge);
        if (!oriented) {
            adjacencies.get(edge.getN2()).remove(edge);
        }

        return true;
    }
}
