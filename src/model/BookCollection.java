package model;

import model.EntityBase;

import java.util.Properties;
import java.util.Vector;

public class BookCollection extends EntityBase {

    private static final String myTableName = Book.myTableName;

    public BookCollection() {
        super(myTableName);
    }

    public void addBook (String author, String bookTitle, String pubYear, String status) {


        Properties properties = new Properties();
        properties.put("author", author);
        properties.put("bookTitle", bookTitle);
        properties.put("pubYear", pubYear);
        properties.put("status", status);
        Book book = new Book(properties);
        book.update();

    }


    public Vector<Book> getBooksByTitle(String book) {
        String query = "SELECT * FROM " + myTableName + " WHERE (bookTitle LIKE '%" + book + "%')";
        Vector allBooks = getSelectQueryResult(query);
        Vector<Book> books= new Vector();

        for (Object o: allBooks) {
            Properties props = (Properties)o;
            books.add(new Book(props));
        }
        return books;

    }

    public Vector<Book> getBooksByYear(String year) {
        String query = "SELECT * FROM " + myTableName;
        Vector allBooks = getSelectQueryResult(query);
        Vector<Book> books= new Vector();

        for (Object o: allBooks) {
            Properties props = (Properties)o;

            if (Integer.valueOf(props.getProperty("pubYear")) <= Integer.valueOf(year)) {
                books.add(new Book(props));
            }
        }
        return books;
    }

    @Override
    public Object getState(String key) {
        return null;
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    @Override
    protected void initializeSchema(String tableName) {

    }








//    public static void main(String[] args) { //Just used for testing. This will need to be replaced with Java FX
//
//        try {
//
//
//            String book1 = "Har";
//            String book2 = "Pot";
//            String book3 = "Boy";
//            String book4 = "Wrong";
//            String year1 = "2021";
//            String year2 = "2010";
//            String year3 = "2000";
//            String year4 = "1999";
//            String year5 = "1885";
//
//
//            BookCollection manager = new BookCollection();
//            manager.addBook("Dean Koontz","Watchers","1985","ACTIVE");
//            manager = new BookCollection();
//            Properties properties = new Properties();
//            properties.put("author", "Dean Koontz");
//            properties.put("bookTitle", "Watchers");
//            properties.put("pubYear", "1985");
//            properties.put("status", "Active");
//            Book book = new Book(properties);
//            book.update();
//            System.out.println(manager.getBooksByTitle(book1));
//            System.out.println(manager.getBooksByTitle(book2));
//            System.out.println(manager.getBooksByTitle(book3));
//            System.out.println(manager.getBooksByTitle(book4));
//            System.out.println(manager.getBooksByYear(year1));
//            System.out.println(manager.getBooksByYear(year2));
//            System.out.println(manager.getBooksByYear(year3));
//            System.out.println(manager.getBooksByYear(year4));
//            System.out.println(manager.getBooksByYear(year5));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
