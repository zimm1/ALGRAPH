package service;

import model.Edge;
import model.Graph;
import model.Node;
import utils.DialogUtils;

import java.io.*;
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
    private static int cNode, cEdges, cN1, cN2, cWeght;

    public static void writeWS(FileWriter fileOut, String s) {
        try {
            fileOut.write(s);
            fileOut.write(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileWriter(Graph graph, String path) throws IOException {
        FileWriter fileout = new FileWriter(path + ".txt");

        if (graph.getAdjacencies().size() != 0) {
            writeWS(fileout, graph.isDirected() ? "1" : "0");
            fileout.write("\r\n");
        }

        for (Node n : graph.getNodes()) {
            writeWS(fileout, n.getLabel());
        }

        fileout.write("\r\n");


        for (Edge e : graph.getEdges()) {
            writeWS(fileout, e.getN1().getLabel());
            writeWS(fileout, e.getN2().getLabel());
            writeWS(fileout, String.valueOf(e.getWeight()));
            fileout.write("\r\n");

        }


        fileout.close(); // chiude il file

    }

    public static Graph FileReader(String path) throws IOException {

        if (isFile(path)) {

            BufferedReader filein = new BufferedReader(new FileReader(path + ".txt"));
            String line;
            if ((line = filein.readLine()) == null) {
                throw new IOException();
            }
            Graph graph = new Graph(line.split(" ")[0].equals("1"));
            if ((line = filein.readLine()) == null) {
                throw new IOException();
            }

            for (String label : line.split(" ")) {
                graph.addNode(label);
            }

            while ((line = filein.readLine()) != null) {
                String[] parts = line.split(" ");
                Edge edge = graph.addEdge(parts[0], parts[1]);
                if (edge != null) {
                    edge.setWeight(Integer.parseInt(parts[2]));
                }
            }

            return graph;
        } else {
            return null;
        }

    }

    public static boolean isFile(String path) {
        File f = new File(path + ".txt");

        if (f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

}

