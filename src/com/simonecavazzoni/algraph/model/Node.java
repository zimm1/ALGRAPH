package com.simonecavazzoni.algraph.model;

import com.simonecavazzoni.algraph.ui.NodeUI;

import java.util.Objects;

public class Node {
    private static final String DEFAULT_LABEL = "";

    private String label;

    private final NodeUI ui;

    public Node() {
        this(DEFAULT_LABEL);
    }

    public Node(String label) {
        setLabel(label);
        this.ui = new NodeUI(this);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label.replace(" ", "_");
    }

    public NodeUI getUi() {
        return ui;
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
