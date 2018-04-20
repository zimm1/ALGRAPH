package controller;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import resources.Strings;
import ui.CodeUI;

import java.util.ArrayList;

public class CodeController implements ControllerInterface{

    private VBox root;

    private static final int DEFAULT_ROOT_MAX_HEIGHT = 300;
    private static final int DEFAULT_ROOT_MAX_WIDTH = 400;
    private static final int DEFAULT_ROOT_MIN_HEIGHT = 300;
    private static final int DEFAULT_ROOT_MIN_WIDTH = 400;

    private ArrayList<CodeUI> listCodeUI;

    private int currentSelected = -1;

    public CodeController(){

        listCodeUI = new ArrayList<>();
        root = new VBox();

        setRootDimension();

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

    private void setRootDimension(){
        root.setMaxHeight(DEFAULT_ROOT_MAX_HEIGHT);
        root.setMinHeight(DEFAULT_ROOT_MIN_HEIGHT);
        root.setMaxWidth(DEFAULT_ROOT_MAX_WIDTH);
        root.setMinWidth(DEFAULT_ROOT_MIN_WIDTH);
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

        root.getChildren().addAll(listCodeUI);
    }
}
