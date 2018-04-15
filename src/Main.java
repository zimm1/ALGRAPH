import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Strings;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("layout/GraphLayout.fxml"));
        primaryStage.setTitle(Strings.program_title);
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
