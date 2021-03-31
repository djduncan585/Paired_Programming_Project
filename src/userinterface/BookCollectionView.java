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
public class BookCollectionView extends View
{
	protected TableView<BookTableModel> tableOfBooks;
	protected Button okButton;

	protected MessageView statusLog;


	//--------------------------------------------------------------------------
	public BookCollectionView(IModel wsc)
	{
		super(wsc, "BookCollectionView");

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
		ObservableList<BookTableModel> tableData = FXCollections.observableArrayList();
		try
		{
			BookCollection bookCollection = (BookCollection)myModel.getState("BookList");
	 		Vector entryList = (Vector)bookCollection.getState("Books");
			Enumeration entries = entryList.elements();

			while (entries.hasMoreElements() == true)
			{
				Book nextBook = (Book)entries.nextElement();
				Vector<String> view = nextBook.getEntryListView();

				// add this list entry to the list
				BookTableModel nextTableRowData = new BookTableModel(view);
				tableData.add(nextTableRowData);
				
			}
			
			tableOfBooks.setItems(tableData);
		}
		catch (Exception e) {//SQLException e) {
			e.printStackTrace();
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
        
        Text prompt = new Text("LIST OF BOOKS");
        prompt.setWrappingWidth(350);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		tableOfBooks = new TableView<BookTableModel>();
		tableOfBooks.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	
		TableColumn bookIdColumn = new TableColumn("Book ID") ;
		bookIdColumn.setMinWidth(100);
		bookIdColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookId"));
		
		TableColumn bookTitleColumn = new TableColumn("Book Title") ;
		bookTitleColumn.setMinWidth(100);
		bookTitleColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("bookTitle"));
		  
		TableColumn authorColumn = new TableColumn("Author") ;
		authorColumn.setMinWidth(100);
		authorColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("author"));
		
		TableColumn pubYearColumn = new TableColumn("Published Year") ;
		pubYearColumn.setMinWidth(100);
		pubYearColumn.setCellValueFactory(
	                new PropertyValueFactory<BookTableModel, String>("pubYear"));

		TableColumn bookStatusColumn = new TableColumn("Book Status") ;
		pubYearColumn.setMinWidth(100);
		pubYearColumn.setCellValueFactory(
				new PropertyValueFactory<BookTableModel, String>("bookStatus"));

		tableOfBooks.getColumns().addAll(bookIdColumn, bookTitleColumn, authorColumn, pubYearColumn, bookStatusColumn);

		tableOfBooks.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event)
			{
				if (event.isPrimaryButtonDown() && event.getClickCount() >=2 ){
					processBookSelected();
				}
			}
		});
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefSize(115, 150);
		scrollPane.setContent(tableOfBooks);

		okButton = new Button("OK");
 		okButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		     	clearErrorMessage();
       		     	myModel.stateChangeRequest("CancelTransaction", null);
            	  }
        	});

		HBox btnContainer = new HBox(100);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().add(okButton);
		
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
	protected void processBookSelected()
	{
		BookTableModel selectedItem = tableOfBooks.getSelectionModel().getSelectedItem();
		
		if(selectedItem != null)
		{
			String selectedBookId = selectedItem.getBookId();

			myModel.stateChangeRequest("BookSelected", selectedBookId);
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
			processAccountSelected();
		}
	}
   */
	
}
