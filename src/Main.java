import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.Strings;
import utils.CssUtils;

import java.io.File;
import java.net.URISyntaxException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Strings.program_title);

        Scene mainScene = new Scene(new MainController().get(), 1024, 768);
        try {
            mainScene.getStylesheets().add(CssUtils.getCssFilePaths(this, "menu"));
        } catch (NullPointerException e) {
            //
        }

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
