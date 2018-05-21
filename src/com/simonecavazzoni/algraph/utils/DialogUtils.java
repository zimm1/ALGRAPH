package com.simonecavazzoni.algraph.utils;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import com.simonecavazzoni.algraph.res.Strings;
import com.simonecavazzoni.algraph.service.FileHandler;
import com.simonecavazzoni.algraph.service.GraphGenerator;
import javafx.stage.Stage;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class DialogUtils {

    /**
     * @param title This is the title of the ChoiceDialog
     * @param header This is the header of the ChoiceDialog
     * @param content This is the content of the ChoiceDialog
     * @param value This is the list of the choices to show
     * @return This returns the item selected by user
     * @throws Exception Error during dialog usage
     */
    public static String showTextSpinnerDialog(String title, String header, String content, String... value)
            throws Exception {

        ChoiceDialog<String> dialog = new ChoiceDialog<>(value[0], value);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/com/simonecavazzoni/algraph/res/images/program_icon.png"));

        Optional<String> result = dialog.showAndWait();
        if(result.isPresent()){
            return result.get();
        }

        throw new Exception();
    }

    /**
     * @param title This is the title of the TextInputDialog
     * @param header This is the header of the TextInputDialog
     * @param content This is the content of the TextInputDialog
     * @param defaultValue This is the default value of the TextInputDialog
     * @return This returns the text inserted by user
     * @throws Exception Error during dialog usage
     */
    public static String showTextInputDialog(String title, String header, String content, String defaultValue)
            throws Exception {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/com/simonecavazzoni/algraph/res/images/program_icon.png"));

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }

        throw new Exception();
    }

    /**
     * @param title This is the title of the Alert
     * @param header This is the header of the Alert
     * @param content This is the content of the Alert
     */
    public static void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/com/simonecavazzoni/algraph/res/images/program_icon.png"));

        alert.showAndWait();
    }

    /**
     * @param save Save the file
     * @return This returns the file selected
     */
    public static File showFileChooserDialog(boolean save) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(save ? Strings.save_file: Strings.open_file);

        File currentFile = FileHandler.getCurrentFile();
        if (currentFile != null) {
            fileChooser.setInitialDirectory(currentFile.getParentFile());
            fileChooser.setInitialFileName(currentFile.getName());
        }

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(Strings.program_files, "*." + Strings.program_extension),
                new FileChooser.ExtensionFilter(Strings.all_files, "*")
        );

        return save ? fileChooser.showSaveDialog(null) : fileChooser.showOpenDialog(null);
    }

    /**
     * @return GraphGeneratorDialogResult This returns the dialog to show when generated a new graph
     * @throws Exception Error during dialog usage
     */
    public static GraphGeneratorDialogResult showGraphGeneratorDialog() throws Exception {
        Dialog<GraphGeneratorDialogResult> dialog = new Dialog<>();
        dialog.setTitle(Strings.generate_graph);

        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/com/simonecavazzoni/algraph/res/images/program_icon.png"));

        ButtonType generateButtonType = new ButtonType(Strings.generate, ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(generateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField numNodes = new TextField();
        numNodes.setText(String.valueOf(GraphGenerator.DEFAULT_NUM_NODES));
        TextField minWeight = new TextField();
        minWeight.setText(String.valueOf(GraphGenerator.DEFAULT_MIN_WEIGHT));
        TextField maxWeight = new TextField();
        maxWeight.setText(String.valueOf(GraphGenerator.DEFAULT_MAX_WEIGHT));
        CheckBox directed = new CheckBox();
        directed.setSelected(true);

        grid.add(new Label(Strings.num_nodes), 0,0);
        grid.add(numNodes, 1, 0);
        grid.add(new Label(Strings.min_weight), 0,1);
        grid.add(minWeight, 1, 1);
        grid.add(new Label(Strings.max_weight), 0,2);
        grid.add(maxWeight, 1, 2);
        grid.add(new Label(Strings.directed_input), 0, 3);
        grid.add(directed, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == generateButtonType) {
                try {
                    return new GraphGeneratorDialogResult(
                            Integer.parseInt(numNodes.getText()),
                            Integer.parseInt(minWeight.getText()),
                            Integer.parseInt(maxWeight.getText()),
                            directed.isSelected()
                    );
                } catch (NumberFormatException e) {
                    return new GraphGeneratorDialogResult(e);
                }
            }

            return null;
        });

        Optional<GraphGeneratorDialogResult> result = dialog.showAndWait();
        if (result.isPresent()) {
            GraphGeneratorDialogResult res = result.get();
            if (res.getException() != null) {
                throw res.getException();
            }
            return res;
        }

        return null;
    }

    /**
     * Result of a graph generator dialog
     */
    public static class GraphGeneratorDialogResult {
        private boolean directed;
        private int numNodes;
        private int minWeight;
        private int maxWeight;

        private Exception exception;

        /**
         * Initializes result with an error
         * @param exception Exception threw by dialog
         */
        public GraphGeneratorDialogResult(Exception exception) {
            this.exception = exception;
        }

        /**
         * Initializes result with a correct result
         * @param numNodes Number of nodes to generate
         * @param minWeight Minimum weight of graph edges
         * @param maxWeight Maximum weight of graph edges
         * @param directed Graph directed
         */
        public GraphGeneratorDialogResult(int numNodes, int minWeight, int maxWeight, boolean directed) {
            this.numNodes = numNodes;
            this.minWeight = minWeight;
            this.maxWeight = maxWeight;
            this.directed = directed;
        }
        
        public int getNumNodes() {
            return numNodes;
        }

        public int getMinWeight() {
            return minWeight;
        }

        public int getMaxWeight() {
            return maxWeight;
        }

        public boolean isDirected() {
            return directed;
        }

        public Exception getException() {
            return exception;
        }
    }
}
