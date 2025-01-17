// specify the package
package model;

// system imports

import event.Event;
import exception.InvalidPrimaryKeyException;
import exception.PasswordMismatchException;
import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

import java.util.Hashtable;
import java.util.Properties;

/** The class containing the Librarian for the Library application */
//==============================================================
public class Librarian implements IView, IModel
// This class implements all these interfaces (and does NOT extend 'EntityBase')
// because it does NOT play the role of accessing the back-end database tables.
// It only plays a front-end role. 'EntityBase' objects play both roles.
{
	// For Impresario
	private Properties dependencies;
	private ModelRegistry myRegistry;

	private AccountHolder myAccountHolder;

	// GUI Components
	private Hashtable<String, Scene> myViews;
	private Stage myStage;

	private String transactionErrorMessage = "";

	private BookCollection bookCollection;
	private PatronCollection patronCollection;

	// constructor for this class
	//----------------------------------------------------------
	public Librarian()
	{
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		// STEP 3.1: Create the Registry object - if you inherit from
		// EntityBase, this is done for you. Otherwise, you do it yourself
		myRegistry = new ModelRegistry("Librarian");
		if(myRegistry == null)
		{
			new Event(Event.getLeafLevelClassName(this), "Librarian",
				"Could not instantiate Registry", Event.ERROR);
		}

		// STEP 3.2: Be sure to set the dependencies correctly
		setDependencies();

		// Set up the initial view
		createAndShowLibrarianView();
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
		dependencies = new Properties();

		/*dependencies.setProperty("Deposit", "TransactionError");
		dependencies.setProperty("Withdraw", "TransactionError");
		dependencies.setProperty("Transfer", "TransactionError");
		dependencies.setProperty("BalanceInquiry", "TransactionError");
		dependencies.setProperty("ImposeServiceCharge", "TransactionError");*/


		myRegistry.setDependencies(dependencies);
	}

	/**
	 * Method called from client to get the value of a particular field
	 * held by the objects encapsulated by this object.
	 *
	 * @param	key	Name of database column (field) for which the client wants the value
	 *
	 * @return	Value associated with the field
	 */
	//----------------------------------------------------------
	public Object getState(String key)
	{
		if (key.equals("TransactionError") == true) {
			return transactionErrorMessage;
		}
		else
		if (key.equals("BookList") == true)
		{
			return bookCollection;
		}
		if (key.equals("PatronList") == true)
		{
			return patronCollection;
		}
		else
			return "";
	}

	//----------------------------------------------------------------
	public void stateChangeRequest(String key, Object value)
	{
		// STEP 4: Write the sCR method component for the key you
		// just set up dependencies for
		// DEBUG System.out.println("Teller.sCR: key = " + key);

		if (key.equals("SearchBooks") == true)
		{
			createAndShowBookSearchView();
		}
		else
		if (key.equals("BookQuery") == true)
		{
			doBookQuery((String)value);
		}
		else
		if (key.equals("SearchPatrons") == true)
		{
			createAndShowPatronSearchView();
		}
		else
		if (key.equals("PatronQuery") == true)
		{
			doPatronQuery((String)value);
		}
		else
		if (key.equals("AddBook") == true)
		{
			createAndShowBookSearchView();
		}
		if (key.equals("AddPatron") == true)
		{
			createAndShowPatronSearchView();
		}
		else
		if(key.equals("ExitApp") == true)
		{
			exitLibraryApp();
		}
		else
		if (key.equals("CancelTransaction") == true)
		{
			createAndShowLibrarianView();
		}

		myRegistry.updateSubscribers(key, this);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value)
	{
		// DEBUG System.out.println("Teller.updateState: key: " + key);

		stateChangeRequest(key, value);
	}



	/**
	 * Create a Transaction depending on the Transaction type (deposit,
	 * withdraw, transfer, etc.). Use the AccountHolder holder data to do the
	 * create.
	 */
	//----------------------------------------------------------
	public void doTransaction(String transactionType)
	{
		try
		{
			Transaction trans = TransactionFactory.createTransaction(
				transactionType, myAccountHolder);

			trans.subscribe("CancelTransaction", this);
			trans.stateChangeRequest("DoYourJob", "");
		}
		catch (Exception ex)
		{
			transactionErrorMessage = "FATAL ERROR: TRANSACTION FAILURE: Unrecognized transaction!!";
			new Event(Event.getLeafLevelClassName(this), "createTransaction",
					"Transaction Creation Failure: Unrecognized transaction " + ex.toString(),
					Event.ERROR);
		}
	}

	//----------------------------------------------------------
	private void createAndShowTransactionChoiceView()
	{
		Scene currentScene = (Scene)myViews.get("TransactionChoiceView");
		
		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("TransactionChoiceView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("TransactionChoiceView", currentScene);
		}
				

		// make the view visible by installing it into the frame
		swapToView(currentScene);
		
	}

	//------------------------------------------------------------
	private void createAndShowLibrarianView()
	{
		Scene currentScene = (Scene)myViews.get("LibrarianView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("LibrarianView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("LibrarianView", currentScene);
		}
				
		swapToView(currentScene);
		
	}

	public void createAndShowBookSearchView()
	{
		Scene currentScene = (Scene)myViews.get("BookSearchView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("BookSearchView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("BookSearchView", currentScene);
		}


		// make the view visible by installing it into the frame
		swapToView(currentScene);
	}

	public void doBookQuery(String querystring)
	{
		bookCollection = new BookCollection();
		bookCollection.getBooksByTitle(querystring);

		Scene currentScene = (Scene)myViews.get("BookCollectionView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("BookCollectionView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("BookCollectionView", currentScene);
		}


		// make the view visible by installing it into the frame
		swapToView(currentScene);
	}

	public void createAndShowPatronSearchView()
	{
		Scene currentScene = (Scene)myViews.get("PatronSearchView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("PatronSearchView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("PatronSearchView", currentScene);
		}


		// make the view visible by installing it into the frame
		swapToView(currentScene);
	}

	public void doPatronQuery(String querystring)
	{
		patronCollection = new PatronCollection();
		System.out.println(patronCollection.getPatronsByZip(querystring));

		Scene currentScene = (Scene)myViews.get("PatronCollectionView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("PatronCollectionView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("PatronCollectionView", currentScene);
		}

		// make the view visible by installing it into the frame
		swapToView(currentScene);
	}

	public void exitLibraryApp(){System.exit(0);}


	/** Register objects to receive state updates. */
	//----------------------------------------------------------
	public void subscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
		// forward to our registry
		myRegistry.subscribe(key, subscriber);
	}

	/** Unregister previously registered objects. */
	//----------------------------------------------------------
	public void unSubscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager.unSubscribe");
		// forward to our registry
		myRegistry.unSubscribe(key, subscriber);
	}



	//-----------------------------------------------------------------------------
	public void swapToView(Scene newScene)
	{

		
		if (newScene == null)
		{
			System.out.println("Teller.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView",
				"Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();
		
			
		//Place in center
		WindowPosition.placeCenter(myStage);

	}

}

