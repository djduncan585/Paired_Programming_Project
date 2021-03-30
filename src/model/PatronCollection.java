package model;

import model.EntityBase;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

public class PatronCollection extends EntityBase {

    private static final String myTableName = Patron.myTableName;

    private Vector<Patron> patrons;

    public PatronCollection() {
        super(myTableName);
    }
    

    public void addPatron(String address, String city, String dateOfBirth, String email, String name, String stateCode, String status, String zip) {
        Properties properties = new Properties();
        properties.put("address", address);
        properties.put("city", city);
        properties.put("dateOfBirth", dateOfBirth);
        properties.put("email", email);
        properties.put("name", name);
        properties.put("stateCode", stateCode);
        properties.put("status", status);
        properties.put("zip", zip);
        Patron patron = new Patron(properties);
        patron.update();
    }

    public Vector getPatronsByDate(String date) {
        String query = "SELECT * FROM " + myTableName;
        Vector allPatrons = getSelectQueryResult(query);
        patrons= new Vector();

        for (Object o: allPatrons) {
            Properties props = (Properties)o;

            if (props.getProperty("dateOfBirth").compareTo(date) > 0) {
                patrons.add(new Patron(props));
            }
        }
        return patrons;
    }

    

    public Vector<Patron> getPatronsByZip(String zip) {
        String query = "SELECT * FROM " + myTableName + " WHERE (zip = '" + zip + "')";
        Vector allPatrons = getSelectQueryResult(query);
        patrons= new Vector();

        for (Object o: allPatrons) {
            Properties props = (Properties)o;

            patrons.add(new Patron(props));

        }
        return patrons;

    }



    @Override
    public Object getState(String key) {
        if (key.equals("Patrons"))
            return patrons;
        else
        if (key.equals("PatronList"))
            return this;
        return null;
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        myRegistry.updateSubscribers(key, this);
    }

    @Override
    protected void initializeSchema(String tableName) {

    }


//    public static void main(String[] args) {
//
//        try {
//
//
//            String Patron1 = "2000-08-18";
//            String Patron2 = "1950-05-02";
//            String Patron3 = "2100-03-16";
//            String Patron4 = "1968-07-07";
//            String zip1 = "14620";
//            String zip2 = "14487";
//            String zip3 = "15798";
//
//
//
//
//            PatronCollection manager = new PatronCollection();
//            //manager.addPatron("Lake st","Oswego","2000-06-02","mjones@gmail.com","Mark Jones" ,"30","ACTIVE","16487");
//            System.out.println(manager.getPatronsByDate(Patron1));
//            System.out.println(manager.getPatronsByDate(Patron2));
//            System.out.println(manager.getPatronsByDate(Patron3));
//            System.out.println(manager.getPatronsByDate(Patron4));
//            System.out.println(manager.getPatronsByZip(zip1));
//            System.out.println(manager.getPatronsByZip(zip2));
//            System.out.println(manager.getPatronsByZip(zip3));
//
//            String birth = "1997-03-16";
//            String death = "2100-03-17";
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = dateFormat.parse(birth);
//            Date date2 = dateFormat.parse(death);
//            System.out.println(date.before(date2));
//            System.out.println(date);
//            System.out.println(date2);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
