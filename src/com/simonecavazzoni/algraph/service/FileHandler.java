package com.simonecavazzoni.algraph.service;

import com.simonecavazzoni.algraph.model.Edge;
import com.simonecavazzoni.algraph.model.Graph;
import com.simonecavazzoni.algraph.model.Node;

import java.io.*;

/**
 *This class handle the file saving and loading process
 * with the two main methods, 'saveGraph' and 'loadGraph'
 */
public abstract class FileHandler {

    private static File currentFile = null;

    public static File getCurrentFile() {
        return currentFile;
    }

    /**
     * rgerger
     * @param graph Graph to save
     * @param file File i which the graph will be saved
     * @throws IOException
     * This method save a generated graph to a file passed as parameter
     */
    public static void saveGraph(Graph graph, File file) throws IOException {
        if (file == null) {
            throw new IOException();
        }
        currentFile = file;

        FileWriter fileout = new FileWriter(file);

        if (graph.getAdjacencies().size() != 0) { //check for adjacencies
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

        fileout.close(); // close the file
    }

    /**
     * @param file The file from which the graph will be loaded
     * @return return a 'graph' object that will be used in the MainController class
     * @throws IOException
     * This method load graph from the file passed as parameter
     */
    public static Graph loadGraph(File file) throws IOException {
        if (file == null) {
            throw new IOException();
        }
        currentFile = file;

        BufferedReader fileIn = new BufferedReader(new FileReader(file));
        String line;

        if ((line = fileIn.readLine()) == null) {
            throw new IOException();
        }
        Graph graph = new Graph(line.split(" ")[0].equals("1"));

        if ((line = fileIn.readLine()) == null) {
            throw new IOException();
        }
        for (String label : line.split(" ")) {
            graph.addNode(label);
        }

        while ((line = fileIn.readLine()) != null) {
            String[] parts = line.split(" ");
            Edge edge = graph.addEdge(parts[0], parts[1]);
            if (edge != null) {
                edge.setWeight(Integer.parseInt(parts[2]));
            }
        }

        return graph;
    }

    /**
     * @param fileOut Input stream parameter
     * @param s String passed as parameter
     *          This method is an efficient way to write spaces between char(used in saveGraph)
     */
    private static void writeWS(FileWriter fileOut, String s) {
        try {
            fileOut.write(s);
            fileOut.write(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

