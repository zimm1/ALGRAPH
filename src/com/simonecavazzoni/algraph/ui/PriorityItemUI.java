package com.simonecavazzoni.algraph.ui;

import com.simonecavazzoni.algraph.res.Colors;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import com.simonecavazzoni.algraph.model.Node;
import com.simonecavazzoni.algraph.model.PriorityItem;
import com.simonecavazzoni.algraph.res.Strings;

/** This class extends Group and creates the graphics for PriorityItem&ltT&gt to show.
 *  This class expose the methods to change the style of its contents.
 * @param <T>
 */
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

    /**
     * This updates the priority label text
     */
    public void updatePriorityLabel() {
        this.priorityLabel.setText(Integer.toString(priorityItem.getPriority()));
    }

    /**
     * This initializes the rectangle properties
     */
    private void initRectangle(){
        rectangle = new Rectangle(width,height);
        rectangle.setStroke(Colors.PRIMARY_COLOR);
        rectangle.setFill(Colors.DEFAULT_COLOR);
    }

    /**
     * This initializes the node label property
     */
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

    /**
     * This initializes the priority label property
     */
    private void initPriorityLabel(){
        priorityLabel = new Label((priorityItem != null) ? (Integer.toString(priorityItem.getPriority())) : "");
        priorityLabel.setLabelFor(this);
        priorityLabel.getStyleClass().add(cssLabelStyle);
        priorityLabel.getStyleClass().add(cssPriorityStyle);
        priorityLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
        priorityLabel.setPrefWidth(rectangle.getWidth());
        priorityLabel.setPrefHeight(rectangle.getHeight());
    }

    /**
     * This initializes the priority label property
     */
    private void initEmptyLabel(){
        emptyLabel = new Label(getEmptyQueueText());
        priorityLabel.setLabelFor(this);
        emptyLabel.setPrefHeight(rectangle.getHeight());
        emptyLabel.setPrefWidth(rectangle.getWidth());
        emptyLabel.getStyleClass().add(cssLabelStyle);
        emptyLabel.getStyleClass().add(cssEmptyLabelStyle);
        emptyLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
    }

    /**
     * @return  Node  This returns the node object
     */
    private Node getNode(){
        if(priorityItem != null){
            if (priorityItem.getItem() instanceof Node) {
                return (Node) priorityItem.getItem();
            }
        }
        return null;
    }

    /**
     * @return  PriorityItem&ltT&gt This returns the priorityItem
     */
    public PriorityItem<T> getPriorityItem() {
        return priorityItem;
    }

    /**
     * @return  Rectangle   This returns the rectangle object
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @return  int This returns the height of the rectangle
     */
    public static int getHeight() {
        return height;
    }

    /**
     * @return  int This returns the width of the rectangle
     */
    public static int getWidth() {
        return width;
    }

    /**
     * @return  String  This returns the empty queue text
     */
    private String getEmptyQueueText(){
        return Strings.emptyQueue;
    }

    /**
     * @param isV   Is This node adjacent to U?
     */
    public void setSelectedItem(boolean isV){
        priorityLabel.setTextFill(isV ? Colors.OTHER_COLOR_1 : Colors.OTHER_COLOR_2);
    }

    /**
     * set to default the style of the priority label
     */
    public void setUnselectedItem(){
        priorityLabel.setTextFill(Colors.LIGHT_TEXT_COLOR);
    }
}
