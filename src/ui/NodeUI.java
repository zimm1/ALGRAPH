package ui;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Node;


public class NodeUI extends Group {

    private static final double DEFAULT_CENTER_X = 0;
    private static final double DEFAULT_CENTER_Y = 0;
    private static final double DEFAULT_RADIUS = 20;
    private static final Color DEFAULT_CIRCLE_COLOR = Color.BLACK;
    private static final Color DEFAULT_LABEL_COLOR = Color.WHITE;
    private static final Color DEFAULT_COLOR_HIGHLIGHT = Color.RED;

    private final Node node;

    private Circle circle;
    private Label label;

    public NodeUI(Node node) {
        this(node, DEFAULT_CENTER_X, DEFAULT_CENTER_Y, DEFAULT_RADIUS);
    }

    public NodeUI(Node node, double centerX, double centerY, double radius) {
        super();

        this.node = node;

        circle = new Circle(centerX, centerY, radius, DEFAULT_CIRCLE_COLOR);

        label = new Label(node.getLabel());
        label.setTextFill(DEFAULT_LABEL_COLOR);
        label.setAlignment(Pos.CENTER);
        label.translateXProperty().bind(circle.centerXProperty().subtract(circle.radiusProperty()));
        label.translateYProperty().bind(circle.centerYProperty().subtract(circle.radiusProperty()));
        label.prefWidthProperty().bind(circle.radiusProperty().multiply(2));
        label.prefHeightProperty().bind(circle.radiusProperty().multiply(2));
        label.setLabelFor(this);

        this.getChildren().addAll(circle, label);

        this.setCursor(Cursor.HAND);
    }

    public Node getNode() {
        return node;
    }

    public Circle getCircle() {
        return circle;
    }

    public Label getLabel() {
        return label;
    }

    public void highlight() {
        circle.setFill(DEFAULT_COLOR_HIGHLIGHT);
    }
}
