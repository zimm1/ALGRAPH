package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import model.Node;
import model.PriorityItem;
import model.PriorityQueue;
import ui.PriorityItemUI;

import java.util.stream.Collectors;

public class PriorityQueueController {

    private PriorityQueue<Node> priorityQueue;

    @FXML
    ScrollPane scrollPane;
    @FXML
    HBox priorityQueueLayout;
    @FXML
    PriorityItemUI selectedNode;

    @FXML
    public void initialize(){
        priorityQueue = new PriorityQueue<>();

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void push(Node node,int priority){
        if(!priorityQueue.existItem(node)){
            priorityQueue.push(node,priority);
            updatePriorityItemUI();
        }
    }

    public void removeNode(Node node){
        priorityQueue.remove(node);
        updatePriorityItemUI();
    }

    public PriorityItem pop(){
        PriorityItem itemPopped = priorityQueue.pop();
        if(itemPopped != null){
            selectedNode = itemPopped.getPriorityItemUI();
            updatePriorityItemUI();
        }
        return itemPopped;
    }

    private void updatePriorityItemUI(){
        priorityQueueLayout.getChildren().clear();

        priorityQueueLayout.getChildren().add(selectedNode);

        priorityQueueLayout.getChildren().addAll(priorityQueue.getAll().stream().map(PriorityItem::getPriorityItemUI).collect(Collectors.toList()));
    }

}
