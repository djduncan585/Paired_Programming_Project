// specify the package
package userinterface;

// system imports

import impresario.IModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/** The class containing the Book View for the Library application */
//==============================================================
public class BookView extends View
{

	// GUI components
	protected TextField bookId;
	protected TextField bookTitle;
	protected TextField author;
	protected TextField pubYear;
	protected TextField bookStatus;

	protected Button cancelButton;

	// For showing error message
	protected MessageView statusLog;

	// constructor for this class -- takes a model object
	//----------------------------------------------------------
	public BookView(IModel account)
	{
		super(account, "AccountView");

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// Add a title for this panel
		container.getChildren().add(createTitle());
		
		// create our GUI components, add them to this Container
		container.getChildren().add(createFormContent());

		container.getChildren().add(createStatusLog("             "));

		getChildren().add(container);

		populateFields();

		myModel.subscribe("ServiceCharge", this);
		myModel.subscribe("UpdateStatusMessage", this);
	}


	// Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text("The Party Bookmobile!!!1!");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);
		
		return container;
	}

	// Create the main form content
	//-------------------------------------------------------------
	private VBox createFormContent()
	{
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
       	grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text prompt = new Text("BOOK INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		Text bookIdLabel = new Text(" Book ID : ");
		Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
		bookIdLabel.setFont(myFont);
		bookIdLabel.setWrappingWidth(150);
		bookIdLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(bookIdLabel, 0, 1);

		bookId = new TextField();
		bookId.setEditable(true);
		bookId.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				myModel.stateChangeRequest("bookId", bookId.getText());
			}
		});
		grid.add(bookId, 1, 1);

		Text bookTitleLabel = new Text(" Book Title : ");
		bookTitleLabel.setFont(myFont);
		bookTitleLabel.setWrappingWidth(150);
		bookTitleLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(bookTitleLabel, 0, 2);

		bookTitle = new TextField();
		bookTitle.setEditable(true);
		bookTitle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				myModel.stateChangeRequest("bookTitle", bookTitle.getText());
			}
		});
		grid.add(bookTitle, 1, 2);

		Text authorLabel = new Text(" Author : ");
		authorLabel.setFont(myFont);
		authorLabel.setWrappingWidth(150);
		authorLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(authorLabel, 0, 3);

		author = new TextField();
		author.setEditable(true);
		author.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				myModel.stateChangeRequest("author", author.getText());
			}
		});
		grid.add(author, 1, 3);

		Text pubYearLabel = new Text(" Year Published : ");
		pubYearLabel.setFont(myFont);
		pubYearLabel.setWrappingWidth(150);
		pubYearLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(pubYearLabel, 0, 4);

		pubYear = new TextField();
		pubYear.setEditable(true);
		pubYear.setOnAction(new EventHandler<ActionEvent>() {

  		     @Override
  		     public void handle(ActionEvent e) {
  		    	clearErrorMessage();
  		    	myModel.stateChangeRequest("pubYear", pubYear.getText());
       	     }
        });
		grid.add(pubYear, 1, 4);
		Text bookStatusLabel = new Text(" Book Status : ");
		bookStatusLabel.setFont(myFont);
		bookStatusLabel.setWrappingWidth(150);
		bookStatusLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(pubYearLabel, 0, 5);

		pubYear = new TextField();
		pubYear.setEditable(true);
		pubYear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				clearErrorMessage();
				myModel.stateChangeRequest("pubYear", pubYear.getText());
			}
		});
		grid.add(pubYear, 1, 5);

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		cancelButton = new Button("Back");
		cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		    	clearErrorMessage();
       		    	myModel.stateChangeRequest("BookCancelled", null);
            	  }
        	});
		doneCont.getChildren().add(cancelButton);
	
		vbox.getChildren().add(grid);
		vbox.getChildren().add(doneCont);

		return vbox;
	}


	// Create the status log field
	//-------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage)
	{
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	//-------------------------------------------------------------
	public void populateFields()
	{
		bookId.setText((String)myModel.getState("bookId"));
		bookTitle.setText((String)myModel.getState("bookTitle"));
		author.setText((String)myModel.getState("author"));
	 	pubYear.setText((String)myModel.getState("pubYear"));
		bookStatus.setText((String)myModel.getState("bookStatus"));
	}

	/**
	 * Update method
	 */
	//---------------------------------------------------------
	public void updateState(String key, Object value)
	{
		/*clearErrorMessage();

		if (key.equals("ServiceCharge") == true)
		{
			String val = (String)value;
			serviceCharge.setText(val);
			displayMessage("Service Charge Imposed: $ " + val);
		}*/
	}

	/**
	 * Display error message
	 */
	//----------------------------------------------------------
	public void displayErrorMessage(String message)
	{
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Display info message
	 */
	//----------------------------------------------------------
	public void displayMessage(String message)
	{
		statusLog.displayMessage(message);
	}

	/**
	 * Clear error message
	 */
	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}

}

//---------------------------------------------------------------
//	Revision History:
//


