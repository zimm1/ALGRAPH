package controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.Strings;
import ui.CodeUI;

import java.util.ArrayList;

public class CodeController implements ControllerInterface{

    private VBox root;

    private static final Pos DEFAULT_ROOT_POSITION = Pos.TOP_CENTER;

    private static final String cssTitleStyle = "titleStyle";

    private static final int DEFAULT_ROOT_PREF_HEIGHT = 100;
    private static final int DEFAULT_ROOT_PREF_WIDTH = 400;

    private ArrayList<CodeUI> listCodeUI;
    private Label title;

    private int currentSelected = -1;

    public CodeController(){

        listCodeUI = new ArrayList<>();
        root = new VBox();

        title = new Label(Strings.pseudoCodeTitle);
        title.getStyleClass().add(cssTitleStyle);
        setTitleDimension();

        setRootStyle();

        initializeControllerUI();
    }

    @Override
    public Pane get() {
        return root;
    }

    public void selectLine(int numLine){
        if(numLine >= -1 && numLine < listCodeUI.size()){
            if (currentSelected != -1) {
                listCodeUI.get(currentSelected).setDefaultLabelStyle();
            }
            if (numLine != -1) {
                listCodeUI.get(numLine).setSelectedLabelStyle();
            }
            currentSelected = numLine;
        }
    }

    private void setRootStyle(){
        this.root.setAlignment(DEFAULT_ROOT_POSITION);
        setRootDimension();
    }

    private void setRootDimension(){
        root.setPrefHeight(DEFAULT_ROOT_PREF_HEIGHT);
        root.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH);
    }

    private void setTitleDimension(){
        title.setPrefWidth(DEFAULT_ROOT_PREF_WIDTH);
        title.setPrefHeight(DEFAULT_ROOT_PREF_HEIGHT);
    }

    private void setListCodeUI(){
        listCodeUI.add(new CodeUI(Strings.pseudoCodeAddRootInPriorityQueue));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeWhile));
        listCodeUI.add(new CodeUI(Strings.pseudoCodePopItem));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeForeach));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeCondition));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeExistItemInPriorityQueueCondition));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeInsertItemInQueue));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeUpdatePriority));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeUpdateDistanceArray));
        listCodeUI.add(new CodeUI(Strings.pseudoCodeUpdateTree));
    }

    private void initializeControllerUI(){
        root.getChildren().clear();

        setListCodeUI();

        root.getChildren().add(title);
        root.getChildren().addAll(listCodeUI);
    }
}
