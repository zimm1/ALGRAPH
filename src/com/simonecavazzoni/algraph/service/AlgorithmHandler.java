package com.simonecavazzoni.algraph.service;

import com.simonecavazzoni.algraph.controller.CodeController;
import com.simonecavazzoni.algraph.controller.GraphController;
import com.simonecavazzoni.algraph.controller.PriorityQueueController;
import com.simonecavazzoni.algraph.model.Edge;
import com.simonecavazzoni.algraph.model.Node;
import com.simonecavazzoni.algraph.model.PriorityItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class AlgorithmHandler {

    private static final int PROGRAM_COUNTER_END = 10;

    private GraphController graphController;
    private PriorityQueueController priorityQueueController;
    private CodeController codeController;
    private int programCounter;
    private Node startNode;

    private Map<Node, Integer> resultDistance;
    private Map<Node, Node> resultParent;

    private Node u;
    private Iterator<Edge> adjacencies;
    private Node v;
    private int w;

    private Edge e;


    public AlgorithmHandler(GraphController graphController, PriorityQueueController priorityQueueController,
                            CodeController codeController) {
        this.graphController = graphController;
        this.priorityQueueController = priorityQueueController;
        this.codeController = codeController;

        restartAlgorithm();
    }

    public void restartAlgorithm(Node startNode) {
        this.startNode = startNode;

        restartAlgorithm();
    }

    public void restartAlgorithm() {
        programCounter = 0;
        u = null;
        v = null;
        resultDistance = graphController.getGraph().getNodes()
                .stream().collect(Collectors.toMap(n -> n, n -> n.equals(this.startNode) ? 0 : Integer.MAX_VALUE));
        resultParent = new HashMap<>();

        priorityQueueController.clear();
        adjacencies = null;
    }

    public void executeStep() {
        if (startNode == null || isFinished()) {
            return;
        }

        codeController.selectLine(programCounter == 10 ? -1 : programCounter);

        switch (programCounter) {
            // S.add(r)
            case 0:
                priorityQueueController.push(startNode, 0);
                break;
            // while not S.isEmpty() do
            case 1:
                if (priorityQueueController.getQueue().isEmpty()) {
                    programCounter = PROGRAM_COUNTER_END;
                    return;
                }
                break;
            // u <- S.pop()
            case 2:
                PriorityItem<Node> priorityItem = priorityQueueController.pop();
                u = priorityItem.getItem();

                graphController.highlight(u, resultParent.get(u));
                u.getUi().getDistanceLabel().setText(String.valueOf(resultDistance.get(u)));
                break;
            // foreach v belonging to G.adj(u) do
            case 3:
                if (adjacencies == null) {
                    adjacencies = graphController.getGraph().getAdjacencies().get(u).iterator();
                }
                if (!adjacencies.hasNext()) {
                    programCounter = 1;
                    adjacencies = null;
                    return;
                }
                e = adjacencies.next();
                v = e.getN2();
                w = e.getWeight();
                break;
            // if d[u] + w(u, v) < d[v] then
            case 4:
               if (resultDistance.get(u) + w >= resultDistance.get(v)) {
                    programCounter = 3;
                    return;
               }
               break;
           // if not b[v] then
            case 5:
                if (priorityQueueController.getQueue().existItem(v)) {
                    programCounter = 7;
                    return;
                }
                break;
            // S.insert(v, d[u] + w(u, v))
            case 6:
                priorityQueueController.push(v, resultDistance.get(u) + w);
                programCounter = 8;
                return;
            // else s.update(v, d[u] + w(u, v))
            case 7:
                priorityQueueController.update(v, resultDistance.get(u) + w);
                break;
            // d[v] <- d[u] + w(u, v)
            case 8:
                resultDistance.put(v, resultDistance.get(u) + w);
                break;
            // T[v] <- u
            case 9:
                resultParent.put(v, u);
                programCounter = 3;
                return;
            case 10:
                priorityQueueController.clear();
                break;
        }

        programCounter++;
    }

    public void executeAll() {
        if (startNode == null || isFinished()) {
            return;
        }

        while (!isFinished()) {
            executeStep();
        }
    }

    public boolean isStarted() {
        return programCounter > 0;
    }

    public boolean isFinished() {
        return programCounter > PROGRAM_COUNTER_END;
    }

    /**
     * @return  boolean This returns true only if the user inserted the start node
     */
    public boolean existRootNode(){
        return startNode != null;
    }

    /**
     * @return  Node    This returns the current node
     */
    public Node getU() {
        return u;
    }

    /**
     * @return  Node    This returns the node adjacent to U (current node)
     */
    public Node getV() {
        return v;
    }

    /**
     * @param node  This is the node to search in resultDistance list
     * @return  Integer This returns the distance of the node to the start node
     */
    public Integer getResultDistance(Node node){
        return resultDistance.get(node);
    }

    /**
     * @return  Integer This return the weight of the edge
     */
    public Integer getWeight(){
        return w;
    }

    /**
     * @return  Edge    This returns the edge (u,v)
     */
    public Edge getE() {
        return e;
    }
}
