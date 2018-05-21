package com.simonecavazzoni.algraph.utils;

import com.simonecavazzoni.algraph.res.Strings;
import com.sun.istack.internal.Nullable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Set of window and stage related utilities
 */
public abstract class WindowUtils {

    /**
     * Sets stage title from node
     * @param node Node of the stage
     * @param title New title
     */
    public static void setWindowTitle(Node node, String title) {
        setWindowTitle((Stage) node.getScene().getWindow(), title);
    }

    /**
     * Sets stage title adding the program title
     * @param stage Stage to change title of
     * @param title New title
     */
    public static void setWindowTitle(Stage stage, String title) {
        stage.setTitle(title + " - " + Strings.program_title);
    }

    /**
     * Creates and shows new window
     * @param title Title of the window
     * @param root Root to set as the main scene
     * @param onCloseCallback Optional callback called when window closes
     * @param cssFiles Optional css files to add to the scene
     */
    public static void openNewWindow(String title, Parent root, @Nullable EventHandler<WindowEvent> onCloseCallback,
                                     @Nullable String... cssFiles) {
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
