import javafx.scene.layout.VBox;

public class HomeView extends ViewBase {
    @Override
    protected void init() {
        VBox container = createStandardContainer(400, 400);
        container.getChildren().add(
                createButton("Add Book", event -> {
                    showView(new AddBookView());
                })
        );
        container.getChildren().add(
                createButton("Add Patron", event -> {
                    showView(new AddPatronView());
                })
        );
    }
}
