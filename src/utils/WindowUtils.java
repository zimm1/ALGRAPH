package utils;

import javafx.scene.Node;
import javafx.stage.Stage;
import resources.Strings;

public abstract class WindowUtils {

    public static void setWindowTitle(Node node, String title) {
        setWindowTitle((Stage)node.getScene().getWindow(), title);
    }

    public static void setWindowTitle(Stage stage, String title) {
        stage.setTitle(title + " - " + Strings.program_title);
    }
}
