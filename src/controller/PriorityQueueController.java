package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Node;
import model.PriorityQueue;

public class PriorityQueueController {

    private PriorityQueue<Node> priorityQueue;

    @FXML
    Pane priorityQueueLayout;

    @FXML
    public void initialize(){
        priorityQueue = new PriorityQueue<>();

        Node node = new Node("A");
        Node node1 = new Node("B");

        priorityQueue.push(node,5);
        priorityQueue.push(node1,7);

        initEventHandlers();

    }

    private void initEventHandlers(){

    }

}
