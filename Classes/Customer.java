package Classes;
import java.util.List;

public class Customer {

    private String ssn;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String firstName;
    private String lastName;

    public Customer(List<String> cust){
        ssn = cust.get(0);
        streetAddress = cust.get(1);
        city = cust.get(2);
        state = cust.get(3);
        zip = cust.get(4);
        firstName = cust.get(5);
        lastName = cust.get(6);
    }

    
    
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



}
