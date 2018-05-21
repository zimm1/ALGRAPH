package com.simonecavazzoni.algraph;

import com.simonecavazzoni.algraph.controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.simonecavazzoni.algraph.res.Strings;
import com.simonecavazzoni.algraph.utils.CssUtils;
import com.simonecavazzoni.algraph.utils.WindowUtils;


public class Main extends Application {

    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 768;

    /**
     * Adds MainController, font, css and icon to the stage and shows it.
     * @see Application
     * @param primaryStage Main stage to show
     */
    @Override
    public void start(Stage primaryStage) {
        WindowUtils.setWindowTitle(primaryStage, Strings.untitled);

        Scene mainScene = new Scene(new MainController().get(), 1, 1);
        try {
            CssUtils.loadFont("product_sans");
            CssUtils.loadCss(mainScene, "main_style");
            CssUtils.loadCss(mainScene, "menu_style");
            CssUtils.loadCss(mainScene, "priority_queue_style");
            CssUtils.loadCss(mainScene, "code_style");
        } catch (NullPointerException e) {
            //
        }

        primaryStage.getIcons().add(new Image("/com/simonecavazzoni/algraph/res/images/program_icon.png"));
        primaryStage.setScene(mainScene);
        primaryStage.setMinWidth(DEFAULT_WIDTH);
        primaryStage.setMinHeight(DEFAULT_HEIGHT);
        primaryStage.setWidth(DEFAULT_WIDTH);
        primaryStage.setWidth(DEFAULT_HEIGHT);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
