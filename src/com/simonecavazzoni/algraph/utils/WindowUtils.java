package com.simonecavazzoni.algraph.utils;

import com.simonecavazzoni.algraph.res.Strings;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class WindowUtils {

    public static void setWindowTitle(Node node, String title) {
        setWindowTitle((Stage) node.getScene().getWindow(), title);
    }

    public static void setWindowTitle(Stage stage, String title) {
        stage.setTitle(title + " - " + Strings.program_title);
    }

    public static void openNewWindow(String title, Parent root, EventHandler<WindowEvent> onCloseCallback) {
        openNewWindow(title, root, onCloseCallback, new String[]{});
    }

    public static void openNewWindow(String title, Parent root, EventHandler<WindowEvent> onCloseCallback, String... cssFiles) {
        Stage stage = new Stage();

        Scene mainScene = new Scene(root);

        if (cssFiles != null) {
            for (String file : cssFiles) {
                CssUtils.loadCss(mainScene, file);
            }
        }

        stage.getIcons().add(new Image("/com/simonecavazzoni/algraph/res/images/program_icon.png"));
        stage.setScene(mainScene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(onCloseCallback);
    }
}
