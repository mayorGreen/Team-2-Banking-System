import Classes.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Customer customer = null;
        String file = "Database Files/customer.csv";
        addCustomers(customer, file);


        //IDandPass idAndPasswords = new IDandPass(); // initialize id and passwords --- this can be replaced for simply acct numbers instead
        //LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo()); // generate login page

    }


    public static void addCustomers(Customer customer, String file) throws FileNotFoundException {
        //Reads Customers file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";

            //Skips first line to ignore the column headings
            int lineSkip = 0;

            //iterates through the list to assign them to customer
            while ((line = br.readLine()) != null)
            {
                if (lineSkip != 0) {
                    customer = new Customer(line);
                    //customers.add(customer);
                    customer.listCustomers();
                    customer.listIDandPass();
                }
                lineSkip++;
            }
            customer.loginPage();
        } catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
    }
}