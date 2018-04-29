package com.simonecavazzoni.algraph.controller;

import com.simonecavazzoni.algraph.model.Edge;
import com.simonecavazzoni.algraph.model.Graph;
import com.simonecavazzoni.algraph.model.Node;
import com.simonecavazzoni.algraph.res.Strings;
import com.simonecavazzoni.algraph.ui.EdgeUI;
import com.simonecavazzoni.algraph.ui.NodeUI;
import com.simonecavazzoni.algraph.utils.DialogUtils;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.util.stream.Collectors;

/**
 * Controller for graph related operations.
 */
public class GraphController extends Controller implements Controller.EventListener {
    private Graph graph;

    // Events data
    private NodeUI movingNode;
    private double eventSceneX, eventSceneY, eventTranslateX, eventTranslateY, contextMenuX, contextMenuY;

    private ContextMenu contextMenu;
    private boolean creatingEdge;
    private Edge tempEdge;

    private MainController mainController;

    public GraphController(MainController mainController) {
        setRoot(new Pane());

        graph = new Graph();

        this.mainController = mainController;

        initEventHandlers();
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        initGraphUi();
    }

    private void initEventHandlers() {
        root.setOnContextMenuRequested(event -> {
            if (creatingEdge) {
                stopEdgeCreation(null);
                return;
            }

            Parent parent = event.getPickResult().getIntersectedNode().getParent();

            while (parent != null) {
                if (parent instanceof NodeUI) {
                    NodeUI nodeUI = (NodeUI) parent;
                    showContextMenu(GraphController.this.createNodeContextMenu(nodeUI.getNode()), event);
                    return;
                }

                if (parent instanceof EdgeUI) {
                    EdgeUI edgeUI = (EdgeUI) parent;
                    showContextMenu(GraphController.this.createEdgeContextMenu(edgeUI.getEdge()), event);
                    return;
                }

                parent = parent.getParent();
            }

            showContextMenu(createMainContextMenu(), event);
        });

        root.setOnMousePressed(event -> {
            hideContextMenu();

            Parent parent = event.getPickResult().getIntersectedNode().getParent();

            while (parent != null) {
                if (parent instanceof NodeUI) {
                    if (creatingEdge) {
                        stopEdgeCreation(((NodeUI) parent).getNode());
                        return;
                    }

                    eventSceneX = event.getSceneX();
                    eventSceneY = event.getSceneY();

                    movingNode = (NodeUI) parent;
                    Circle source = movingNode.getCircle();

                    eventTranslateX = source.getCenterX();
                    eventTranslateY = source.getCenterY();

                    parent.setCursor(Cursor.CLOSED_HAND);

                    return;
                }

                parent = parent.getParent();
            }

            if (creatingEdge) {
                stopEdgeCreation(null);
            }
        });

        root.setOnMouseDragged(event -> {
            if (movingNode == null) {
                return;
            }

            Double translateX = eventTranslateX + event.getSceneX() - eventSceneX;
            Double translateY = eventTranslateY + event.getSceneY() - eventSceneY;

            Circle source = movingNode.getCircle();
            source.setCenterX(translateX);
            source.setCenterY(translateY);

            restrictNodePosition(movingNode);
        });

        root.setOnMouseReleased(event -> {
            if (movingNode == null) {
                return;
            }

            movingNode.setCursor(Cursor.HAND);
            movingNode = null;
        });

        root.widthProperty().addListener((observable, oldValue, newValue) -> restrictNodesPosition());
        root.heightProperty().addListener((observable, oldValue, newValue) -> restrictNodesPosition());
    }

    private ContextMenu createNodeContextMenu(Node node) {
        ContextMenu nodeContextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem(Strings.create_edge_from_here);
        item1.setOnAction(event -> startEdgeCreation(node));

        MenuItem item2 = new MenuItem(Strings.remove_node);
        item2.setOnAction(event -> removeNode(node));

        nodeContextMenu.getItems().addAll(item1, item2);
        return nodeContextMenu;
    }

    private ContextMenu createEdgeContextMenu(Edge edge) {
        ContextMenu edgeContextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem(Strings.change_weight);
        item1.setOnAction(event -> changeWeight(edge));

        MenuItem item2 = new MenuItem(Strings.change_direction);
        item2.setOnAction(event -> changeDirection(edge));
        if (!graph.isDirected() || graph.getEdges().contains(edge.getInverted())) {
            item2.setDisable(true);
        }

        MenuItem item3 = new MenuItem(Strings.remove_edge);
        item3.setOnAction(event -> removeEdge(edge));

        edgeContextMenu.getItems().addAll(item1, item2, item3);
        return edgeContextMenu;
    }

    private ContextMenu createMainContextMenu() {
        ContextMenu mainContextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem(Strings.create_node);
        item1.setOnAction(event -> addNode());

        mainContextMenu.getItems().add(item1);
        return mainContextMenu;
    }

    private void showContextMenu(ContextMenu contextMenu, ContextMenuEvent event) {
        for (MenuItem item : contextMenu.getItems()) {
            if (!mainController.isGraphEditable()) {
                item.setDisable(false);
            }
        }
        contextMenuX = event.getSceneX();
        contextMenuY = event.getSceneY();


        this.contextMenu = contextMenu;
        this.contextMenu.show((Pane) event.getSource(), event.getScreenX(), event.getScreenY());
    }

    private void hideContextMenu() {
        if (contextMenu != null) {
            contextMenu.hide();
            contextMenu = null;
        }
    }

    private void initGraphUi() {
        if (graph == null) {
            return;
        }

        int k = 0;
        double startX = root.getWidth() / 2;
        double startY = root.getHeight() / 2;
        double radius = Math.min(startX, startY) - NodeUI.DEFAULT_RADIUS - 10;
        int n = graph.getNodes().size();

        for (Node node : graph.getNodes()) {
            NodeUI ui = node.getUi();
            ui.getCircle().setCenterX(startX + radius * Math.cos(k * 2 * Math.PI / n - Math.PI / 2));
            ui.getCircle().setCenterY(startY + radius * Math.sin(k * 2 * Math.PI / n - Math.PI / 2));

            k++;
        }

        updateGraphUI();
    }

    private void updateGraphUI() {
        root.getChildren().clear();

        root.getChildren().addAll(graph.getEdges().stream().map(Edge::getUi).collect(Collectors.toSet()));
        root.getChildren().addAll(graph.getNodes().stream().map(Node::getUi).collect(Collectors.toSet()));
    }

    public void resetGraphUI() {
        graph.getNodes().forEach(n -> {
            n.getUi().highlight(false);
            n.getUi().getDistanceLabel().setText("");
        });
        graph.getEdges().forEach(n -> n.getUi().highlight(false));
    }

    private void addNode() {
        try {
            Node node = graph.addNode(DialogUtils.showTextInputDialog(
                    Strings.create_node, null, Strings.name, null));

            if (node == null) {
                DialogUtils.showErrorDialog(
                        Strings.error, Strings.create_node, Strings.error_create_node);
                return;
            }

            node.getUi().getCircle().setCenterX(contextMenuX);
            node.getUi().getCircle().setCenterY(contextMenuY);
            updateGraphUI();

        } catch (Exception e) {
            //
        }
    }

    private void removeNode(Node node) {
        if (graph.removeNode(node)) {
            updateGraphUI();
        }
    }

    private void removeEdge(Edge edge) {
        if (graph.removeEdge(edge)) {
            updateGraphUI();
        }
    }

    private void changeWeight(Edge edge) {
        try {
            int weight = Integer.valueOf(DialogUtils.showTextInputDialog(
                    Strings.change_weight, null, Strings.weight, String.valueOf(edge.getWeight())));

            if (!edge.setWeight(weight) || (!graph.isDirected() && !graph.getInvertedEdge(edge).setWeight(weight))) {
                DialogUtils.showErrorDialog(
                        Strings.error, Strings.change_weight, Strings.error_min_weight + Edge.MIN_WEIGHT);
            }
        } catch (Exception e) {
            DialogUtils.showErrorDialog(
                    Strings.error, Strings.change_weight, Strings.error_change_weight);
        }
    }

    private void startEdgeCreation(Node node) {
        creatingEdge = true;

        Node tempNode = new Node();
        tempNode.getUi().getCircle().setRadius(0);

        tempNode.getUi().getCircle().setCenterX(node.getUi().getCircle().getCenterX());
        tempNode.getUi().getCircle().setCenterY(node.getUi().getCircle().getCenterY());

        root.setOnMouseMoved(event -> {
            tempNode.getUi().getCircle().setCenterX(event.getX());
            tempNode.getUi().getCircle().setCenterY(event.getY());
        });

        tempEdge = new Edge(node, tempNode, Edge.MIN_WEIGHT, false);
        tempEdge.getUi().getLabel().setVisible(false);

        root.getChildren().add(0, tempEdge.getUi());
    }

    private void stopEdgeCreation(Node node) {
        root.setOnMouseMoved(null);
        root.getChildren().remove(tempEdge.getUi());

        creatingEdge = false;

        if (node != null) {
            setGraphDirected();
            addEdge(new Edge(tempEdge.getN1(), node, Edge.MIN_WEIGHT, graph.isDirected()));
        }

        tempEdge = null;
    }

    private void setGraphDirected() {
        if (!graph.getEdges().isEmpty()) {
            return;
        }

        try {
            graph.setDirected(
                    DialogUtils.showTextSpinnerDialog(
                            Strings.graph_type,
                            Strings.graph_type_choice,
                            Strings.type,
                            Strings.directed, Strings.undirected
                    ).equals(Strings.directed));
        } catch (Exception e) {
            DialogUtils.showErrorDialog(Strings.graph_type, Strings.graph_type_choice, Strings.graph_type_error);
        }
    }

    private void addEdge(Edge edge) {
        if (graph.addEdge(edge) == null) {
            DialogUtils.showErrorDialog(
                    Strings.error, Strings.create_edge, Strings.error_create_edge);
            return;
        }

        changeWeight(edge);

        updateGraphUI();
    }

    private void changeDirection(Edge edge) {
        graph.removeEdge(edge);
        graph.addEdge(edge.getInverted());
        updateGraphUI();
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
        if (creatingEdge && event.getCode() == KeyCode.ESCAPE) {
            event.consume();
            stopEdgeCreation(null);
        }
    }

    public void highlight(Node node, Node parent) {
        node.getUi().highlight(true);

        if (parent == null) {
            return;
        }

        graph.getAdjacencies().get(parent).stream()
                .filter(e -> e.getN2().equals(node))
                .findAny()
                .ifPresent(e -> e.getUi().highlight(true));

        if (!graph.isDirected()) {
            graph.getAdjacencies().get(node).stream()
                    .filter(e -> e.getN2().equals(parent))
                    .findAny()
                    .ifPresent(e -> e.getUi().highlight(true));
        }
    }

    private void restrictNodePosition(NodeUI node) {
        Circle c = node.getCircle();

        double margin = NodeUI.DEFAULT_RADIUS + 10;

        if (c.getCenterX() > root.getWidth() - margin) {
            c.setCenterX(root.getWidth() - margin);
        }
        if (c.getCenterX() < root.getTranslateX() + margin) {
            c.setCenterX(root.getTranslateX() + margin);
        }
        if (c.getCenterY() > root.getHeight() - margin) {
            c.setCenterY(root.getHeight() - margin);
        }
        if (c.getCenterY() < root.getTranslateY() + margin) {
            c.setCenterY(root.getTranslateY() + margin);
        }
    }

    private void restrictNodesPosition() {
        graph.getNodes().forEach(n -> restrictNodePosition(n.getUi()));
    }
}
