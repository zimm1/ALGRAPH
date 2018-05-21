package com.simonecavazzoni.algraph.ui;

import com.simonecavazzoni.algraph.res.Colors;
import com.simonecavazzoni.algraph.res.Strings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class contains information about Dijkstra algorithm, it explain the data structure
 * used by Dijkstra and the hypothesis that the algorithm require.
 */
public class AlgorithmInfoUI extends Group {

    private static final String CSS_ALGORITHM_INFO_TITLE = "algorithmInfoTitle";
    private static final String CSS_ALGORITHM_INFO_MAIN_TITLE = "algorithmInfoMainTitle";
    private static final String CSS_ALGORITHM_INFO_DESCRIPTION = "algorithmInfoDescription";
    private static final String ALGORITHM_INFO_TEXT_LARGE = "algorithmInfoTextLarge";

    private static final String IMAGE_PATH = "/com/simonecavazzoni/algraph/res/images/dijkstra_animation.gif";

    private VBox root;

    public AlgorithmInfoUI(){
        root = new VBox();
        //root.setPrefHeight(height);
        //root.setPrefWidth(width);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(20);

        Label title = createLabel(Strings.algorithm_info_title,true);

        Label description = createLabelDescription(Strings.algorithm_info_description);
        description.setWrapText(true);
        description.setPrefWidth(300);

        ImageView imageView = new ImageView(IMAGE_PATH);

        Label hypothesisTitle = createLabel(Strings.algorithm_info_hypothesis_title,false);

        String[] hypothesis = {Strings.algorithm_info_hypothesis,Strings.algorithm_info_hypothesis_1,Strings.algorithm_info_hypothesis_2};
        StringBuilder stringBuilder = new StringBuilder();
        for(String item:hypothesis){
            stringBuilder.append("\u2022");
            stringBuilder.append(item);
            stringBuilder.append('\n');
        }

        Label textHypothesis = createLabelDescription(stringBuilder.toString());
        textHypothesis.getStyleClass().add(ALGORITHM_INFO_TEXT_LARGE);

        Label dataStructureTitle = createLabel(Strings.algorithm_info_data_structure_title,false);

        Label textDataStructure = createLabelDescription(Strings.algorithm_info_fata_structure_description);
        textDataStructure.getStyleClass().add(ALGORITHM_INFO_TEXT_LARGE);

        hBox.getChildren().addAll(imageView, description);
        root.getChildren().addAll(title, hBox, hypothesisTitle,textHypothesis,dataStructureTitle,textDataStructure);

        this.getChildren().add(root);
    }

    /**
     * @param message   This is the message to set to the label
     * @param isMainTitle   Is this message the main title?
     * @return  Label   This returns the label
     */
    private Label createLabel(String message, boolean isMainTitle){
        Label label = new Label(message);
        label.getStyleClass().add(CSS_ALGORITHM_INFO_TITLE);
        label.setTextFill(Colors.PRIMARY_COLOR);
        label.setId(isMainTitle ? CSS_ALGORITHM_INFO_MAIN_TITLE : "");
        return label;
    }

    /**
     * @param message   This is the message to set to the label
     * @return  Label   This returns the label
     */
    private Label createLabelDescription(String message){
        Label label = new Label(message);
        label.getStyleClass().add(CSS_ALGORITHM_INFO_DESCRIPTION);
        label.setTextFill(Colors.DEFAULT_COLOR);
        return label;
    }
}
