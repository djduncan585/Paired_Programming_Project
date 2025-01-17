package model;

// system imports
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JFrame;

// project imports
import exception.InvalidPrimaryKeyException;
import database.*;

import impresario.IView;

import model.EntityBase;
import userinterface.View;
import userinterface.ViewFactory;

public class Book extends EntityBase
{
	public static final String myTableName = "Book1";

	protected Properties dependencies;

	// GUI Components

	private String updateStatusMessage = "";

	// constructor for this class
	//----------------------------------------------------------
	public Book(String bookId)
		throws InvalidPrimaryKeyException
	{
		super(myTableName); //EntityBase("Book");

		setDependencies();
		String query = "SELECT * FROM " + myTableName + " WHERE (bookId = " + bookId + ")";

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// You must get one account at least
		if (allDataRetrieved != null)
		{
			int size = allDataRetrieved.size();

			// There should be EXACTLY one account. More than that is an error
			if (size != 1)
			{
				throw new InvalidPrimaryKeyException("Multiple books matching id : "
					+ bookId + " found.");
			}
			else
			{
				// copy all the retrieved data into persistent state
				Properties retrievedBookData = allDataRetrieved.elementAt(0);
				persistentState = new Properties();

				Enumeration allKeys = retrievedBookData.propertyNames();
				while (allKeys.hasMoreElements() == true)
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedBookData.getProperty(nextKey);
					// accountNumber = Integer.parseInt(retrievedAccountData.getProperty("accountNumber"));

					if (nextValue != null)
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}

			}
		}
		// If no book found for this bookId, throw an exception
		else
		{
			throw new InvalidPrimaryKeyException("No book matching ID : "
				+ bookId + " found.");
		}
	}

	// Can also be used to create a NEW Book (if the system it is part of
	// allows for a new book to be set up)
	//----------------------------------------------------------
	public Book(Properties props)
	{
		super(myTableName); //new EntityBase("Book");

		setDependencies();
		persistentState = new Properties();
		Enumeration allKeys = props.propertyNames();
		while (allKeys.hasMoreElements() == true)
		{
			String nextKey = (String)allKeys.nextElement();
			String nextValue = props.getProperty(nextKey);

			if (nextValue != null)
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
		dependencies = new Properties();
	
		myRegistry.setDependencies(dependencies);
	}

	public Object getState(String key)
	{
		if (key.equals("UpdateStatusMessage") == true)
			return updateStatusMessage;

		return persistentState.getProperty(key);
	}

	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}

	public void stateChangeRequest(String key, Object value)
	{

		myRegistry.updateSubscribers(key, this);
	}

	/** Called via the IView relationship */
	//

	/*Inserts a new object into the database or updates an existing object in the database, depending on whether the auto-generated primary key is present or not. */
	public void update()
	{
		updateStateInDatabase();
	}

	private void updateStateInDatabase() 
	{
		try
		{
			if (persistentState.getProperty("bookId") != null)
			{
				Properties whereClause = new Properties();
				whereClause.setProperty("bookId",
				persistentState.getProperty("bookId"));
				updatePersistentState(mySchema, persistentState, whereClause);
				updateStatusMessage = "Book data for bookId : " + persistentState.getProperty("bookId") + " updated successfully in database!";
			}
			else
			{
				Integer bookId =
					insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("bookId", "" + bookId.intValue());
				updateStatusMessage = "New data for new book : " +  persistentState.getProperty("bookId")
					+ "installed successfully in database!";
			}
		}
		catch (SQLException ex)
		{
			updateStatusMessage = "Error in installing book data in database!";
		}


		//DEBUG System.out.println("updateStateInDatabase " + updateStatusMessage);
	}
	@Override
	public String toString() {
		return "Book{" +
				persistentState.getProperty("bookTitle") + ", " + persistentState.getProperty("pubYear") +
				'}' + '\n';
	}

	/**
	 * This method is needed solely to enable the Book information to be displayable in a table
	 *
	 */
	//--------------------------------------------------------------------------
	public Vector<String> getEntryListView()
	{
		Vector<String> v = new Vector<String>();

		v.addElement(persistentState.getProperty("bookId"));
		v.addElement(persistentState.getProperty("bookTitle"));
		v.addElement(persistentState.getProperty("author"));
		v.addElement(persistentState.getProperty("pubYear"));
		v.addElement(persistentState.getProperty("status"));

		return v;
	}

}
