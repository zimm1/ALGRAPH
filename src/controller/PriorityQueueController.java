package controller;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Node;
import model.PriorityItem;
import model.PriorityQueue;
import ui.PriorityItemUI;

import java.util.stream.Collectors;

public class PriorityQueueController implements ControllerInterface {

    private HBox root;

    private PriorityQueue<Node> priorityQueue;

    private ScrollPane scrollPane;
    private HBox priorityQueueLayout;
    private PriorityItemUI selectedNode;

    public PriorityQueueController(){

        root = new HBox();
        scrollPane = new ScrollPane();
        priorityQueueLayout = new HBox();
        selectedNode = new PriorityItemUI();

        priorityQueue = new PriorityQueue<>();

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setContent(priorityQueueLayout);

        updatePriorityItemUI();
    }

    @Override
    public Pane get() {
        return root;
    }

    public PriorityQueue<Node> getQueue() {
        return priorityQueue;
    }

    public void clear(){
        priorityQueue.clear();
        updatePriorityItemUI();
    }

    public void push(Node node, int priority){
        if (!priorityQueue.existItem(node)) {
            priorityQueue.push(node, priority);
            updatePriorityItemUI();
        }
    }

    public PriorityItem<Node> pop(){
        PriorityItem<Node> itemPopped = priorityQueue.pop();
        if(itemPopped != null){
            selectedNode = itemPopped.getPriorityItemUI();
            updatePriorityItemUI();
        }
        return itemPopped;
    }

    public void update(Node item, int newPriority) {
        priorityQueue.update(item, newPriority);
        updatePriorityItemUI();
    }

    private void updatePriorityItemUI(){
        root.getChildren().clear();
        priorityQueueLayout.getChildren().clear();

        priorityQueueLayout.getChildren().addAll(priorityQueue.getAll().stream().map(PriorityItem::getPriorityItemUI)
                .collect(Collectors.toList()));
        root.getChildren().addAll(selectedNode, scrollPane);
    }
}
