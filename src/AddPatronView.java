
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import userinterface.MainStageContainer;

public class AddPatronView extends ViewBase {


    protected void init() {
        VBox container = createStandardContainer(400, 400);

        addLabel("Patron Name", container);
        TextField patronName = new TextField();
        patronName.setPrefWidth(300);
        container.getChildren().add(patronName);

        addLabel("Address", container);
        TextField address = new TextField();
        address.setPrefWidth(300);
        container.getChildren().add(address);

        addLabel("City", container);
        TextField city = new TextField();
        city.setPrefWidth(300);
        container.getChildren().add(city);

        addLabel("State Code", container);
        TextField stateCode = new TextField();
        stateCode.setPrefWidth(300);
        container.getChildren().add(stateCode);

        addLabel("Zip", container);
        TextField zip = new TextField();
        zip.setPrefWidth(300);
        container.getChildren().add(zip);

        addLabel("Email", container);
        TextField email = new TextField();
        email.setPrefWidth(300);
        container.getChildren().add(email);

        addLabel("Date of Birth", container);
        TextField dateOfBirth = new TextField();
        dateOfBirth.setPrefWidth(300);
        container.getChildren().add(dateOfBirth);



        container.getChildren().add(createButton("Save", event -> {
            // Use manager class to save

            patronCollection.addPatron(
                    address.getText(),
                    city.getText(),
                    dateOfBirth.getText(),
                    email.getText(),
                    patronName.getText(),
                    stateCode.getText(),
                    zip.getText()


            );

            showHome();
        }));
    }

}
