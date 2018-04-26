package com.simonecavazzoni.algraph.ui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class CodeUI extends Group {

    private Label lineCode;
    private static final int DEFAULT_MAX_HEIGHT = 30;
    private static final int DEFAULT_MIN_HEIGHT = 30;
    private static final int DEFAULT_MAX_WIDTH = 400;
    private static final int DEFAULT_MIN_WIDTH = 400;

    private static final Pos DEFAULT_LABEL_POSITION = Pos.CENTER_LEFT;
    private static final String cssSelectedItem = "selectedItem";
    private static final String cssUnselectedItem = "unselectedItem";

    public CodeUI(String codeLine){
        this.lineCode = new Label(codeLine);

        lineCode.setAlignment(DEFAULT_LABEL_POSITION);
        setDefaultLabelStyle();

        lineCode.setMaxWidth(DEFAULT_MAX_WIDTH);
        lineCode.setMinWidth(DEFAULT_MIN_WIDTH);

        lineCode.setMaxHeight(DEFAULT_MAX_HEIGHT);
        lineCode.setMinHeight(DEFAULT_MIN_HEIGHT);

        this.getChildren().add(lineCode);

    }

    public void setSelectedLabelStyle(){
        this.lineCode.getStyleClass().remove(cssUnselectedItem);
        this.lineCode.getStyleClass().add(cssSelectedItem);
    }

    public void setDefaultLabelStyle(){
        this.lineCode.getStyleClass().remove(cssSelectedItem);
        this.lineCode.getStyleClass().add(cssUnselectedItem);
    }
}
