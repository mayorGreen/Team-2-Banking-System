import javax.swing.*;
import Classes.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Main {
    public static void main(String[] args) {
        getCustomers();
        //IDandPass idAndPasswords = new IDandPass(); // initialize id and passwords --- this can be replaced for simply acct numbers instead
        //LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo()); // generate login page


    }

    //getCustomers method that reads in the customer file and assigns it to a customer class
    static void getCustomers() {
        String file = "Database Files/customer.csv";
        List<Customer> customers = new ArrayList<>();
        IDandPass idandpass = new IDandPass();

        //Reads Customers file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";

            //Skips first line to ignore the column headings
            int lineSkip = 0;

            //iterates through the list to assign them to customer
            while ((line = br.readLine()) != null) {
                if (lineSkip != 0) {
                    Customer customer = new Customer(line);
                    customers.add(customer);
                    customer.listCustomers();
                    idandpass = new IDandPass(customer.getUserName(), customer.getPassword());

                }
                lineSkip++;
            }
            customers.get(0).listCustomers();
            LoginPage loginPage = new LoginPage(idandpass.getLoginInfo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}