package com.simonecavazzoni.algraph.ui;

import javafx.beans.InvalidationListener;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import com.simonecavazzoni.algraph.model.Edge;


public class EdgeUI extends Group {

    private static final Color DEFAULT_LINE_COLOR = Color.BLACK;
    private static final Color DEFAULT_LABEL_COLOR = Color.BLACK;
    private static final Color DEFAULT_COLOR_HIGHLIGHT = Color.RED;
    private static final double DEFAULT_ARROW_LENGTH = 10;
    private static final double DEFAULT_ARROW_WIDTH = 7;
    private static final double DEFAULT_LABEL_DISTANCE = 10;
    private static final double DEFAULT_LINE_DISTANCE = 5;
    private static final double DEFAULT_FONT_SIZE = 20;

    private Edge edge;

    private Line line;
    private Line arrowLeft;
    private Line arrowRight;
    private Label label;

    public EdgeUI(Edge edge) {
        super();

        this.edge = edge;

        line = new Line();
        line.setStroke(DEFAULT_LINE_COLOR);

        arrowLeft = new Line();
        arrowRight = new Line();

        label = new Label(String.valueOf(edge.getWeight()));
        label.setTextFill(DEFAULT_LABEL_COLOR);
        label.setAlignment(Pos.CENTER);
        label.setLabelFor(this);
        label.setFont(new Font(DEFAULT_FONT_SIZE));

        edge.getN1().getUi().getCircle().centerXProperty().addListener(updater);
        edge.getN1().getUi().getCircle().centerYProperty().addListener(updater);
        edge.getN2().getUi().getCircle().centerXProperty().addListener(updater);
        edge.getN2().getUi().getCircle().centerYProperty().addListener(updater);

        label.widthProperty().addListener(updater);
        label.heightProperty().addListener(updater);

        updater.invalidated(null);

        this.getChildren().addAll(line, arrowLeft, arrowRight, label);
    }

    public Edge getEdge() {
        return edge;
    }

    public Line getLine() {
        return line;
    }

    public Label getLabel() {
        return label;
    }

    private InvalidationListener updater = o -> {
        double margin = getEdge().getN2().getUi().getCircle().getRadius();

        double
                sx = edge.getN1().getUi().getCircle().getCenterX(),
                sy = edge.getN1().getUi().getCircle().getCenterY(),
                ex = edge.getN2().getUi().getCircle().getCenterX(),
                ey = edge.getN2().getUi().getCircle().getCenterY(),
                dx = sx - ex,
                dy = sy - ey;

        double dist = Math.hypot(dx, dy);

        dx /= dist;
        dy /= dist;

        sx = sx + DEFAULT_LINE_DISTANCE * dy;
        sy = sy - DEFAULT_LINE_DISTANCE * dx;
        ex = ex + DEFAULT_LINE_DISTANCE * dy;
        ey = ey - DEFAULT_LINE_DISTANCE * dx;

        line.setStartX(sx);
        line.setStartY(sy);
        line.setEndX(ex);
        line.setEndY(ey);

        double middleX = (sx + ex) / 2;
        double middleY = (sy + ey) / 2;

        double labelX = middleX + DEFAULT_LABEL_DISTANCE * dy - label.getWidth() / 2;
        double labelY = middleY - DEFAULT_LABEL_DISTANCE * dx - label.getHeight() / 2;
        label.setTranslateX(labelX);
        label.setTranslateY(labelY);

        double distance = Math.hypot(ex - sx, ey - sy);
        double t = (distance - margin + 1) / distance;

        double arrowsEx = (1 - t) * sx + t * ex;
        double arrowsEy = (1 - t) * sy + t * ey;

        arrowLeft.setEndX(arrowsEx);
        arrowLeft.setEndY(arrowsEy);
        arrowRight.setEndX(arrowsEx);
        arrowRight.setEndY(arrowsEy);

        double parallelComponent = DEFAULT_ARROW_LENGTH / (distance - margin);
        double orthogonalComponent = DEFAULT_ARROW_WIDTH / (distance - margin);

        // part in direction of main line
        dx = (sx - arrowsEx) * parallelComponent;
        dy = (sy - arrowsEy) * parallelComponent;

        // part orthogonal to main line
        double ox = (sx - arrowsEx) * orthogonalComponent;
        double oy = (sy - arrowsEy) * orthogonalComponent;

        arrowLeft.setStartX(arrowsEx + dx - oy);
        arrowLeft.setStartY(arrowsEy + dy + ox);
        arrowRight.setStartX(arrowsEx + dx + oy);
        arrowRight.setStartY(arrowsEy + dy - ox);
    };

    public void setWeight(int weight) {
        label.setText(String.valueOf(weight));
    }

    public void highlight(boolean highlight) {
        line.setStroke(highlight ? DEFAULT_COLOR_HIGHLIGHT : DEFAULT_LINE_COLOR);
        arrowLeft.setStroke(highlight ? DEFAULT_COLOR_HIGHLIGHT : DEFAULT_LINE_COLOR);
        arrowRight.setStroke(highlight ? DEFAULT_COLOR_HIGHLIGHT : DEFAULT_LINE_COLOR);
        label.setTextFill(highlight ? DEFAULT_COLOR_HIGHLIGHT : DEFAULT_LINE_COLOR);
    }
}
