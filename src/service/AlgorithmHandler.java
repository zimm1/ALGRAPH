package service;

import controller.GraphController;
import controller.PriorityQueueController;
import model.Edge;
import model.Node;
import model.PriorityItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class AlgorithmHandler {

    private static final int PROGRAM_COUNTER_END = 10;

    private GraphController graphController;
    private PriorityQueueController priorityQueueController;
    private int programCounter;
    private Node startNode;

    private Map<Node, Integer> resultDistance;
    private Map<Node, Node> resultParent;

    private Node u;
    private Iterator<Edge> adjacencies;
    private Node v;
    private int w;


    public AlgorithmHandler(GraphController graphController, PriorityQueueController priorityQueueController) {
        this.graphController = graphController;
        this.priorityQueueController = priorityQueueController;

        restartAlgorithm();
    }

    public void restartAlgorithm() {
        restartAlgorithm(null);
    }

    public void restartAlgorithm(Node startNode) {
        if (startNode != null) {
            this.startNode = startNode;
        }

        programCounter = 0;
        resultDistance = graphController.getGraph().getNodes()
                .stream().collect(Collectors.toMap(n -> n, n -> n.equals(startNode) ? 0 : Integer.MAX_VALUE));
        resultParent = new HashMap<>();

        priorityQueueController.clear();
        adjacencies = null;
    }

    public void executeStep() {
        if (startNode == null || programCounter > PROGRAM_COUNTER_END) {
            return;
        }

        uiStep();

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
                break;
            // foreach v belonging to G.adj(u) do
            case 4:
                if (adjacencies == null) {
                    adjacencies = graphController.getGraph().getAdjacencies().get(u).iterator();
                }
                if (!adjacencies.hasNext()) {
                    programCounter = 1;
                    adjacencies = null;
                    return;
                }
                Edge e = adjacencies.next();
                v = e.getN2();
                w = e.getWeight();
                break;
            // if d[u] + w(u, v) < d[v] then
            case 5:
               if (resultDistance.get(u) + w >= resultDistance.get(v)) {
                    programCounter = 4;
                    return;
               }
               break;
           // if not b[v] then
            case 6:
                if (priorityQueueController.getQueue().existItem(v)) {
                    programCounter = 8;
                    return;
                }
                break;
            // S.insert(v, d[u] + w(u, v))
            case 7:
                priorityQueueController.push(v, resultDistance.get(u) + w);
                programCounter = 9;
                return;
            // else s.update(v, d[u] + w(u, v))
            case 8:
                priorityQueueController.update(v, resultDistance.get(u) + w);
                break;
            // d[v] <- d[u] + w(u, v)
            // T[v] <- u
            case 9:
                resultDistance.put(v, resultDistance.get(u) + w);
                resultParent.put(v, u);
                programCounter = 4;
                return;
            case 10:
                priorityQueueController.clear();
                break;
        }

        programCounter++;
    }

    public void executeAll() {
        while (programCounter <= PROGRAM_COUNTER_END) {
            executeStep();
        }
    }

    private void uiStep() {
        switch (programCounter - 1) {
            case 2:
                graphController.highlight(u, resultParent.get(u));
                break;
        }
    }
}
