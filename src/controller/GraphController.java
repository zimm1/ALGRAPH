package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Edge;
import model.Graph;
import model.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphController {
    @FXML
    private Pane container;

    private Graph graph;

    private Map<Node, Circle> nodes;
    private Map<Edge, Line> edges;

    @FXML
    private void initialize() {
        nodes = new HashMap<>();
        edges = new HashMap<>();

        createTestGraph();
        initGraphUi();
        drawGraph();
    }

    private void createTestGraph() {
        graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");

        graph.addEdge("A", "B");
    }

    private void initGraphUi() {
        int count = 0;
        int startX = 30;
        int startY = 30;

        for (Node n : graph.getNodes()) {
            nodes.put(n, new Circle(startX + count * startX * 2, startY, startX / 2, Color.BLACK));
            count++;
        }

        for (Edge e : graph.getEdges()) {
            Circle c1 = nodes.get(e.getN1());
            Circle c2 = nodes.get(e.getN2());

            edges.put(e, new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY()));
        }
    }

    private void drawGraph() {
        container.getChildren().addAll(nodes.values());
        container.getChildren().addAll(edges.values());
    }
}
