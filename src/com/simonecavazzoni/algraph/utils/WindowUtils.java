package com.simonecavazzoni.algraph.utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.simonecavazzoni.algraph.resources.Strings;
import javafx.stage.WindowEvent;

public abstract class WindowUtils {

    public static void setWindowTitle(Node node, String title) {
        setWindowTitle((Stage)node.getScene().getWindow(), title);
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
                CssUtils.loadCss(mainScene,file);
            }
        }

        stage.setScene(mainScene);
        stage.setTitle(title);
        stage.show();

        stage.setOnCloseRequest(onCloseCallback);
    }
}
