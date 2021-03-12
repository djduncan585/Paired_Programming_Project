import java.util.*;
import model.*;

public class MainDriver {
    public static void main(String[] args) {
        String bookTitle;
        String author;
        String pubYear;
        boolean status = false;
        int statusMarker = 0;

        BookCollection managerB = new BookCollection();

        String name;
        String address;
        String city;
        String stateCode;
        String zip;
        String email;
        String dateOfBirth;
        PatronCollection managerP = new PatronCollection();

        String searchBook;
        String searchBookYear;
        String searchPatronYear;
        String searchPatronZip;


        Scanner in = new Scanner(System.in);
        System.out.println("This program is a demo and is therefore subject to change. As such, please only enter valid data.");
        try { //Add book section
            for (int i = 0; i < 1; i++) {
                statusMarker = 0;
                managerB = new BookCollection();
                System.out.println("Enter a book title: ");
                bookTitle = in.nextLine();
                System.out.println("Enter book author: ");
                author = in.nextLine();
                System.out.println("Enter publication year: ");
                pubYear = in.nextLine();
                System.out.println("Enter 0 if status is INACTIVE. 1 if status is ACTIVE: ");
                statusMarker = in.nextInt();
                in.nextLine();


                if (statusMarker == 0) {
                    status = false;
                } else if (statusMarker == 1) {
                    status = true;
                } else {
                    System.out.println("Invalid entry. Status will be set to INACTIVE.");
                    status = false;
                }
                if (status == true) {
                    managerB.addBook(author, bookTitle, pubYear, "ACTIVE");
                }
                else {
                    managerB.addBook(author, bookTitle, pubYear, "INACTIVE");
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try { //Add patron section
            for (int i = 0; i < 1; i++) {
                managerP = new PatronCollection();
                statusMarker = 0;
                System.out.println("Enter a patron name: ");
                name = in.nextLine();
                System.out.println("Enter patron address: ");
                address = in.nextLine();
                System.out.println("Enter patron city: ");
                city = in.nextLine();
                System.out.println("Enter patron state code: ");
                stateCode = in.nextLine();
                System.out.println("Enter patron zip: ");
                zip = in.nextLine();
                System.out.println("Enter patron email: ");
                email = in.nextLine();
                System.out.println("Enter patron date of birth: ");
                dateOfBirth = in.nextLine();
                System.out.println("Enter 0 if status is INACTIVE. 1 if status is ACTIVE: ");
                statusMarker = in.nextInt();
                in.nextLine();


                if (statusMarker == 0) {
                    status = false;
                } else if (statusMarker == 1) {
                    status = true;
                } else {
                    System.out.println("Invalid entry. Status will be set to INACTIVE.");
                    status = false;
                }
                if (status == true) {
                    managerP.addPatron(address, city, dateOfBirth, email, name, stateCode, "ACTIVE", zip);
                }
                else {
                    managerP.addPatron(address, city, dateOfBirth, email, name, stateCode, "INACTIVE", zip);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Enter part of a book title to search: ");
            searchBook = in.nextLine();
            System.out.println(managerB.getBooksByTitle(searchBook));
            System.out.println("Enter a year to search: ");
            searchBookYear = in.nextLine();
            System.out.println(managerB.getBooksByYear(searchBookYear));
            System.out.println("Enter patron date to search (yyyy-mm-dd): ");
            searchPatronYear = in.nextLine();
            System.out.println(managerP.getPatronsByDate(searchPatronYear));
            System.out.println("Enter patron zip to search: ");
            searchPatronZip = in.nextLine();
            System.out.println(managerP.getPatronsByZip(searchPatronZip));


        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("End of demo.");

    }
}
