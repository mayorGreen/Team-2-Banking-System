import javax.swing.*;
import Classes.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


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

        // TODO: create lists of all accts

        List<SavingsAccount> savingsList = new ArrayList<>();
        for(int i=1; i<savingsAccounts.size(); i++) {
            savingsList.add(new SavingsAccount(savingsAccounts.get(i)));
        }

        System.out.println(customerList.get(0).toString()); // print first cust

        /* // customer object write test
        try (FileOutputStream fout = new FileOutputStream(new File("customerObj.txt"))){
            
            ObjectOutputStream oout = new ObjectOutputStream(fout);

            // write customer objects
            oout.writeObject(customerList.get(0));
            System.out.println("Customer object written to file!");

            oout.close();
            fout.close();

            System.out.println("Now attempting to read in customer file");

            FileInputStream fin = new FileInputStream(new File("customerObj.txt"));
            ObjectInputStream oin = new ObjectInputStream(fin);

            Customer readCustomer = (Customer)oin.readObject();
            System.out.println("customer obj read. now printing customer");
            System.out.println(readCustomer.toString());

            oin.close();
            fin.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in obj output stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

        // customer list write test

        try (FileOutputStream fout = new FileOutputStream(new File("customerObj.txt"))){
            
            ObjectOutputStream oout = new ObjectOutputStream(fout);

            // write customer objects
            oout.writeObject(customerList);
            System.out.println("Customer list written to file!");

            oout.close();
            fout.close();

            System.out.println("Now attempting to read in customer file");

            FileInputStream fin = new FileInputStream(new File("customerObj.txt"));
            ObjectInputStream oin = new ObjectInputStream(fin);

            //List<Customer> readCustomer = new ArrayList<>();
            //readCustomer = (ArrayList<Customer>)oin.readObject();

            ArrayList<Customer> readCustomer = (ArrayList)oin.readObject();
            System.out.println("customer list read. now printing customer 1");
            System.out.println(readCustomer.get(0));

            readCustomer.add(new Customer("1234","asdf","asdf","asdf","asdf","asdf","asdf"));
            System.out.println(readCustomer.get(readCustomer.size()-1));

            oin.close();
            fin.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in obj output stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // write savings accounts to file
        try (FileOutputStream fout = new FileOutputStream(new File("savingsAccObj.txt"))){
            
            ObjectOutputStream oout = new ObjectOutputStream(fout);

            // write customer objects
            oout.writeObject(savingsList);
            System.out.println("savings list written to file!");

            oout.close();
            fout.close();

            System.out.println("Now attempting to read in savings account file");

            FileInputStream fin = new FileInputStream(new File("savingsAccObj.txt"));
            ObjectInputStream oin = new ObjectInputStream(fin);


            ArrayList<SavingsAccount> readSavingsAccounts = (ArrayList)oin.readObject();
            System.out.println("savings account list read. now printing account 1");
            System.out.println(readSavingsAccounts.get(0));

            readSavingsAccounts.add(new SavingsAccount());
            System.out.println(readSavingsAccounts.get(readSavingsAccounts.size()-1));

            oin.close();
            fin.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error in obj output stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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