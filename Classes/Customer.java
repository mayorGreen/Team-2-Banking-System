package Classes;
import java.io.Serializable;
import java.util.List;

// main class for customer objects, contains customer information
public class Customer implements Serializable{

    // TODO: make pin number from last 4 ssn
    private String ssn;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String firstName;
    private String lastName;
    private String loginUsername;
    private String loginPassword;

    public Customer(List<String> cust){
        ssn = cust.get(0);
        streetAddress = cust.get(1);
        city = cust.get(2);
        state = cust.get(3);
        zip = cust.get(4);
        firstName = cust.get(5);
        lastName = cust.get(6);
        if(lastName != null && firstName != null) loginUsername = lastName + firstName.charAt(0);
        if(ssn != null) loginPassword = ssn.substring(ssn.length()-4, ssn.length());
    }

    public Customer(String ssn, String streetAddress, String city, String state, String zip, String firstName, String lastName){
        this.ssn = ssn;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.firstName = firstName;
        this.lastName = lastName;
        if(lastName != null && firstName != null) this.loginUsername = lastName + firstName.charAt(0);
        if(ssn != null) this.loginPassword = ssn.substring(ssn.length()-4, ssn.length()-1);
    }

    
    // getters
    public String getFirstName() {
        return firstName;
    }public String getCity() {
        return city;
    }public String getLastName() {
        return lastName;
    }public String getSsn() {
        return ssn;
    }public String getState() {
        return state;
    }public String getStreetAddress() {
        return streetAddress;
    }public String getZip() {
        return zip;
    }
    public String getLoginUsername() {
        return loginUsername;
    }
    public String getLoginPassword() {
        return loginPassword;
    }

    @Override
    public String toString() {
        return  "CustID: " + ssn + " Street Address: " + streetAddress + " City: " + city
        + " State: " + state + " Zip Code: " + zip + " First Name: " + firstName + " Last Name: " + lastName
        + " Username: " + loginUsername + " Password: " + loginPassword;


    }

} // end Customer
