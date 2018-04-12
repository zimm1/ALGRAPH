package model;

import java.util.Objects;

public class Edge {
    private static final int DEFAULT_WEIGHT = 0;

    private Node n1;
    private Node n2;
    private int weight;

    public Edge(Node start, Node end) {
        this(start, end, DEFAULT_WEIGHT);
    }

    public Edge(Node n1, Node n2, int weight) {
        setN1(n1);
        setN2(n2);
        setWeight(weight);
    }

    public Node getN1() {
        return n1;
    }

    public void setN1(Node node) {
        this.n1 = node;
    }

    public Node getN2() {
        return n2;
    }

    public void setN2(Node node) {
        this.n2 = node;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
        return getWeight() == edge.getWeight() &&
                Objects.equals(getN1(), edge.getN1()) &&
                Objects.equals(getN2(), edge.getN2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getN1(), getN2(), getWeight());
    }
}
