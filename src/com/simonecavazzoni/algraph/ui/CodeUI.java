package com.simonecavazzoni.algraph.ui;

import com.simonecavazzoni.algraph.res.Colors;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

/**
 * This class extends Group and creates the graphics for the line code to show.
 * This class expose the methods to change the style of its contents.
 */
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

    /**
     * set the selected label style
     */
    public void setSelectedLabelStyle(){
        this.lineCode.getStyleClass().remove(cssUnselectedItem);
        this.lineCode.getStyleClass().add(cssSelectedItem);
        this.lineCode.setTextFill(Colors.LIGHT_TEXT_COLOR);
        this.lineCode.setBackground(new Background(new BackgroundFill(Colors.PRIMARY_COLOR, null, null)));
    }

    /**
     * set the default label style
     */
    public void setDefaultLabelStyle(){
        this.lineCode.getStyleClass().remove(cssSelectedItem);
        this.lineCode.getStyleClass().add(cssUnselectedItem);
        this.lineCode.setTextFill(Colors.DEFAULT_COLOR);
        this.lineCode.setBackground(new Background(new BackgroundFill(null, null, null)));
    }
}
