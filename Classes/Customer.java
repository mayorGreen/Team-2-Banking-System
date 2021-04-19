package Classes;

public class Customer {

    //customer username getter
    public String getUserName() {
        return userName;
    }

    public void loginPage()
    {
        ids.listUsers();
        LoginPage loginPage = new LoginPage(ids.getLoginInfo());
    }

    public void listIDandPass()
    {
       ids.listUsers();
    }

    //prints all customers
    public void listCustomers()
    {
        System.out.println(ssn+" "+ street + " "+ city +" "+ state+" "+ zip+" "+ firstName +" " +lastName+" "+password+" "+userName);
    }

}
