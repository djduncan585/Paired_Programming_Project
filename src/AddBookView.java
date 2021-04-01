
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import userinterface.MainStageContainer;

public class AddBookView extends ViewBase {


    protected void init() {
        VBox container = createStandardContainer(400, 400);

        addLabel("Book title", container);
        TextField bookName = new TextField();
        bookName.setPrefWidth(300);
        container.getChildren().add(bookName);

        addLabel("Author", container);
        TextField author = new TextField();
        bookName.setPrefWidth(300);
        container.getChildren().add(author);

        addLabel("Year Published", container);
        TextField pubYear = new TextField();
        bookName.setPrefWidth(300);
        container.getChildren().add(pubYear);


        container.getChildren().add(createButton("Save", event -> {
            // Use manager class to save

            bookCollection.addBook(
                    author.getText(),
                    bookName.getText(),
                    pubYear.getText()
            );

            showHome();
        }));
    }

}
