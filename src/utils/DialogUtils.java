package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public abstract class DialogUtils {
    public static String showTextInputDialog(String title, String header, String content, String defaultValue)
            throws Exception {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }

        throw new Exception();
    }

    public static void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
