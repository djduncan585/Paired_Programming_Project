import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.MainStageContainer;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainStageContainer.setStage(primaryStage, "Home");
        primaryStage.setTitle("This is my App");
        Scene scene = new Scene(new HomeView());
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
