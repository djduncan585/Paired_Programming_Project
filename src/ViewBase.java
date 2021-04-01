

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.BookCollection;
import model.PatronCollection;
import userinterface.MainStageContainer;
import userinterface.View;

import java.util.function.Consumer;

public abstract class ViewBase extends View {

    protected BookCollection bookCollection = new BookCollection();
    protected PatronCollection patronCollection = new PatronCollection();

    public ViewBase() {
        super(null, null);
        init();
    }

    protected abstract void init();

    protected Button createButton(String label, Consumer<ActionEvent> handler) {
        Button button = new Button(label);
        button.setOnAction(event -> handler.accept(event));
        return button;
    }

    protected VBox createStandardContainer(double width, double height) {
        VBox container = new VBox(10);
        container.setPadding(new Insets(15, 5, 5, 5));
        container.setPrefSize(width, height);
        getChildren().add(container);
        return container;
    }

    protected void showView(ViewBase theNewView) {
        Scene newScene = new Scene(theNewView);
        MainStageContainer.getInstance().setScene(newScene);
        MainStageContainer.getInstance().sizeToScene();
    }

    protected void showHome() {
        showView(new HomeView());
    }

    protected void addLabel(String text, VBox container) {
        Text label = new Text(text);
        container.getChildren().add(label);
    }


    @Override
    public void updateState(String key, Object value) {
    }
}
