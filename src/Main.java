import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.Strings;
import utils.CssUtils;
import utils.WindowUtils;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        WindowUtils.setWindowTitle(primaryStage, Strings.untitled);

        Scene mainScene = new Scene(new MainController().get(), 1024, 768);
        try {
            CssUtils.loadFont("Product Sans");
            CssUtils.loadCss(mainScene, "mainStyle");
            CssUtils.loadCss(mainScene, "menuStyle");
            CssUtils.loadCss(mainScene, "priorityQueueStyle");
            CssUtils.loadCss(mainScene, "labelStyleSheet");
            CssUtils.loadCss(mainScene,"AlgorithmInfoStyle");
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
