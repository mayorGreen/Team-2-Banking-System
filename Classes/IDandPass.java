package Classes;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

// This class contains information and functions for the login interface
public class IDandPass {

    HashMap<String,String> customerLoginInfo = new HashMap<>(); // login info for customers -- this would be for something like an online banking app
    HashMap<String,String> tellerLoginInfo = new HashMap<>(); // teller login info
    HashMap<String,String> managerLoginInfo = new HashMap<>(); // manager login info
    ArrayList<HashMap<String,String>> loginInfo = new ArrayList<>(); // container for all login info
    List<Customer> customerList;

    Teller t1 = new Teller("JamesP", "pass1");
    Teller t2 = new Teller("KateW", "pass2");

    Manager m1 = new Manager("SamR", "password");

    public IDandPass(List<Customer> customerList){

        this.customerList = customerList;

        // set customer login info
        for(Customer cust : customerList) {
            customerLoginInfo.put(cust.getLoginUsername(), cust.getLoginPassword());
        }

        // set teller login info
        tellerLoginInfo.put(t1.getUsername(), t1.getPassword());
        tellerLoginInfo.put(t2.getUsername(), t2.getPassword());

        // set manager login info
        managerLoginInfo.put(m1.getUsername(), m1.getPassword());

        // add login information to master ArrayList
        loginInfo.add(customerLoginInfo);
        loginInfo.add(tellerLoginInfo);
        loginInfo.add(managerLoginInfo);
    }

    // function for listing out login info
    public void listUsers(){
       customerLoginInfo.forEach((k,v) -> System.out.println("Customer login: " + k + " Password: " + v));
       tellerLoginInfo.forEach((k,v) -> System.out.println("Teller login: " + k + " Password: " + v));
       managerLoginInfo.forEach((k,v) -> System.out.println("Manager login: " + k + " Password: " + v));
    }

    // function for getting login info HashMaps
    public List<HashMap<String,String>> getLoginInfo() {
        return loginInfo;
    }

} // end IDandPass