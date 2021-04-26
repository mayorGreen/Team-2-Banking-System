package Classes;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class IDandPass {
    HashMap<String,String> customerLoginInfo = new HashMap<>();
    HashMap<String,String> tellerLoginInfo = new HashMap<>();
    HashMap<String,String> managerLoginInfo = new HashMap<>();
    ArrayList<HashMap<String,String>> loginInfo = new ArrayList<>();


    List<Customer> customerList;

    Teller t1 = new Teller("JamesP", "pass1");
    Teller t2 = new Teller("KateW", "pass2");

    Manager m1 = new Manager("SamR", "password");

    public IDandPass(List<Customer> customerList){
        this.customerList = customerList;

        for(Customer cust : customerList) {
            customerLoginInfo.put(cust.getLoginUsername(), cust.getLoginPassword());
        }


        tellerLoginInfo.put(t1.getUsername(), t1.getPassword());
        tellerLoginInfo.put(t2.getUsername(), t2.getPassword());
        managerLoginInfo.put(m1.getUsername(), m1.getPassword());

        customerLoginInfo.put("a", "a");
        tellerLoginInfo.put("a", "a");
        managerLoginInfo.put("a", "a");

        // add login information to master array
        loginInfo.add(customerLoginInfo);
        loginInfo.add(tellerLoginInfo);
        loginInfo.add(managerLoginInfo);
    }

    
    public void listUsers(){
       customerLoginInfo.forEach((k,v) -> System.out.println("Customer login: " + k + " Password: " + v));
       tellerLoginInfo.forEach((k,v) -> System.out.println("Teller login: " + k + " Password: " + v));
       managerLoginInfo.forEach((k,v) -> System.out.println("Manager login: " + k + " Password: " + v));
    }

    public List<HashMap<String,String>> getLoginInfo() {
        return loginInfo;
    }
}
