package ui;

import javafx.beans.InvalidationListener;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.Edge;


public class EdgeUI extends Group {

    private static final Color DEFAULT_LINE_COLOR = Color.BLACK;
    private static final double DEFAULT_ARROW_LENGTH = 10;
    private static final double DEFAULT_ARROW_WIDTH = 7;

    private final Edge edge;

    private Line line;
    private Line arrowLeft;
    private Line arrowRight;

    public EdgeUI(Edge edge) {
        super();

        this.edge = edge;

        line = new Line();
        line.setStroke(DEFAULT_LINE_COLOR);
        line.startXProperty().bind(edge.getN1().getUi().getCircle().centerXProperty());
        line.startYProperty().bind(edge.getN1().getUi().getCircle().centerYProperty());
        line.endXProperty().bind(edge.getN2().getUi().getCircle().centerXProperty());
        line.endYProperty().bind(edge.getN2().getUi().getCircle().centerYProperty());

        arrowLeft = new Line();
        arrowRight = new Line();

        line.startXProperty().addListener(updater);
        line.startYProperty().addListener(updater);
        line.endXProperty().addListener(updater);
        line.endYProperty().addListener(updater);

        updater.invalidated(null);

        this.getChildren().addAll(line, arrowLeft, arrowRight);
    }

    public Edge getEdge() {
        return edge;
    }

    public Line getLine() {
        return line;
    }

    private InvalidationListener updater = o -> {
        double margin = getEdge().getN1().getUi().getCircle().getRadius();

        double
                sx = line.getStartX(),
                sy = line.getStartY(),
                ex = line.getEndX(),
                ey = line.getEndY();

        double distance = Math.sqrt(Math.pow((ex - sx), 2) + Math.pow((ey - sy), 2));
        double t = (distance - margin) / distance;

        ex = (1 - t) * sx + t * ex;
        ey = (1 - t) * sy + t * ey;

        arrowLeft.setEndX(ex);
        arrowLeft.setEndY(ey);
        arrowRight.setEndX(ex);
        arrowRight.setEndY(ey);

        if (ex == sx && ey == sy) {
            // arrow parts of length 0
            arrowLeft.setStartX(ex);
            arrowLeft.setStartY(ey);
            arrowRight.setStartX(ex);
            arrowRight.setStartY(ey);
        } else {
            double factor = DEFAULT_ARROW_LENGTH / Math.hypot(sx-ex, sy-ey);
            double factorO = DEFAULT_ARROW_WIDTH / Math.hypot(sx-ex, sy-ey);

            // part in direction of main line
            double dx = (sx - ex) * factor;
            double dy = (sy - ey) * factor;

            // part orthogonal to main line
            double ox = (sx - ex) * factorO;
            double oy = (sy - ey) * factorO;

            arrowLeft.setStartX(ex + dx - oy);
            arrowLeft.setStartY(ey + dy + ox);
            arrowRight.setStartX(ex + dx + oy);
            arrowRight.setStartY(ey + dy - ox);
        }
    };
}
