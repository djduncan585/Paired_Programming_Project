package userinterface;

import javafx.beans.property.SimpleStringProperty;

import java.util.Vector;

//==============================================================================
public class PatronTableModel
{
    private final SimpleStringProperty patronId;
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;
    private final SimpleStringProperty city;
    private final SimpleStringProperty stateCode;
    private final SimpleStringProperty zip;
    private final SimpleStringProperty email;
    private final SimpleStringProperty dateOfBirth;
    private final SimpleStringProperty status;

    //----------------------------------------------------------------------------
	public PatronTableModel(Vector<String> patronData)
	{
        patronId =  new SimpleStringProperty(patronData.elementAt(0));
        name =  new SimpleStringProperty(patronData.elementAt(1));
        address =  new SimpleStringProperty(patronData.elementAt(2));
        city =  new SimpleStringProperty(patronData.elementAt(3));
        stateCode =  new SimpleStringProperty(patronData.elementAt(4));
        zip =  new SimpleStringProperty(patronData.elementAt(5));
        email =  new SimpleStringProperty(patronData.elementAt(6));
        dateOfBirth =  new SimpleStringProperty(patronData.elementAt(7));
        status =  new SimpleStringProperty(patronData.elementAt(8));
	}

	//----------------------------------------------------------------------------
	public String getPatronId() {
        return patronId.get();
    }

	//----------------------------------------------------------------------------
    public void setPatronId(String number) {
        patronId.set(number);
    }

    //----------------------------------------------------------------------------
    public String getName() {
        return name.get();
    }

    //----------------------------------------------------------------------------
    public void setName(String n) {
        name.set(n);
    }

    //----------------------------------------------------------------------------
    public String getAddress() {
        return address.get();
    }

    //----------------------------------------------------------------------------
    public void setAddress(String a) {
        address.set(a);
    }

    //----------------------------------------------------------------------------
    public String getCity() {
        return city.get();
    }

    //----------------------------------------------------------------------------
    public void setCity(String c) {
        city.set(c);
    }

    //----------------------------------------------------------------------------
    public String getStateCode() { return stateCode.get(); }

    //----------------------------------------------------------------------------
    public void setStateCode(String sc) {
        stateCode.set(sc);
    }

    //----------------------------------------------------------------------------
    public String getZip() { return zip.get(); }

    //----------------------------------------------------------------------------
    public void setZip(String z) {
        zip.set(z);
    }

    //----------------------------------------------------------------------------
    public String getEmail() { return email.get(); }

    //----------------------------------------------------------------------------
    public void setEmail(String e) {
        email.set(e);
    }

    //----------------------------------------------------------------------------
    public String getDateOfBirth() { return dateOfBirth.get(); }

    //----------------------------------------------------------------------------
    public void setDateOfBirth(String dob) {
        dateOfBirth.set(dob);
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
