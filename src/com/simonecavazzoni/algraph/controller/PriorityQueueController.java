package com.simonecavazzoni.algraph.controller;

import com.simonecavazzoni.algraph.res.Colors;
import com.simonecavazzoni.algraph.res.Strings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import com.simonecavazzoni.algraph.model.Node;
import com.simonecavazzoni.algraph.model.PriorityItem;
import com.simonecavazzoni.algraph.model.PriorityQueue;
import com.simonecavazzoni.algraph.ui.PriorityItemUI;

import java.util.stream.Collectors;

public class PriorityQueueController extends Controller {
    private PriorityQueue<Node> priorityQueue;

    private ScrollPane scrollPane;
    private HBox priorityQueueLayout;
    private HBox container;
    private PriorityItemUI selectedNode;
    private Pane pane;
    private Line line;
    private Line leftArrow;
    private Line rightArrow;
    private Label title;

    private static final int DEFAULT_LINE_DIMENSION = 100;

    private static final String CSS_SCROLL_PANE_ID = "scrollPane";
    private static final String CSS_TITLE_CLASS = "titleStyle";

    public PriorityQueueController(){
        setRoot(new VBox());
        ((VBox)get()).setAlignment(Pos.CENTER);
        scrollPane = new ScrollPane();
        priorityQueueLayout = new HBox(3);
        pane = new Pane();
        selectedNode = new PriorityItemUI();

        line = new Line();
        leftArrow = new Line();
        rightArrow = new Line();

        priorityQueue = new PriorityQueue<>();

        pane.setMaxHeight(PriorityItemUI.getHeight());
        pane.setMinHeight(PriorityItemUI.getHeight());
        pane.setMaxWidth(PriorityItemUI.getWidth());
        pane.setMinWidth(PriorityItemUI.getWidth());

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setId(CSS_SCROLL_PANE_ID);
        scrollPane.setContent(priorityQueueLayout);

        title = new Label(Strings.priority_queue_title);
        title.setAlignment(Pos.CENTER);
        title.setPrefWidth(get().getPrefWidth());
        title.setTextFill(Colors.PRIMARY_COLOR);
        title.getStyleClass().add(CSS_TITLE_CLASS);

        container = new HBox();

        updatePriorityItemUI();
    }

    /**
     * @return PriorityQueue This returns the PriorityQueue list
     */
    public PriorityQueue<Node> getQueue() {
        return priorityQueue;
    }

    /**
     * delete all nodes shown in the root
     */
    public void clear(){
        priorityQueue.clear();
        selectedNode = new PriorityItemUI();
        updatePriorityItemUI();
    }

    /**
     * @param node  This is the new node to insert in the queue
     * @param priority  This is the priority of the new node
     */
    public void push(Node node, int priority){
        if (!priorityQueue.existItem(node)) {
            priorityQueue.push(node, priority);
            updatePriorityItemUI();
        }
    }

    /**
     * @return PriorityItem This returns the PriorityItem with the lower priority
     */
    public PriorityItem<Node> pop(){
        PriorityItem<Node> itemPopped = priorityQueue.pop();
        if(itemPopped != null){
            selectedNode = itemPopped.getPriorityItemUI();
            updatePriorityItemUI();
        }
        return itemPopped;
    }

    /**
     * @param item  This is the item to search in the PriorityQueue
     * @param newPriority This is the new priority of the item searched
     */
    public void update(Node item, int newPriority) {
        priorityQueue.update(item, newPriority);
        updatePriorityItemUI();
    }

    /**
     * update the nodes shown in the stage
     */
    private void updatePriorityItemUI(){
        root.getChildren().clear();
        priorityQueueLayout.getChildren().clear();
        pane.getChildren().clear();
        container.getChildren().clear();

        updateLines();

        root.getChildren().add(title);

        if(!priorityQueue.isEmpty()){
            pane.getChildren().addAll(line,rightArrow,leftArrow);
        }

        container.getChildren().addAll(selectedNode,pane);

        priorityQueueLayout.getChildren().addAll(priorityQueue.getAll().stream().map(PriorityItem::getPriorityItemUI)
                .collect(Collectors.toList()));


        container.getChildren().add(scrollPane);
        root.getChildren().add(container);
    }

    /**
     * update the arrow coordinates
     */
    private void updateLines(){
        double startX = 0;
        double endX = DEFAULT_LINE_DIMENSION;

        double startY = PriorityItemUI.getHeight()/2;
        double endY = PriorityItemUI.getHeight()/2;
        double offset = PriorityItemUI.getWidth()/4;
        double endXRightLeftArrow = endX/4;

        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);

        rightArrow.setStartX(startX);
        rightArrow.setEndX(endXRightLeftArrow);
        rightArrow.setStartY(startY);
        rightArrow.setEndY(startY-offset);

        leftArrow.setStartX(startX);
        leftArrow.setEndX(endXRightLeftArrow);
        leftArrow.setStartY(startY);
        leftArrow.setEndY(startY+offset);
    }

    @Override
    public String toString() {
        return priorityQueue.toString();
    }

    /**
     * @param node This is the node to search in the queue and to change its style
     */
    public void selectItem(Node node){

        deselectItem();

        if(node != null){
            priorityQueue.getAll().stream().filter(n -> n.getItem().equals(node)).findAny()
                    .ifPresent(nodePriorityItem -> nodePriorityItem.getPriorityItemUI().setSelectedItem(true));


            selectedNode.setSelectedItem(false);
        }
    }

    /**
     * set to default the style of all items of queue
     */
    private void deselectItem(){
        priorityQueue.getAll().forEach(priorityItem -> priorityItem.getPriorityItemUI()
                .setUnselectedItem());
        selectedNode.setUnselectedItem();
    }
}
