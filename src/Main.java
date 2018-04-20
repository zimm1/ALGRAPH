import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.Strings;
import utils.CssUtils;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Strings.program_title);

        Scene mainScene = new Scene(new MainController().get(), 1024, 768);
        try {
            mainScene.getStylesheets().add(CssUtils.getCssFilePaths(this, "menu"));
            mainScene.getStylesheets().add(CssUtils.getCssFilePaths(this,"priorityQueueStyle"));
            mainScene.getStylesheets().add(CssUtils.getCssFilePaths(this,"labelStyleSheet"));
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
