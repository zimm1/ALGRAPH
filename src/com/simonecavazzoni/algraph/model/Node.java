package com.simonecavazzoni.algraph.model;

import com.simonecavazzoni.algraph.ui.NodeUI;

import java.util.Objects;

/**
 * This is the Node class, every object of this class is a 'node'
 */
public class Node {
    private static final String DEFAULT_LABEL = "";

    private String label;

    private final NodeUI ui;

    /**
     * Void constructor
     */
    public Node() {
        this(DEFAULT_LABEL);
    }

    /**
     * @param label The name of the node passed as parameter
     */
    public Node(String label) {
        setLabel(label);
        this.ui = new NodeUI(this);
    }

    /**
     * @return return the label of the node
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label label(name) passed as parameter
     */
    public void setLabel(String label) {
        this.label = label.replace(" ", "_");
    }

    public NodeUI getUi() {
        return ui;
    }

    /**
     * @return return
     */
    @Override
    public String toString() {
        return "Node{" +
                "label='" + label + '\'' +
                '}';
    }

    /**
     * @param o node to compare
     * @return return true if the two labels are equal or false otherwise
     */
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
