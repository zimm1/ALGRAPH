package com.simonecavazzoni.algraph.model;

import com.simonecavazzoni.algraph.ui.EdgeUI;

import java.util.Objects;

public class Edge {
    private static final int DEFAULT_WEIGHT = 1;
    public static final int MIN_WEIGHT = 0;

    private final Node n1;
    private final Node n2;
    private int weight;

    private final EdgeUI ui;

    public Edge(Node n1, Node n2) {
        this(n1, n2, DEFAULT_WEIGHT);
    }

    public Edge(Node n1, Node n2, int weight) {
        this.n1 = n1;
        this.n2 = n2;
        this.weight = weight;

        this.ui = new EdgeUI(this);
    }

    public Node getN1() {
        return n1;
    }

    public Node getN2() {
        return n2;
    }

    public int getWeight() {
        return weight;
    }

    public boolean setWeight(int weight) {
        if (weight < MIN_WEIGHT) {
            return false;
        }

        this.weight = weight;
        if (ui != null) {
            this.ui.setWeight(weight);
        }

        return true;
    }

    public EdgeUI getUi() {
        return ui;
    }

    public Edge getInverted() {
        return new Edge(n2, n1, weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Edge edge = (Edge) o;
        return Objects.equals(getN1(), edge.getN1()) &&
                Objects.equals(getN2(), edge.getN2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getN1(), getN2());
    }
}
