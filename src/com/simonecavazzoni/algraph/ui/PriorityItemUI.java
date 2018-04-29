package com.simonecavazzoni.algraph.ui;

import com.simonecavazzoni.algraph.res.Colors;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import com.simonecavazzoni.algraph.model.Node;
import com.simonecavazzoni.algraph.model.PriorityItem;
import com.simonecavazzoni.algraph.res.Strings;

public class PriorityItemUI<T> extends Group {

    private PriorityItem<T> priorityItem;
    private Rectangle rectangle;
    private Label nodeLabel;
    private Label priorityLabel;
    private Label emptyLabel;
    private static final int height = 80;
    private static final int width = 80;
    private static final String cssLabelStyle = "labelStyle";
    private static final String cssNodeLabelStyle = "nodeLabelStyle";
    private static final String cssPriorityStyle = "priorityLabelStyle";
    private static final String cssEmptyLabelStyle = "emptyLabelStyle";

    public PriorityItemUI(){
        this(null);
    }

    public PriorityItemUI(PriorityItem<T> priorityItem){

        this.priorityItem = priorityItem;
        initRectangle();
        initNodeLabel();
        initPriorityLabel();

        if(getNode() == null){
            initEmptyLabel();
        }

        this.getChildren().addAll(rectangle,nodeLabel,priorityLabel);

        if(emptyLabel != null){
            this.getChildren().add(emptyLabel);
        }

    }

    public void updatePriorityLabel() {
        this.priorityLabel.setText(Integer.toString(priorityItem.getPriority()));
    }

    private void initRectangle(){
        rectangle = new Rectangle(width,height);
        rectangle.setStroke(Colors.PRIMARY_COLOR);
        rectangle.setFill(Colors.DEFAULT_COLOR);
    }

    private void initNodeLabel(){
        Node node = getNode();
        nodeLabel = new Label((node != null) ? node.getLabel() : "");
        nodeLabel.setLabelFor(this);
        nodeLabel.getStyleClass().add(cssLabelStyle);
        nodeLabel.getStyleClass().add(cssNodeLabelStyle);
        nodeLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
        nodeLabel.setPrefWidth(rectangle.getWidth());
        nodeLabel.setPrefHeight(rectangle.getHeight());
    }

    private void initPriorityLabel(){
        priorityLabel = new Label((priorityItem != null) ? (Integer.toString(priorityItem.getPriority())) : "");
        priorityLabel.setLabelFor(this);
        priorityLabel.getStyleClass().add(cssLabelStyle);
        priorityLabel.getStyleClass().add(cssPriorityStyle);
        priorityLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
        priorityLabel.setPrefWidth(rectangle.getWidth());
        priorityLabel.setPrefHeight(rectangle.getHeight());
    }

    private void initEmptyLabel(){
        emptyLabel = new Label(getEmptyQueueText());
        priorityLabel.setLabelFor(this);
        emptyLabel.setPrefHeight(rectangle.getHeight());
        emptyLabel.setPrefWidth(rectangle.getWidth());
        emptyLabel.getStyleClass().add(cssLabelStyle);
        emptyLabel.getStyleClass().add(cssEmptyLabelStyle);
        emptyLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
    }

    private Node getNode(){
        if(priorityItem != null){
            if (priorityItem.getItem() instanceof Node) {
                return (Node) priorityItem.getItem();
            }
        }
        return null;
    }

    public PriorityItem<T> getPriorityItem() {
        return priorityItem;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    private String getEmptyQueueText(){
        return Strings.emptyQueue;
    }

    public void setSelectedItem(boolean isV){
        priorityLabel.setTextFill(isV ? Colors.OTHER_COLOR_1 : Colors.OTHER_COLOR_2);
    }

    public void setUnselectedItem(){
        priorityLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
    }
}
