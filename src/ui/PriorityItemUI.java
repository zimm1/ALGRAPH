package ui;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Node;
import model.PriorityItem;

public class PriorityItemUI<T> extends Group {

    private PriorityItem<T> priorityItem;

    public PriorityItemUI(PriorityItem<T> priorityItem){
        super();

        this.priorityItem = priorityItem;

        Rectangle rectangle = new Rectangle(20,50, Color.RED);
        Node node = (Node) priorityItem.getItem();
        Text text = new Text(node.getLabel());

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(rectangle,text);
        stackPane.setLayoutX(50);
        stackPane.setLayoutY(50);

        this.getChildren().addAll(rectangle,text,stackPane);

    }

    public PriorityItem<T> getPriorityItem() {
        return priorityItem;
    }


}
