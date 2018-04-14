package ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.Edge;


public class EdgeUI extends Group {

    private static final Color DEFAULT_LINE_COLOR = Color.BLACK;

    private final Edge edge;

    private Line line;

    public EdgeUI(Edge edge) {
        super();

        this.edge = edge;

        line = new Line();
        line.setStroke(DEFAULT_LINE_COLOR);
        line.startXProperty().bind(edge.getN1().getUi().getCircle().centerXProperty());
        line.startYProperty().bind(edge.getN1().getUi().getCircle().centerYProperty());
        line.endXProperty().bind(edge.getN2().getUi().getCircle().centerXProperty());
        line.endYProperty().bind(edge.getN2().getUi().getCircle().centerYProperty());

        this.getChildren().add(line);
    }

    public Edge getEdge() {
        return edge;
    }

    public Line getLine() {
        return line;
    }
}
