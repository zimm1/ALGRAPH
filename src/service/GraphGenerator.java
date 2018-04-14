package service;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.*;

public class GraphGenerator {
    private static final int MAX_NODES = 20;
    private static final int MIN_NODES = 3;

    private GraphGenerator(){}

    public static Graph generateGraph(int numNodes) {
        return generateGraph(numNodes,0,0);
    }

    public static Graph generateGraph(int numNodes, int minWeight, int maxWeight) {
        return generateGraph(numNodes, minWeight, maxWeight,true);
    }

    public static Graph generateGraph(int numNodes, int minWeight, int maxWeight, boolean directed){

        if (numNodes > MAX_NODES || numNodes < MIN_NODES) {
            return null;
        }

        if (maxWeight < minWeight || maxWeight < 0 || minWeight < 0) {
            return null;
        }

        Random random = new Random();

        int maxEdges = directed ? numNodes * (numNodes - 1) : numNodes * (numNodes - 1) / 2;
        int minEdges = directed ? (numNodes - 1) * (numNodes - 2) : numNodes;
        int numEdges = random.nextInt(maxEdges - minEdges + 1) + minEdges;

        Graph graph = new Graph(directed);

        for (int i = 0; i < numNodes; i++) {
            graph.addNode(Integer.toString(i + 1));
        }

        int i = 0;

        while (i < numEdges) {
            int rand1 = 0;
            int rand2 = 0;

            while (rand1 == rand2) {
                rand1 = random.nextInt(numNodes);
                rand2 = random.nextInt(numNodes);
            }

            Node[] nodes = graph.getNodes().toArray(new Node[0]);
            Node n1 = nodes[rand1];
            Node n2 = nodes[rand2];

            Edge newEdge = new Edge(n1, n2);
            if (directed) {
                int weight = random.nextInt(maxWeight - minWeight + 1) + minWeight;
                newEdge.setWeight(weight);
            }

            if (graph.addEdge(newEdge) != null) {
                i++;
            }
        }

        return graph;
    }
}
