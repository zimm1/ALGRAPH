package com.simonecavazzoni.algraph.controller;

import com.simonecavazzoni.algraph.model.Edge;
import com.simonecavazzoni.algraph.ui.AlgorithmInfoUI;
import com.simonecavazzoni.algraph.utils.AsyncUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import com.simonecavazzoni.algraph.model.Node;

import com.simonecavazzoni.algraph.res.Strings;
import com.simonecavazzoni.algraph.service.AlgorithmHandler;
import com.simonecavazzoni.algraph.service.FileHandler;
import com.simonecavazzoni.algraph.service.GraphGenerator;
import com.simonecavazzoni.algraph.utils.DialogUtils;
import com.simonecavazzoni.algraph.utils.WindowUtils;
import javafx.util.StringConverter;

import java.io.File;
import java.io.IOException;


/**
 * Controller which contains the menu and all other controllers.
 */
public class MainController extends Controller {

    private final static int MAX_EXECUTION_TIME = 2000;
    private final static int MAX_SPEED_SLIDER = 20;

    private GraphController graphController;
    private PriorityQueueController priorityQueueController;
    private CodeController codeController;

    private AlgorithmHandler algorithmHandler;

    private Button generateButton;
    private Button openButton;
    private Button saveButton;
    private Button stepButton;
    private Button executeButton;
    private Button pauseButton;
    private Slider executionSpeedSlider;
    private Button resetButton;
    private Button infoButton;

    private boolean executionPaused;
    private boolean pendingExecution;

    /**
     * Initializes all the components of the panel.
     */
    public MainController() {
        root = new BorderPane();

        ((BorderPane)root).setTop(initMenu());
        ((BorderPane)root).setCenter(initGraph());
        ((BorderPane)root).setRight(initCode());
        ((BorderPane)root).setBottom(initPriorityQueue());

        algorithmHandler = new AlgorithmHandler(graphController, priorityQueueController, codeController);

        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> graphController.onKeyPressed(event));
    }

    /**
     * Initializes graph panel.
     * @return Graph panel
     */
    private Pane initGraph() {
        graphController = new GraphController(this);
        return graphController.get();
    }

    /**
     * Initializes priority queue panel.
     * @return Priority queue panel
     */
    private Pane initPriorityQueue() {
        priorityQueueController = new PriorityQueueController();
        return priorityQueueController.get();
    }

    /**
     * Initializes pseudo code panel.
     * @return Pseudo code panel
     */
    private Pane initCode() {
        codeController = new CodeController(this);
        return codeController.get();
    }

    /**
     * Initializes menu panel.
     * @return Menu panel
     */
    private Pane initMenu() {
        HBox menuContainer = new HBox();

        menuContainer.setId("menu");
        menuContainer.setPrefHeight(90);
        menuContainer.setSpacing(5);

        menuContainer.getChildren().addAll(buildGraphSection(), buildExecuteSection(), buildHelpSection());
        return menuContainer;
    }

    /**
     * Initializes menu graph section.
     * @return Graph section
     */
    private Pane buildGraphSection() {
        VBox root = new VBox();

        GridPane menuPane = new GridPane();
        menuPane.setGridLinesVisible(false);
        menuPane.setHgap(5);

        menuPane.add(generateButton = buildButton(
                Strings.generate, Strings.generate_graph,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_note_add_black_24dp_1x.png",
                event -> generateGraph()), 0, 0);
        menuPane.add(openButton = buildButton(
                Strings.open, Strings.open_file,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_folder_open_black_24dp_1x.png",
                event -> loadGraphFromFile()), 1, 0);
        menuPane.add(saveButton = buildButton(
                Strings.save, Strings.save_file,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_save_black_24dp_1x.png",
                event -> saveGraphToFile()), 2, 0);

        Label label = new Label(Strings.graph);

        VBox vbox = new VBox();
        vbox.getChildren().add(label);
        VBox.setVgrow(label, Priority.ALWAYS);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setStyle("-fx-padding: 5 0 0 0");

        menuPane.add(vbox, 0, 1, 3, 1);

        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menuSection");
        root.getChildren().add(menuPane);

        return root;
    }

    /**
     * Initializes menu execute section.
     * @return Execute section
     */
    private Pane buildExecuteSection() {
        VBox root = new VBox();

        GridPane menuPane = new GridPane();
        menuPane.setGridLinesVisible(false);
        menuPane.setHgap(5);

        menuPane.add(stepButton = buildButton(
                Strings.step, Strings.execute_step,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_skip_next_black_24dp_1x.png",
                event -> executeStep()), 0, 0);
        menuPane.add(executeButton = buildButton(
                Strings.execute_all, Strings.execute_all,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_play_arrow_black_24dp_1x.png",
                event -> executeAll()), 1, 0);
        menuPane.add(pauseButton = buildButton(
                Strings.execute_pause, Strings.execute_pause,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_pause_black_24dp_1x.png",
                event -> pauseExecution()), 2, 0);
        menuPane.add(resetButton = buildButton(
                Strings.execute_reset, Strings.execute_reset,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_replay_black_24dp_1x.png",
                event -> resetExecution(null)), 4, 0);

        executionSpeedSlider = new Slider();
        executionSpeedSlider.setMin(1);
        executionSpeedSlider.setMax(MAX_SPEED_SLIDER);
        executionSpeedSlider.setValue(2);
        executionSpeedSlider.setBlockIncrement(1);
        executionSpeedSlider.setPrefWidth(100);
        executionSpeedSlider.setShowTickLabels(true);
        executionSpeedSlider.setShowTickMarks(true);
        executionSpeedSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                switch ((int)Math.floor(n)) {
                    case 1:
                        return Strings.slow;
                    case MAX_SPEED_SLIDER:
                        return Strings.instant;
                    default:
                        return "";
                }
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
        executionSpeedSlider.setPadding(new Insets(0, 10, 0, 10));
        menuPane.add(executionSpeedSlider, 3, 0);

        pauseButton.setDisable(true);
        resetButton.setDisable(true);

        Label label = new Label(Strings.execution);

        VBox vbox = new VBox();
        vbox.getChildren().add(label);
        VBox.setVgrow(label, Priority.ALWAYS);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setStyle("-fx-padding: 5 0 0 0");

        menuPane.add(vbox, 0, 1, 5, 1);

        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menuSection");
        root.getChildren().add(menuPane);

        return root;
    }

    /**
     * Initializes menu help section.
     * @return Help section
     */
    private Pane buildHelpSection() {
        VBox root = new VBox();

        GridPane menuPane = new GridPane();
        menuPane.setGridLinesVisible(false);
        menuPane.setHgap(5);

        menuPane.add(infoButton = buildButton(
                Strings.algorithm_info_title, Strings.algorithm_info_title,
                "/com/simonecavazzoni/algraph/res/images/menu/ic_info_black_24dp_1x.png",
                event -> openInfo()), 0, 0);
        Label label = new Label(Strings.help);

        VBox vbox = new VBox();
        vbox.getChildren().add(label);
        VBox.setVgrow(label, Priority.ALWAYS);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setStyle("-fx-padding: 5 0 0 0");

        menuPane.add(vbox, 0, 1);

        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menuSection");
        root.getChildren().add(menuPane);

        return root;
    }

    /**
     * Initializes a menu button.
     * @return Menu button
     */
    private Button buildButton(String title, String tooltip, String imagePath, EventHandler<ActionEvent> onAction) {

        Button button = new Button(title);
        button.setContentDisplay(ContentDisplay.TOP);

        Image image = new Image(this.getClass().getResourceAsStream(imagePath),
                24.0, 24.0, true, true);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);
        button.setTooltip(new Tooltip(tooltip));
        button.setOnAction(onAction);

        return button;
    }

    /**
     * Shows graph generation dialog, generates a new graph and displays it
     */
    private void generateGraph() {
        try {
            DialogUtils.GraphGeneratorDialogResult result =
                    DialogUtils.showGraphGeneratorDialog();

            if (result == null) {
                return;
            }

            graphController.setGraph(GraphGenerator.generateGraph(
                    result.getNumNodes(),
                    result.getMinWeight(),
                    result.getMaxWeight(),
                    result.isDirected()
            ));

            resetExecution(null);

        } catch (Exception e) {
            DialogUtils.showErrorDialog(Strings.error, Strings.generate_graph, Strings.error_generate);
        }
    }

    /**
     * Shows file picker, loads a graph from file and displays it
     */
    private void loadGraphFromFile() {
        try {
            File file = DialogUtils.showFileChooserDialog(false);
            if (file == null) {
                return;
            }

            graphController.setGraph(FileHandler.loadGraph(file));
            WindowUtils.setWindowTitle(root, file.getName());

            resetExecution(null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Shows file picker and saves current graph to file.
     */
    private void saveGraphToFile() {
        try {
            if (graphController.getGraph().getAdjacencies().size() == 0) {
                DialogUtils.showErrorDialog(Strings.error, Strings.error_file_header, Strings.error_file_content);
                return;
            }

            File file = DialogUtils.showFileChooserDialog(true);
            if (file == null) {
                return;
            }

            FileHandler.saveGraph(graphController.getGraph(), file);
            WindowUtils.setWindowTitle(root, file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a step of the algorithm.
     */
    private void executeStep() {
        if (graphController.getGraph().getAdjacencies().size() == 0) {
            return;
        }

        if (!algorithmHandler.existRootNode()) {
            setRootNode();
        }

        algorithmHandler.executeStep();

        resetButton.setDisable(!algorithmHandler.isStarted());
    }

    /**
     * Start executing all the steps of the algorithm, based on the selected speed.
     * If the speed is set to max, executes all the steps instantly.
     */
    private void executeAll() {
        pendingExecution = false;

        if(graphController.getGraph().getAdjacencies().size() == 0){
            return;
        }

        if(!algorithmHandler.existRootNode()){
            setRootNode();
        }

        int speed = (int)Math.floor(executionSpeedSlider.getValue());
        if (speed == MAX_SPEED_SLIDER) {
            algorithmHandler.executeAll();
            resetButton.setDisable(false);
            pauseButton.setDisable(true);
            stepButton.setDisable(true);
            executeButton.setDisable(true);
            resetButton.setDisable(!algorithmHandler.isStarted());
            return;
        }

        stepButton.setDisable(true);
        executeButton.setDisable(true);
        pauseButton.setDisable(false);

        pendingExecution = true;

        int delay = MAX_EXECUTION_TIME / speed;
        AsyncUtils.setTimeout(() -> {
            if (executionPaused) {
                executionPaused = false;
                pendingExecution = false;

                stepButton.setDisable(false);
                executeButton.setDisable(false);
                return;
            }

            pendingExecution = true;
            algorithmHandler.executeStep();
        }, delay, new AsyncUtils.AsyncCallback() {
            @Override
            public void onComplete() {
                if (algorithmHandler.isFinished()) {
                    endExecutionUI();
                    pendingExecution = false;
                    return;
                }

                if (executionPaused) {
                    pauseExecutionUI();
                    executionPaused = false;
                    pendingExecution = false;
                    return;
                }

                continueExecutionUI();
                pendingExecution = true;

                executeAll();
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Sets UI in continue execution state
     */
    private void continueExecutionUI() {
        pauseButton.setDisable(false);
        resetButton.setDisable(false);
    }

    /**
     * Sets UI in end execution state
     */
    private void endExecutionUI() {
        resetButton.setDisable(false);
        pauseButton.setDisable(true);
        stepButton.setDisable(true);
        executeButton.setDisable(true);
    }

    /**
     * Resets the execution of the algorithm
     */
    private void resetExecution(Node startNode) {
        if (pendingExecution) {
            executionPaused = true;
        }

        algorithmHandler.restartAlgorithm(startNode);

        graphController.resetGraphUI();
        codeController.selectLine(-1);

        resetExecutionUI();
    }

    /**
     * Sets UI in reset execution state
     */
    private void resetExecutionUI() {
        resetButton.setDisable(true);
        pauseButton.setDisable(true);
        stepButton.setDisable(false);
        executeButton.setDisable(false);
    }

    /**
     * Sets flag to pause execution
     */
    private void pauseExecution() {
        pauseButton.setDisable(true);
        if (pendingExecution) {
            executionPaused = true;
        }
    }

    /**
     * Sets UI in pause execution state
     */
    private void pauseExecutionUI() {
        stepButton.setDisable(false);
        executeButton.setDisable(false);
    }

    /**
     * Shows select node dialog and sets the root node of the algorithm.
     */
    private void setRootNode(){
        try {
            String input = DialogUtils.showTextSpinnerDialog(
                    Strings.choose_root_title,
                    Strings.choose_root_header,
                    Strings.choose_root_content,
                    graphController.getGraph().getNodes().stream().map(Node::getLabel)
                            .sorted(String::compareTo).toArray(String[]::new)
            );

            Node node = graphController.getGraph().getNode(input);
            if (node != null) {
                resetExecution(node);
            } else {
                DialogUtils.showErrorDialog(Strings.error,
                        Strings.choose_root_error_header, Strings.choose_root_error_content);
                resetExecution(null);
            }
        } catch (Exception e) {
            //
        }
    }

    /**
     * Gets if graph can be modified
     * @return Graph is editable
     */
    public boolean isGraphEditable(){
        return !algorithmHandler.isStarted() || algorithmHandler.isFinished();
    }

    /**
     * Opens algorithm information window
     */
    private void openInfo() {
        infoButton.setDisable(true);
        WindowUtils.openNewWindow(Strings.pseudo_code_title, new AlgorithmInfoUI(), event ->
                infoButton.setDisable(false), "algorithm_info_style");
    }

    /**
     * Gets current extracted node of the algorithm.
     * @return Current extracted node
     */
    public Node getU(){
        return algorithmHandler.getU();
    }

    /**
     * Gets current analyzed node of the algorithm.
     * @return Current analyzed node
     */
    public Node getV(){
        return algorithmHandler.getV();
    }

    /**
     * Gets current distance from root to a node from the algorithm.
     * @param node Node to get distance of
     * @return Current distance
     */
    public Integer getResultDistance(Node node){
        return algorithmHandler.getResultDistance(node);
    }

    /**
     * Selects a node in the priority queue.
     * @param node Current node
     */
    public void selectQueueItem(Node node){
        this.priorityQueueController.selectItem(node);
    }

    /**
     * Gets current analyzed edge weight from the algorithm.
     * @return Edge weight
     */
    public Integer getWeight(){
        return this.algorithmHandler.getWeight();
    }

    /**
     * Selects an edge in the graph.
     * @param edge Current edge
     */
    public void selectEdge(Edge edge){
        if (edge == null) {
            return;
        }
        this.graphController.selectEdge(edge);
    }

    /**
     * Deselects an edge in the graph
     * @param edge Edge to deselect
     */
    public void deselectEdge(Edge edge){
        if (edge == null) {
            return;
        }
        this.graphController.deselectEdge(edge);
    }

    /**
     * Gets current analyzed edge of the algorithm.
     * @return Current analyzed edge
     */
    public Edge getE(){
        return this.algorithmHandler.getE();
    }

}
