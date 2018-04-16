package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Node;
import model.PriorityItem;
import model.PriorityQueue;

import java.util.stream.Collectors;

public class PriorityQueueController {

    private PriorityQueue<Node> priorityQueue;

    @FXML
    Pane priorityQueueLayout;

    @FXML
    public void initialize(){
        priorityQueue = new PriorityQueue<>();

        Node node = new Node("A");
        Node node1 = new Node("B");
        Node node2 = new Node("C");

        priorityQueue.push(node,6);
        //priorityQueue.push(node1,7);
        /*priorityQueue.decrease(node2,1);
        priorityQueue.remove(priorityQueue.read(node));

        PriorityItem priorityItem = null;
        priorityItem = priorityQueue.read(node);
        if(priorityItem != null){
            System.out.println(priorityItem.getItem() + " " + priorityItem.getPriority());
        }*/

        initEventHandlers();
        updatePriorityItemUI();

    }

    private void initEventHandlers(){

    }

    public void addNode(Node node,int priority){
        if(!priorityQueue.existItem(node)){
            this.priorityQueue.push(node,priority);
        }
    }

    private void updatePriorityItemUI(){
        priorityQueueLayout.getChildren().clear();

        priorityQueueLayout.getChildren().addAll(priorityQueue.getAll().stream().map(PriorityItem::getPriorityItemUI).collect(Collectors.toSet()));

    }

}
