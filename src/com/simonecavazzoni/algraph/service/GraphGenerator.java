package com.simonecavazzoni.algraph.service;

import com.simonecavazzoni.algraph.model.Edge;
import com.simonecavazzoni.algraph.model.Graph;
import com.simonecavazzoni.algraph.model.Node;

import java.util.*;

/**
 * This class expose two methods to create oriented/unoriented graph.
 */
public abstract class GraphGenerator {

    public static final int DEFAULT_NUM_NODES = 7;
    public static final int DEFAULT_MIN_WEIGHT = 1;
    public static final int DEFAULT_MAX_WEIGHT = 20;

    private static final int MIN_NODES = 3;
    private static final int MAX_NODES = 15;

    /**
     * @return  This returns the default unoriented graph
     * @throws Exception
     */
    public static Graph generateGraph() throws Exception {
        return generateGraph(DEFAULT_NUM_NODES);
    }

    /**
     * @param numNodes  This is the maximum number nodes of the graph
     * @return  Graph   This returns the unoriented graph
     * @throws Exception
     */
    public static Graph generateGraph(int numNodes) throws Exception {
        return generateGraph(numNodes, DEFAULT_MIN_WEIGHT, DEFAULT_MAX_WEIGHT);
    }

    /**
     * @param numNodes  This is the maximum number nodes of the graph
     * @param minWeight This is the minimum weight of the edge
     * @param maxWeight This is the maximum weight of the edge
     * @return  Graph   This returns the oriented graph
     * @throws Exception
     */
    public static Graph generateGraph(int numNodes, int minWeight, int maxWeight) throws Exception {
        return generateGraph(numNodes, minWeight, maxWeight,true);
    }

    /**
     * @param numNodes  This is the maximum number nodes of the graph
     * @param minWeight This is the minimum weight of the edge
     * @param maxWeight This is the maximum weight of the edge
     * @param directed  Is the graph directed?
     * @return  Graph   This returns the generated graph
     * @throws Exception
     */
    public static Graph generateGraph(int numNodes, int minWeight, int maxWeight, boolean directed) throws Exception {
        if (numNodes > MAX_NODES || numNodes < MIN_NODES) {
            throw new Exception();
        }

        if (maxWeight < minWeight || maxWeight < 0 || minWeight < 0) {
            throw new Exception();
        }

        Random random = new Random();

        int minEdges = (numNodes - 1) * (directed ? 2 : 1);
        int maxEdges = (numNodes - 1) * 3 / (directed ? 1 : 2);
        int numEdges = random.nextInt(maxEdges - minEdges + 1) + minEdges;

        Graph graph = new Graph(directed);

        for (int i = 0; i < numNodes; i++) {
            graph.addNode(String.valueOf((char) (i + 65)));
        }

        int i = 0;

        while (i < numEdges) {
            int rand1 = 0;
            int rand2 = 0;

            while (rand1 == rand2) {
                rand1 = random.nextInt(numNodes);
                rand2 = random.nextInt(numNodes);
            }

            Node[] nodes = graph.getAdjacencies().keySet().toArray(new Node[0]);
            Node n1 = nodes[rand1];
            Node n2 = nodes[rand2];

            Edge newEdge = new Edge(n1, n2, Edge.MIN_WEIGHT, directed);
            int weight = random.nextInt(maxWeight - minWeight + 1) + minWeight;
            newEdge.setWeight(weight);

            if (graph.addEdge(newEdge) != null) {
                i++;
            }
        }

        return graph;
    }
}
