package com.simonecavazzoni.algraph.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is the graph class, every instance of this class is a complete graph
 */
public class Graph {
    private Map<Node, Set<Edge>> adjacencies;

    private boolean directed;


    /**
     * Void constructor
     */
    public Graph() {
        this(true);
    }

    /**
     * @param directed Constructor with orientation parameter
     */
    public Graph(boolean directed) {
        this.directed = directed;

        this.adjacencies = new HashMap<>();
    }

    /**
     * @return return the adjacencies of the current graph
     */
    public Map<Node, Set<Edge>> getAdjacencies() {
        return adjacencies;
    }

    /**
     * @return return the number of the nodes
     */
    public Set<Node> getNodes() {
        return adjacencies.keySet();
    }

    /**
     * @return return the number of the edges
     */
    public Set<Edge> getEdges() {
        List<Set<Edge>> setList = new ArrayList<>(adjacencies.values());

        if (setList.size() == 0) {
            return new HashSet<>();
        }

        return setList.stream().collect(() -> new HashSet<>(setList.get(0)), Set::addAll, Set::addAll);
    }

    /**
     * Returns current graph mode
     * @return Current directed_input value
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Sets current graph mode.
     * Does nothing if graph already contains at least one edge.
     * @param directed New directed_input value
     */
    public void setDirected(boolean directed) {
        if (!getEdges().isEmpty()) {
            return;
        }

        this.directed = directed;
    }

    /**
     * Get a node by label
     * @param label Label of the node
     * @return Selected node (if exists)
     */
    public Node getNode(String label) {
        return getNode(new Node(label));
    }

    public Node getNode(Node node) {
        Optional<Node> optionalNode = getNodes().stream().filter(n -> n.equals(node)).findAny();
        return optionalNode.orElse(null);
    }

    /**
     * @param label Label of the new node
     * @return The node has been successfully added
     */
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

    /**
     * @param node Node to remove
     * @return The node has been successfully removed
     */
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

    /**
     * @param edge Edge to return inverted
     * @return Inverted edge
     */
    public Edge getInvertedEdge(Edge edge) {
        return getAdjacencies().get(edge.getN2()).stream()
                .filter(e -> e.getN2().equals(edge.getN1())).findAny().orElse(null);
    }

    public Edge addEdge(String label1, String label2) {
        Node node1 = getNode(label1);
        Node node2 = getNode(label2);

        if (node1 == null || node2 == null) {
            return null;
        }

        return addEdge(node1, node2);
    }

    /**
     * Add a new edge from first to second node
     * @param n1 First node
     * @param n2 Second node
     * @return The edge has been successfully added
     */
    public Edge addEdge(Node n1, Node n2) {
        return addEdge(new Edge(n1, n2, Edge.MIN_WEIGHT, isDirected()));
    }

    public Edge addEdge(Edge edge) {
        if (edge.isDirected() != isDirected()) {
            return null;
        }
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
