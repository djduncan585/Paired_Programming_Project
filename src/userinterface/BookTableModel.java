package userinterface;

import javafx.beans.property.SimpleStringProperty;

import java.util.Vector;

//==============================================================================
public class BookTableModel
{
    private final SimpleStringProperty bookId;
    private final SimpleStringProperty bookTitle;
    private final SimpleStringProperty author;
    private final SimpleStringProperty pubYear;
    private final SimpleStringProperty status;

	//----------------------------------------------------------------------------
	public BookTableModel(Vector<String> bookData)
	{
        bookId =  new SimpleStringProperty(bookData.elementAt(0));
        bookTitle =  new SimpleStringProperty(bookData.elementAt(1));
        author =  new SimpleStringProperty(bookData.elementAt(2));
        pubYear =  new SimpleStringProperty(bookData.elementAt(3));
        status =  new SimpleStringProperty(bookData.elementAt(4));
	}

	//----------------------------------------------------------------------------
	public String getBookId() {
        return bookId.get();
    }

	//----------------------------------------------------------------------------
    public void setBookId(String number) {
        bookId.set(number);
    }

    //----------------------------------------------------------------------------
    public String getBookTitle() {
        return bookTitle.get();
    }

    //----------------------------------------------------------------------------
    public void setBookTitle(String title) {
        bookTitle.set(title);
    }

    //----------------------------------------------------------------------------
    public String getAuthor() {
        return author.get();
    }

    //----------------------------------------------------------------------------
    public void setAuthor(String a) {
        author.set(a);
    }

    //----------------------------------------------------------------------------
    public String getPubYear() {
        return pubYear.get();
    }

    //----------------------------------------------------------------------------
    public void setPubYear(String py) {
        pubYear.set(py);
    }

    //----------------------------------------------------------------------------
    public String getStatus() {
        return status.get();
    }

    //----------------------------------------------------------------------------
    public void setStatus(String st) {
        status.set(st);
    }

}
