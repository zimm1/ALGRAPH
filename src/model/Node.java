package model;

import java.util.Objects;

public class Node {
    private static final String DEFAULT_LABEL = "";

    private String label;

    public Node() {
        this(DEFAULT_LABEL);
    }

    public Node(String label) {
        setLabel(label);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
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

        return Objects.equals(getLabel(), ((Node) o).getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel());
    }
}
