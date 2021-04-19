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
        IDandPass idAndPasswords = new IDandPass(); // initialize id and passwords --- this can be replaced for simply acct numbers instead
        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo()); // generate login page
        Parser p1 = new Parser("./Database Files/cds.csv");
        Parser p2 = new Parser("./Database Files/checking.csv");
        Parser p3 = new Parser("./Database Files/customer.csv");
        Parser p4 = new Parser("./Database Files/loans.csv");
        Parser p5 = new Parser("./Database Files/savings.csv");
        List<List<String>> cds = p1.getRecords();
        List<List<String>> checkingAccounts = p2.getRecords();
        List<List<String>> customers = p3.getRecords();
        List<List<String>> loans = p4.getRecords();
        List<List<String>> savingsAccounts = p5.getRecords();

        // create the customer objects and add them to customerList
        List<Customer> customerList = new ArrayList<>();
        for(int i=1; i<customers.size(); i++) {
            // start at i=1, create new anonymous customer obj, add to array
            customerList.add(new Customer(customers.get(i)));
        }
    

    }

    //getCustomers method that reads in the customer file and assigns it to a customer class
    static void getCustomers() {
        String file = "Database Files/customer.csv";
        List<Customer> customers = new ArrayList<>();
        List<IDandPass> ids = new ArrayList<>();
        IDandPass idandpass = new IDandPass();

        //Reads Customers file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";

            //Skips first line to ignore the column headings
            int lineSkip = 0;

            //iterates through the list to assign them to customer
            /*while ((line = br.readLine()) != null) {
                if (lineSkip != 0) {
                    Customer customer = new Customer(line);
                    customers.add(customer);
                    customer.listCustomers();
                    ids.add(new IDandPass(customer.getUserName(), customer.getPassword()));

                }
                lineSkip++;
            }*/

            //debugging prints to show that ids and customers are being imported to their arrayLists
            //customers.get(0).listCustomers();
            //ids.get(4).listUsers();

            /*
            //If you uncomment this and run it it should help you understand what is happening.
            //with this code we are creating a new instance for every single login that we are writing.
            for(int i = 0; i<ids.size(); i++)
            {
                LoginPage loginPage = new LoginPage(ids.get(i).getLoginInfo());
            }

             */

            //this code works to allow a single login based on the position defined in the arrayList
            //LoginPage loginPage = new LoginPage(ids.get(1).getLoginInfo());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}