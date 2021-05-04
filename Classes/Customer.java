package Classes;
import java.io.Serializable;
import java.util.List;

// main class for customer objects, contains customer information
public class Customer implements Serializable{

    private String ssn; // unique identifier
    private String streetAddress; // customer's Address
    private String city; // customer's city
    private String state; // customer's state
    private String zip; // customer's ZIP code
    private String firstName; // customer's first name
    private String lastName; // customer's last name
    private String loginUsername; // customer's login username
    private String loginPassword; // customer's login password

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
        if(ssn != null && ssn.length() > 3)this.loginPassword = ssn.substring(ssn.length()-4, ssn.length()-1);
    }

    
    // getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getSsn() {
        return ssn;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getZip() {
        return zip;
    }
    public String getLoginUsername() {
        return loginUsername;
    }
    public String getLoginPassword() {
        return loginPassword;
    }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return  "CustID: " + ssn + " Street Address: " + streetAddress + " City: " + city
        + " State: " + state + " Zip Code: " + zip + " First Name: " + firstName + " Last Name: " + lastName
        + " Username: " + loginUsername + " Password: " + loginPassword;
    }

} // end Customer