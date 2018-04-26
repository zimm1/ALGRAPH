package com.simonecavazzoni.algraph.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import com.simonecavazzoni.algraph.resources.Strings;
import com.simonecavazzoni.algraph.ui.CodeUI;

import java.util.ArrayList;

public class CodeController extends Controller {

    private static final Pos DEFAULT_ROOT_POSITION = Pos.TOP_CENTER;

    private static final String cssTitleStyle = "titleStyle";

    private static final int DEFAULT_ROOT_PREF_HEIGHT = 100;
    private static final int DEFAULT_ROOT_PREF_WIDTH = 400;

    private ArrayList<CodeUI> listCodeUI;
    private Label title;

    private int currentSelected = -1;

    public CodeController(){
        this.setRoot(new VBox());

        listCodeUI = new ArrayList<>();

        title = new Label(Strings.pseudo_code_title);
        title.getStyleClass().add(cssTitleStyle);
        setTitleDimension();

        setRootStyle();

        initializeControllerUI();
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
        ((VBox)this.root).setAlignment(DEFAULT_ROOT_POSITION);
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

    private void initializeControllerUI(){
        root.getChildren().clear();

        setListCodeUI();

        root.getChildren().add(title);
        root.getChildren().addAll(listCodeUI);
    }
}
