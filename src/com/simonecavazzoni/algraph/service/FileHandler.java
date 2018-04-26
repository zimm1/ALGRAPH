package com.simonecavazzoni.algraph.service;

import com.simonecavazzoni.algraph.model.Edge;
import com.simonecavazzoni.algraph.model.Graph;
import com.simonecavazzoni.algraph.model.Node;

import java.io.*;

public abstract class FileHandler {

    private static File currentFile = null;

    public static File getCurrentFile() {
        return currentFile;
    }

    public static void saveGraph(Graph graph, File file) throws IOException {
        if (file == null) {
            throw new IOException();
        }
        currentFile = file;

        FileWriter fileout = new FileWriter(file);

        writeWS(fileout, graph.isDirected() ? "1" : "0");
        fileout.write("\r\n");

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

    public static Graph loadGraph(File file) throws IOException {
        if (file == null) {
            throw new IOException();
        }
        currentFile = file;

        BufferedReader filein = new BufferedReader(new FileReader(file));

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
    }

    private static void writeWS(FileWriter fileOut, String s) {
        try {
            fileOut.write(s);
            fileOut.write(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

