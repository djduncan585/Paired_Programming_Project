
// specify the package
package userinterface;

// system imports

import impresario.IModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Properties;

/** The class containing the Teller View  for the ATM application */
//==============================================================
public class LibrarianView extends View
{

	// GUI stuff
	private Button addBookButton;
	private Button addPatronButton;
	private Button searchBooksButton;
	private Button searchPatronsButton;
	private Button exitButton;

	// constructor for this class -- takes a model object
	//----------------------------------------------------------
	public LibrarianView(IModel librarian)
	{

		super(librarian, "LibrarianView");

		// create a container for showing the contents
		VBox container = new VBox(10);

		container.setPadding(new Insets(25, 25, 25, 25));

		// create a Node (Text) for showing the title
		container.getChildren().add(createTitle());

		// create a Node (GridPane) for showing data entry fields
		//container.getChildren().add(createFormContents());
		container.getChildren().add(createButtons());

		getChildren().add(container);

		// STEP 0: Be sure you tell your model what keys you are interested in
		myModel.subscribe("LoginError", this);
	}

	// Create the label (Text) for the title of the screen
	//-------------------------------------------------------------
	private Node createTitle()
	{
		
		Text titleText = new Text("The Party Bookmobile!!!1!");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		
	
		return titleText;
	}

	// Create the main form contents
	//-------------------------------------------------------------
	/*private GridPane createFormContents()
	{
		GridPane grid = new GridPane();
        	grid.setAlignment(Pos.CENTER);
       		grid.setHgap(10);
        	grid.setVgap(10);
        	grid.setPadding(new Insets(25, 25, 25, 25));

		// data entry fields
		Label userName = new Label("User ID:");
        	grid.add(userName, 0, 0);

		userid = new TextField();
		userid.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     	processAction(e);    
            	     }
        	});
        	grid.add(userid, 1, 0);

		Label pw = new Label("Password:");
        	grid.add(pw, 0, 1);

		password = new PasswordField();
		password.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     	processAction(e);    
            	     }
        	});
        	grid.add(password, 1, 1);

		submitButton = new Button("Submit");
 		submitButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     	processAction(e);    
            	     }
        	});

		HBox btnContainer = new HBox(10);
		btnContainer.setAlignment(Pos.BOTTOM_RIGHT);
		btnContainer.getChildren().add(submitButton);
		grid.add(btnContainer, 1, 3);

		return grid;
	}*/

	//Create buttons
	private VBox createButtons()
	{
		VBox buttonBox = new VBox(20);
		buttonBox.setPrefWidth(200);
		buttonBox.setAlignment(Pos.CENTER);

//Five buttons to add: "Add Book", "Add Patron", "Search Books", "Search Patrons", and "Exit".

		addBookButton = new Button("Add Book");
		addBookButton.setMinWidth(buttonBox.getPrefWidth());
		addBookButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		buttonBox.getChildren().add(addBookButton);

		addPatronButton = new Button("Add Patron");
		addPatronButton.setMinWidth(buttonBox.getPrefWidth());
		addPatronButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		buttonBox.getChildren().add(addPatronButton);

		searchBooksButton = new Button("Search Books");
		searchBooksButton.setMinWidth(buttonBox.getPrefWidth());
		searchBooksButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				myModel.stateChangeRequest("SearchBooks", null);
			}
		});
		buttonBox.getChildren().add(searchBooksButton);

		searchPatronsButton = new Button("Search Patrons");
		searchPatronsButton.setMinWidth(buttonBox.getPrefWidth());
		searchPatronsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		buttonBox.getChildren().add(searchPatronsButton);

		exitButton = new Button("Exit");
		exitButton.setMinWidth(buttonBox.getPrefWidth());
		exitButton.setCancelButton(true); //Activates on escape key
		searchPatronsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				processAction(e);
			}
		});
		buttonBox.getChildren().add(exitButton);

		return buttonBox;
	}

	

	// Create the status log field
	/*-------------------------------------------------------------
	private MessageView createStatusLog(String initialMessage)
	{

		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	// This method processes events generated from our GUI components.
	// Make the ActionListeners delegate to this method*/
	//-------------------------------------------------------------
	public void processAction(Event evt)
	{
	// DEBUG: System.out.println("TellerView.actionPerformed()");


	}

	/**
	 * Process userid and pwd supplied when Submit button is hit.
	 * Action is to pass this info on to the teller object
	//----------------------------------------------------------
	private void processUserIDAndPassword(String useridString,
		String passwordString)
	{
		Properties props = new Properties();
		props.setProperty("ID", useridString);
		props.setProperty("Password", passwordString);

		// clear fields for next time around
		userid.setText("");
		password.setText("");

		myModel.stateChangeRequest("Login", props);
	}*/

	//---------------------------------------------------------
	public void updateState(String key, Object value)
	{
		// STEP 6: Be sure to finish the end of the 'perturbation'
		// by indicating how the view state gets updated.
		/*if (key.equals("LoginError") == true)
		{
			// display the passed text
			displayErrorMessage((String)value);
		}*/

	}

	/**
	 * Display error message
	 */
	/*----------------------------------------------------------
	public void displayErrorMessage(String message)
	{
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Clear error message

	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}*/

}

