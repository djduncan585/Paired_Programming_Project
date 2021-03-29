package userinterface;

// system imports

import impresario.IModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.*;

import java.util.Enumeration;
import java.util.Vector;

//==============================================================================
public class PatronCollectionView extends View
{
	protected TableView<PatronTableModel> tableOfPatrons;
	protected Button cancelButton;
	protected Button submitButton;

	protected MessageView statusLog;


	//--------------------------------------------------------------------------
	public PatronCollectionView(IModel wsc)
	{
		super(wsc, "PatronCollectionView");

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// create our GUI components, add them to this panel
		container.getChildren().add(createTitle());
		container.getChildren().add(createFormContent());

		// Error message area
		container.getChildren().add(createStatusLog("                                            "));

		getChildren().add(container);
		
		populateFields();
	}

	//--------------------------------------------------------------------------
	protected void populateFields()
	{
		getEntryTableModelValues();
	}

	//--------------------------------------------------------------------------
	protected void getEntryTableModelValues()
	{
		
		ObservableList<PatronTableModel> tableData = FXCollections.observableArrayList();
		try
		{
			PatronCollection patronCollection = (PatronCollection)myModel.getState("PatronList");

	 		Vector entryList = (Vector)patronCollection.getState("Patrons");
			Enumeration entries = entryList.elements();

			while (entries.hasMoreElements() == true)
			{
				Patron nextPatron = (Patron)entries.nextElement();
				Vector<String> view = nextPatron.getEntryListView();

				// add this list entry to the list
				PatronTableModel nextTableRowData = new PatronTableModel(view);
				tableData.add(nextTableRowData);
				
			}
			
			tableOfPatrons.setItems(tableData);
		}
		catch (Exception e) {//SQLException e) {
			// Need to handle this exception
		}
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
        
        Text prompt = new Text("LIST OF PATRONS");
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		tableOfPatrons = new TableView<PatronTableModel>();
		tableOfPatrons.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
		TableColumn patronIdColumn = new TableColumn("Patron ID") ;
		patronIdColumn.setMinWidth(100);
		patronIdColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronId"));
		
		TableColumn patronNameColumn = new TableColumn("Patron Name") ;
		patronNameColumn.setMinWidth(100);
		patronNameColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronName"));
		  
		TableColumn patronAddressColumn = new TableColumn("Address") ;
		patronAddressColumn.setMinWidth(100);
		patronAddressColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronAddress"));
		
		TableColumn patronCityColumn = new TableColumn("City") ;
		patronCityColumn.setMinWidth(100);
		patronCityColumn.setCellValueFactory(
	                new PropertyValueFactory<PatronTableModel, String>("patronCity"));

		TableColumn patronStateColumn = new TableColumn("State") ;
		patronStateColumn.setMinWidth(100);
		patronStateColumn.setCellValueFactory(
				new PropertyValueFactory<PatronTableModel, String>("patronState"));

		TableColumn patronZipCodeColumn = new TableColumn("Zip") ;
		patronZipCodeColumn.setMinWidth(100);
		patronZipCodeColumn.setCellValueFactory(
				new PropertyValueFactory<PatronTableModel, String>("patronZipCode"));

		TableColumn patronEmailColumn = new TableColumn("Email") ;
		patronEmailColumn.setMinWidth(100);
		patronEmailColumn.setCellValueFactory(
				new PropertyValueFactory<PatronTableModel, String>("patronEmail"));

		TableColumn patronDateOfBirthColumn = new TableColumn("Date Of Birth") ;
		patronEmailColumn.setMinWidth(100);
		patronEmailColumn.setCellValueFactory(
				new PropertyValueFactory<PatronTableModel, String>("patronDateOfBirth"));

		TableColumn patronStatusColumn = new TableColumn("Patron Status") ;
		patronStatusColumn.setMinWidth(100);
		patronStatusColumn.setCellValueFactory(
				new PropertyValueFactory<PatronTableModel, String>("patronStatus"));

		tableOfPatrons.getColumns().addAll(patronIdColumn, patronNameColumn, patronAddressColumn, patronCityColumn,
				patronStateColumn, patronZipCodeColumn, patronEmailColumn, patronDateOfBirthColumn, patronStatusColumn);

		tableOfPatrons.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event)
			{
				if (event.isPrimaryButtonDown() && event.getClickCount() >=2 ){
					processPatronSelected();
				}
			}
		});
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(115, 150);
		scrollPane.setContent(tableOfPatrons);

		submitButton = new Button("Submit");
 		submitButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     	clearErrorMessage(); 
					// do the inquiry
					processPatronSelected();
					
            	 }
        	});

		cancelButton = new Button("Back");
 		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
					/**
					 * Process the Cancel button.
					 * The ultimate result of this action is that the transaction will tell the teller to
					 * to switch to the transaction choice view. BUT THAT IS NOT THIS VIEW'S CONCERN.
					 * It simply tells its model (controller) that the transaction was canceled, and leaves it
					 * to the model to decide to tell the teller to do the switch back.
			 		*/
					//----------------------------------------------------------
       		     	clearErrorMessage();
       		     	myModel.stateChangeRequest("CancelPatronList", null);
            	  }
        	});

		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().add(submitButton);
		btnContainer.getChildren().add(cancelButton);
		
		vbox.getChildren().add(grid);
		vbox.getChildren().add(scrollPane);
		vbox.getChildren().add(btnContainer);
	
		return vbox;
	}

	

	//--------------------------------------------------------------------------
	public void updateState(String key, Object value)
	{
	}

	//--------------------------------------------------------------------------
	protected void processPatronSelected()
	{
		PatronTableModel selectedItem = tableOfPatrons.getSelectionModel().getSelectedItem();
		
		if(selectedItem != null)
		{
			String selectedPatronId = selectedItem.getPatronId();

			myModel.stateChangeRequest("PatronSelected", selectedPatronId);
		}
	}

	//--------------------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage)
	{
		statusLog = new MessageView(initialMessage);

		return statusLog;
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
	/*
	//--------------------------------------------------------------------------
	public void mouseClicked(MouseEvent click)
	{
		if(click.getClickCount() >= 2)
		{
			processPatronSelected();
		}
	}
   */
	
}
