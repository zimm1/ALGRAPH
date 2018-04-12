package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public boolean isOriented() {
        return oriented;
    }

    public void setOriented(boolean oriented) {
        this.oriented = oriented;
    }

    public boolean addNode(String label) {
        return addNode(new Node(label));
    }

    public boolean addNode(Node node) {
        if (!nodes.add(node)) {
            return false;
        }

        adjacencies.put(node, new HashSet<>());

        return true;
    }

    public boolean removeNode(String label) {
        return removeNode(new Node(label));
    }

    public boolean removeNode(Node node) {
        if (!nodes.remove(node)) {
            return false;
        }

        adjacencies.remove(node);

        return true;
    }

    public boolean addEdge(String label1, String label2) {
        return addEdge(new Node(label1), new Node(label2));
    }

    public boolean addEdge(Node n1, Node n2) {
        return addEdge(new Edge(n1, n2));
    }

    public boolean addEdge(Edge edge) {
        if (!edges.add(edge)) {
            return false;
        }

        adjacencies.get(edge.getN1()).add(edge);
        if (oriented) {
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
        if (oriented) {
            adjacencies.get(edge.getN2()).remove(edge);
        }

        return true;
    }
}
