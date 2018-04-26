package com.simonecavazzoni.algraph.ui;

import com.simonecavazzoni.algraph.resources.Strings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AlgorithmInfoUI extends Group {

    private static final int width = 500;
    private static final int height = 550;
    private static final String CSS_ALGORITHM_INFO_TITLE = "algorithmInfoTitle";
    private static final String CSS_ALGORITHM_INFO_MAIN_TITLE = "algorithmInfoMainTitle";
    private static final String CSS_ALGORITHM_INFO_DESCRIPTION = "algorithmInfoDescription";
    private static final String ALGORITHM_INFO_TEXT_LARGE = "algorithmInfoTextLarge";

    private static final String pathImage = "/com/simonecavazzoni/algraph/resources/images/Dijkstra_Animation.gif";

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

        ImageView imageView = new ImageView(pathImage);

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

    private Label createLabel(String message, boolean isMainTitle){
        Label label = new Label(message);
        label.getStyleClass().add(CSS_ALGORITHM_INFO_TITLE);
        label.setId(isMainTitle ? CSS_ALGORITHM_INFO_MAIN_TITLE : "");
        return label;
    }

    private Label createLabelDescription(String message){
        Label label = new Label(message);
        label.getStyleClass().add(CSS_ALGORITHM_INFO_DESCRIPTION);
        return label;
    }
}
