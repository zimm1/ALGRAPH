package com.simonecavazzoni.algraph.controller;

import com.simonecavazzoni.algraph.model.Node;
import com.simonecavazzoni.algraph.res.Colors;
import com.simonecavazzoni.algraph.res.Strings;
import com.simonecavazzoni.algraph.ui.CodeUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class CodeController extends Controller {

    private Label uNode;
    private Label vNode;
    private HBox container;
    private TextFlow variableValue;

    private static final Pos DEFAULT_POSITION = Pos.TOP_CENTER;
    private static final String CSS_TITLE_STYLE = "titleStyle";
    private static final String CSS_QUEUE_LABEL = "queueLabel";
    private static final String CSS_TEXT_ALIGNMENT = "textAlignment";

    private static final int DEFAULT_ROOT_PREF_HEIGHT = 100;
    private static final int DEFAULT_ROOT_PREF_WIDTH = 400;

    private ArrayList<CodeUI> listCodeUI;
    private Label title;

    private MainController mainController;

    private int currentSelected = -1;


    public CodeController(MainController mainController) {

        this.mainController = mainController;

        this.setRoot(new VBox());
        listCodeUI = new ArrayList<>();
        uNode = new Label();
        uNode.getStyleClass().add(CSS_QUEUE_LABEL);
        uNode.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH / 2);
        uNode.setPadding(new Insets(10, 0, 10, 0));

        vNode = new Label();
        vNode.getStyleClass().add(CSS_QUEUE_LABEL);
        vNode.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH / 2);
        vNode.setPadding(new Insets(10, 0, 10, 0));

        title = new Label(Strings.pseudo_code_title);
        title.getStyleClass().add(CSS_TITLE_STYLE);
        title.setTextFill(Colors.PRIMARY_COLOR);
        setTitleDimension();

        container = new HBox(10);
        container.setAlignment(DEFAULT_POSITION);

        variableValue = new TextFlow();
        variableValue.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH / 2);
        variableValue.getStyleClass().addAll(CSS_TEXT_ALIGNMENT, CSS_QUEUE_LABEL);

        setRootStyle();

        initializeControllerUI();
    }

    public void selectLine(int numLine) {
        if (numLine >= -1 && numLine < listCodeUI.size()) {
            if (currentSelected != -1) {
                listCodeUI.get(currentSelected).setDefaultLabelStyle();
            }
            if (numLine != -1) {
                listCodeUI.get(numLine).setSelectedLabelStyle();
            }
            currentSelected = numLine;
        }

        updateVariableUI();

    }

    private void updateVariableUI() {
        container.getChildren().clear();
        variableValue.getChildren().clear();

        root.getChildren().remove(container);
        root.getChildren().remove(variableValue);

        uNode.setText(getString((currentSelected != -1) ? mainController.getU() : null, true));
        vNode.setText(getString((currentSelected != -1) ? mainController.getV() : null, false));

        container.getChildren().addAll(uNode, vNode);
        root.getChildren().add(container);

        if (currentSelected == 4) {
            mainController.selectQueueItem(mainController.getV());
            mainController.selectEdge(mainController.getE());
            initTextFlow();
            root.getChildren().add(variableValue);
        } else {
            mainController.selectQueueItem(null);
            mainController.deselctEdge(mainController.getE());
        }
    }

    private void setRootStyle() {
        ((VBox) this.root).setAlignment(DEFAULT_POSITION);
        setRootDimension();
    }

    private void setRootDimension() {
        root.setPrefHeight(DEFAULT_ROOT_PREF_HEIGHT);
        root.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH);
    }

    private void setTitleDimension() {
        title.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH);
        title.setPrefHeight(DEFAULT_ROOT_PREF_HEIGHT);
    }

    private void setListCodeUI() {
        listCodeUI.add(new CodeUI(Strings.pseudo_code_add_root));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_while));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_pop_item));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_foreach));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_condition));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_exist_item));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_insert_item));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_update_priority));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_update_distance));
        listCodeUI.add(new CodeUI(Strings.pseudo_code_update_tree));
    }

    private void initializeControllerUI() {
        root.getChildren().clear();

        setListCodeUI();

        root.getChildren().add(title);
        root.getChildren().addAll(listCodeUI);
        root.getChildren().add(uNode);
    }

    private String getString(Node node, boolean isV) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((isV) ? 'v' : 'u');
        stringBuilder.append('\u2190');
        if (node == null) {
            stringBuilder.append('\u2205');
        } else {
            stringBuilder.append(node.toString());
        }
        return stringBuilder.toString();
    }

    private void initTextFlow() {
        Integer vValue = mainController.getResultDistance(mainController.getV());
        Integer uValue = mainController.getResultDistance(mainController.getU());
        Integer weight = mainController.getWeight();

        Text text = new Text("if ");
        Text text1 = new Text((uValue != Integer.MAX_VALUE) ? Integer.toString(uValue) : "\u221e");
        Text text2 = new Text(" + ");
        Text text3 = new Text(Integer.toString(weight));
        Text text4 = new Text(" < ");
        Text text5 = new Text((vValue != Integer.MAX_VALUE) ? Integer.toString(vValue) : "\u221e");
        Text text6 = new Text(" then");

        text5.setFill(Colors.OTHER_COLOR_1);
        text1.setFill(Colors.OTHER_COLOR_2);
        text3.setFill(Colors.OTHER_COLOR_3);

        variableValue.getChildren().addAll(text, text1, text2, text3, text4, text5,text6);
    }
}