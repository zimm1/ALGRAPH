package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Node;
import model.PriorityItem;

public class PriorityItemUI<T> extends Group {

    private PriorityItem<T> priorityItem;
    private Rectangle rectangle;
    private Label nodeLabel;
    private Label priorityLabel;
    private static final int height = 100;
    private static final int width = 100;
    private static final Pos nodeLabelPos = Pos.TOP_CENTER;
    private static final Pos priorityLabelPos = Pos.BOTTOM_CENTER;
    private static final int fontSize = 15;
    private static final Color defaultRectangleColor = Color.BLACK;
    private static final Color defaultLabelColor = Color.WHITE;
    private static final Color rectangleStrokeColor = Color.RED;

    public PriorityItemUI(){
        this.rectangle = createRectangle();
        this.getChildren().add(rectangle);
    }

    public PriorityItemUI(PriorityItem<T> priorityItem){

        this.priorityItem = priorityItem;
        this.rectangle = createRectangle();
        this.nodeLabel = createNodeLabel();
        this.priorityLabel = createPriorityLabel();

        this.getChildren().addAll(rectangle,nodeLabel,priorityLabel);
    }

    public void updatePriorityLabel() {
        this.priorityLabel.setText(Integer.toString(priorityItem.getPriority()));
    }

    private Rectangle createRectangle(){
        rectangle = new Rectangle(width,height,defaultRectangleColor);
        rectangle.setStroke(rectangleStrokeColor);
        return rectangle;
    }

    private Label createNodeLabel(){
        Node node = getNode();
        nodeLabel = new Label(node.getLabel());
        nodeLabel.setLabelFor(this);
        nodeLabel.setAlignment(nodeLabelPos);
        nodeLabel.setTextFill(defaultLabelColor);
        nodeLabel.setFont(new Font("Arial",fontSize));
        nodeLabel.setPrefWidth(rectangle.getWidth());
        nodeLabel.setPrefHeight(rectangle.getHeight());
        nodeLabel.setPadding(new Insets(20,0,0,0));
        return nodeLabel;
    }

    private Label createPriorityLabel(){
        priorityLabel = new Label(Integer.toString(priorityItem.getPriority()));
        priorityLabel.setAlignment(priorityLabelPos);
        priorityLabel.setTextFill(defaultLabelColor);
        priorityLabel.setPrefWidth(rectangle.getWidth());
        priorityLabel.setPrefHeight(rectangle.getHeight());
        priorityLabel.setPadding(new Insets(0,0,20,0));
        return priorityLabel;
    }

    private Node getNode(){
        if (priorityItem.getItem() instanceof Node) {
            return (Node) priorityItem.getItem();
        }

        return new Node("");

    }

    public PriorityItem<T> getPriorityItem() {
        return priorityItem;
    }


}
