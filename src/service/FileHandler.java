package service;

import model.Edge;
import model.Graph;
import model.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class FileHandler {

    private static int c = 0;
    private static ArrayList labels = new ArrayList();
    private static ArrayList edges = new ArrayList();
    private static ArrayList N1 = new ArrayList();
    private static ArrayList N2 = new ArrayList();
    private static ArrayList weight = new ArrayList();
    private static int cNode,cEdges,cN1,cN2,cWeght;

    public static void writeWS(FileWriter fileOut, String s){
        try {
            fileOut.write(s);
            fileOut.write(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileWriter(Graph graph, String path) throws IOException {
        FileWriter fileout = new FileWriter(path + ".txt");


        for (Node n : graph.getNodes()) {
            writeWS(fileout, n.getLabel());
            writeWS(fileout, String.valueOf(graph.getAdjacencies().get(n).size()));


            for (Edge e : graph.getAdjacencies().get(n)) {
                writeWS(fileout, e.getN1().getLabel());
                writeWS(fileout, e.getN2().getLabel());
                writeWS(fileout, String.valueOf(e.getWeight()));

            }
            fileout.write("\r\n");
        }

        fileout.close(); // chiude il file

    }

public static Graph FileReader(String path) throws IOException{

    BufferedReader filein = new BufferedReader(new FileReader(path + ".txt"));
    String line;
    Graph graph = new Graph(true);

    while((line = filein.readLine()) != null){
        String[] parts = line.split(" ");
        String label = parts[0];
        Node node = new Node(label);
        graph.addNode(node);
            int numEdge = Integer.parseInt(parts[1]);
            for(int j = 0; j < numEdge;j++){
                int posNode1 = 2 + j*3;
                int posNode2 = 3 + j*3;
                int posWeight = 4 + j*3;
                Edge edge = new Edge(new Node(parts[posNode1]),new Node(parts[posNode2]),posWeight);
                graph.addEdge(edge);
            }
        }

        return graph;

    }

public static void loadGraph(){

}


}

