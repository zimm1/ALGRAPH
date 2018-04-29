package ui;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import resources.Strings;

public class AlgorithmInfoUI extends Group {

    private static final int width = 500;
    private static final int height = 550;
    private static final String cssAlgorithmInfoTitle = "algorithmInfoTitle";
    private static final String cssAlgorithmInfoMainTitle = "algorithmInfoMainTitle";
    private static final String cssAlgorithmInfoDescription = "algorithmInfoDescription";
    private static final String cssAlgorithmInfoLargeText = "algorithmInfoTextLarge";

    private static final String pathImage = "/resources/images/Dijkstra_Animation.gif";

    private VBox root;

    public AlgorithmInfoUI(){
        root = new VBox();
        root.setPrefHeight(height);
        root.setPrefWidth(width);

        HBox hBox = new HBox(20);

        Label title = createLabel(Strings.algorithmInfoTitle,true);

        Label description = createLabelDescription(Strings.algorithmInfoDescription);

        ImageView imageView = new ImageView(pathImage);

        VBox container = new VBox();

        Label hypothesisTitle = createLabel(Strings.algorithmInfoHypothesisTitle,false);

        String[] hypothesis = {Strings.algorithmInfoHypothesis,Strings.algorithmInfoHypothesis1,Strings.algorithmInfoHypothesis2};
        StringBuilder stringBuilder = new StringBuilder();
        for(String item:hypothesis){
            stringBuilder.append("\u2022");
            stringBuilder.append(item);
            stringBuilder.append('\n');
        }

        Label textHypothesis = createLabelDescription(stringBuilder.toString());
        textHypothesis.getStyleClass().add(cssAlgorithmInfoLargeText);

        Label dataStructureTitle = createLabel(Strings.algorithmInfoDataStructureTitle,false);

        Label textDataStructure = createLabelDescription(Strings.algorithmInfoDataStructureDescription);
        textDataStructure.getStyleClass().add(cssAlgorithmInfoLargeText);

        container.getChildren().addAll(hypothesisTitle,textHypothesis,dataStructureTitle,textDataStructure);

        hBox.getChildren().addAll(imageView, description);

        root.getChildren().addAll(title, hBox, container);

        this.getChildren().add(root);
    }

    private Label createLabel(String message,boolean isMainTitle){
        Label label = new Label(message);
        label.getStyleClass().add(cssAlgorithmInfoTitle);
        label.setId((isMainTitle) ? cssAlgorithmInfoMainTitle : "");
        label.setPrefWidth(root.getPrefWidth());
        label.setPrefHeight(root.getPrefHeight());
        return label;
    }

    private Label createLabelDescription(String message){
        Label label = new Label(message);
        label.getStyleClass().add(cssAlgorithmInfoDescription);
        label.setPrefWidth(root.getPrefHeight());
        label.setPrefHeight(root.getPrefHeight());
        return label;
    }

}
