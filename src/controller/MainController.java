package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import model.Node;

import resources.Strings;
import service.AlgorithmHandler;
import service.FileHandler;
import service.GraphGenerator;
import utils.DialogUtils;
import utils.WindowUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;


public class MainController implements ControllerInterface {
    private BorderPane root;

    private GraphController graphController;
    private PriorityQueueController priorityQueueController;
    private CodeController codeController;

    private AlgorithmHandler algorithmHandler;

    public MainController() {
        root = new BorderPane();

        root.setTop(initMenu());
        root.setCenter(initGraph());
        root.setRight(initCode());
        root.setBottom(initPriorityQueue());

        algorithmHandler = new AlgorithmHandler(graphController, priorityQueueController, codeController);

        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> graphController.onKeyPressed(event));
    }

    @Override
    public Pane get() {
        return root;
    }

    private Pane initGraph() {
        graphController = new GraphController(this);

        return graphController.get();
    }

    private Pane initCode() {
        codeController = new CodeController();

        return codeController.get();
    }

    private Pane initPriorityQueue() {
        priorityQueueController = new PriorityQueueController();

        return priorityQueueController.get();
    }

    private Pane initMenu() {
        HBox menuContainer = new HBox();

        menuContainer.setId("menu");
        menuContainer.setPrefHeight(90);
        menuContainer.setSpacing(5);

        menuContainer.getChildren().addAll(buildGraphSection(), buildExecuteSection());
        return menuContainer;
    }

    private Pane buildGraphSection() {
        VBox root = new VBox();

        GridPane menuPane = new GridPane();
        menuPane.setGridLinesVisible(false);
        menuPane.setHgap(5);

        menuPane.add(buildButton(
                Strings.generate, Strings.generate_graph,
                "/resources/images/ic_note_add_black_24dp_1x.png",
                event -> generateGraph()), 0, 0);
        menuPane.add(buildButton(
                Strings.open, Strings.open_file,
                "/resources/images/ic_folder_open_black_24dp_1x.png",
                event -> loadGraphFromFile()), 1, 0);
        menuPane.add(buildButton(
                Strings.save, Strings.save_file,
                "/resources/images/ic_save_black_24dp_1x.png",
                event -> saveGraphToFile()), 2, 0);

        Label label = new Label(Strings.graph);
        // TODO
        //label.getStyleClass().add("ribbonLabel");

        VBox vbox = new VBox();
        vbox.getChildren().add(label);
        VBox.setVgrow(label, Priority.ALWAYS);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setStyle("-fx-padding: 5 0 0 0");
        // TODO
        //vbox.getStyleClass().add("menuSection");
        menuPane.add(vbox, 0, 1, 3, 1);

        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menuSection");
        root.getChildren().add(menuPane);

        return root;
    }

    private Pane buildExecuteSection() {
        VBox root = new VBox();

        GridPane menuPane = new GridPane();
        menuPane.setGridLinesVisible(false);
        menuPane.setHgap(5);

        menuPane.add(buildButton(
                Strings.step, Strings.execute_step,
                "/resources/images/ic_skip_next_black_24dp_1x.png",
                event -> executeStep()), 0, 0);
        menuPane.add(buildButton(
                Strings.execute_all, Strings.execute_all,
                "/resources/images/ic_play_arrow_black_24dp_1x.png",
                event -> executeAll()), 1, 0);
        menuPane.add(buildButton(
                Strings.execute_reset, Strings.execute_reset,
                "/resources/images/ic_replay_black_24dp_1x.png",
                event -> resetExecution()), 2, 0);

        Label label = new Label(Strings.execution);
        //TODO
        //label.getStyleClass().add("ribbonLabel");

        //TODO: find a better way to center a label.
        VBox vbox = new VBox();
        vbox.getChildren().add(label);
        VBox.setVgrow(label, Priority.ALWAYS);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setStyle("-fx-padding: 5 0 0 0");
        // TODO
        //vbox.getStyleClass().add("menuSection");
        menuPane.add(vbox, 0, 1, 3, 1);

        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("menuSection");
        root.getChildren().add(menuPane);

        return root;
    }

    private Button buildButton(String title, String tooltip, String imagePath, EventHandler<ActionEvent> onAction) {

        //Create button with text.
        Button button = new Button(title);

        //Set the Image above the text.
        button.setContentDisplay(ContentDisplay.TOP);

        //Add image.
        Image image = new Image(this.getClass().getResourceAsStream(imagePath),
                24.0, 24.0, true, true);
        ImageView imageView = new ImageView(image);
        button.setGraphic(imageView);

        //Set CSS Styles.
        // TODO
        //button.getStyleClass().add("ribbonToggleButton");

        //Set Tooltip
        button.setTooltip(new Tooltip(tooltip));

        //Set simple Click Event Handler.
        button.setOnAction(onAction);

        return button;
    }

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

            resetExecution();

        } catch (Exception e) {
            DialogUtils.showErrorDialog(Strings.error, Strings.generate_graph, Strings.error_generate);
        }
    }

    private void loadGraphFromFile() {
        try {
            File file = DialogUtils.showFileChooserDialog(false);
            if (file == null) {
                return;
            }

            graphController.setGraph(FileHandler.loadGraph(file));
            WindowUtils.setWindowTitle(root, file.getName());

            resetExecution();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveGraphToFile() {
        try {
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

    private void executeStep() {
        if(graphController.getGraph().getAdjacencies().size() == 0){
            return;
        }

        if(!algorithmHandler.existRootNode()){
            selectRootNode();
        }
        algorithmHandler.executeStep();
        /*if(algorithmHandler.existRootNode()){
            algorithmHandler.executeStep();
        } else {
            selectRootNode();
        }*/
    }

    private void executeAll() {
        if(graphController.getGraph().getAdjacencies().size() == 0){
            return;
        }

        if(!algorithmHandler.existRootNode()){
            selectRootNode();
        }
        algorithmHandler.executeAll();
        /*if(algorithmHandler.existRootNode()){
            algorithmHandler.executeAll();
        } else {
            selectRootNode();
        }*/
    }

    private void resetExecution() {
        resetExecution(null);
    }

    private void resetExecution(Node startNode) {
        if(startNode != null){
            algorithmHandler.restartAlgorithm(startNode);
        } else {
            algorithmHandler.restartAlgorithm();
        }
        graphController.resetGraphUI();
        codeController.selectLine(-1);
    }

    private Node getStartNode(){
        String input = null;
        try {
            input = DialogUtils.showTextSpinnerDialog(Strings.chooseRootTitle,Strings.chooseRootHeader,Strings.chooseRootContent,
                    graphController.getGraph().getNodes().stream().map(Node::getLabel).collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (input != null) ? graphController.getGraph().getNode(input) : null;
    }

    private void selectRootNode(){
        Node node = getStartNode();
        if(graphController.getGraph().getNode(node) != null){
            resetExecution(node);
        } else {
            DialogUtils.showErrorDialog(Strings.chooseRootErrorTitle,Strings.chooseRootErrorHeader,Strings.chooseRootErrorContent);
            resetExecution();
        }
    }

    public boolean isAlgoritmHandlerStarted(){
        return algorithmHandler.isStarted();
    }

}
