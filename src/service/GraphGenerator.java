package service;

import model.Edge;
import model.Graph;
import model.Node;
import util.*;

import java.util.*;

public class GraphGenerator {

    private static final int minNode = 3;

    private GraphGenerator(){}

    public static Graph generateGraph(int maxNode,int minWeight,int maxWeight) throws NumberNodesInvalid,
            WeightRangeMalformed {

        if(maxNode > Graph.MAX_NODES || maxNode < 0){
            throw new NumberNodesInvalid();
        }

        if(maxWeight < minWeight || maxWeight < 0 || minWeight < 0){
            throw new WeightRangeMalformed();
        }

        return generateGraph(maxNode,minWeight,maxWeight,true);
    }

    public static Graph generateGraph(int maxNode) throws NumberNodesInvalid {
        if(maxNode > Graph.MAX_NODES || maxNode < 0){
            throw new NumberNodesInvalid();
        }

        return generateUnorientedGraph(maxNode);
    }

    private static Graph generateUnorientedGraph(int maxNode){
        return generateGraph(maxNode,0,0,false);
    }

    private static Graph generateGraph(int maxNode, int minWeight, int maxWeight, boolean oriented){
        Random random = new Random();
        int numNodes = random.nextInt((maxNode - minNode) + 1) + minNode;
        int maxEdge = numNodes*(numNodes-1);
        if(!oriented){
            maxEdge = maxEdge / 2;
        }
        int minEdge;
        if(oriented){
            minEdge = (numNodes-1)*(numNodes-2);
        } else {
            minEdge = numNodes;
        }
        int numEdges = random.nextInt((maxEdge - minEdge) + 1) + minEdge;

        Graph graph = new Graph(oriented);

        for(int i = 0;i < numNodes;i++){
            Node item = new Node(Integer.toString(i));
            graph.addNode(item);
        }

        int i = 0;

        while(i < numEdges){
            int numNode1 = 0;
            int numNode2 = 0;
            while(numNode1 == numNode2){
                numNode1 = random.nextInt(numNodes);
                numNode2 = random.nextInt(numNodes);
            }
            Node node = new Node(Integer.toString(numNode1));
            Node node1 = new Node(Integer.toString(numNode2));
            Edge newEdge = new Edge(node,node1);
            if(oriented){
                int weight = random.nextInt((maxWeight - minWeight) + 1) + minWeight;
                newEdge.setWeight(weight);
            }
            boolean existEdge = false;
            for(Edge item:graph.getNodeAdjacencies(node)){
                if(item.equals(newEdge)){
                    existEdge = true;
                    break;
                } else {
                    if(!oriented){
                        Edge secondEdge = new Edge(node1,node);
                        if(item.equals(secondEdge)){
                            existEdge = true;
                            break;
                        }
                    }
                }
            }

            if(!existEdge){
                graph.addEdge(newEdge);
                i++;
            }

        }

        return graph;
    }
}
