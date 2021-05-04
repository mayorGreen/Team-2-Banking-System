import Classes.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** This is the beginning of the program, it's going to check for database data
 * and import it into the program, however, the presence of object files implies that
 * newer data is available, and the program will prefer to import these files instead. 
*/ 
class Main{
    public static void main(String[] args) {

        // initialize csv file string lists
        List<List<String>> customers;
        List<List<String>> checkingAccounts;
        List<List<String>> savingsAccounts;
        List<List<String>> cds;
        List<List<String>> loans;
    
        // initialize object lists
        List<Customer> customerList;
        List<Checking> checkingList;
        List<SavingsAccount> savingsList;
        List<CD> cdList;
        List<Loans> loanList;
        List<Check> checkList;

        // -------------- Begin reading in data --------------

        // check if no prior record for customers exists
        if(!(new File("customerObj.txt").exists())){
            // get data from csv file
            customers = Parser.getRecords("./Database Files/customer.csv");

            // create the customer objects and add them to customerList
            customerList = new ArrayList<>();
            for(int i=1; i<customers.size(); i++) {
                // start at i=1, create new anonymous customer obj, add to array
                customerList.add(new Customer(customers.get(i)));
            }
        } else {
            // import records
            customerList = Parser.readObjectRecords("customerObj.txt");
        }


        // check if no prior record for checking accounts exists
        if(!(new File("checkingAccObj.txt").exists())){
            // get data from the csv file
            checkingAccounts = Parser.getRecords("./Database Files/checking.csv");

            // create the checking account objects and add them to checkingList
            checkingList = new ArrayList<>();
            for(int i=1; i<checkingAccounts.size(); i++) {
                // start at i=1, create new anonymous Checking obj, add to array
                checkingList.add(new Checking(checkingAccounts.get(i)));
            }
        } else {
            // import records
            checkingList = Parser.readObjectRecords("checkingAccObj.txt");
        }

        // check if no prior record for savings accounts exists
        if(!(new File("savingsAccObj.txt").exists())){
            // get data from the csv file
            savingsAccounts = Parser.getRecords("./Database Files/savings.csv");

            savingsList = new ArrayList<>();
            for(int i=1; i<savingsAccounts.size(); i++) {
                // start at i=1, create new anonymous Checking obj, add to array
                savingsList.add(new SavingsAccount(savingsAccounts.get(i)));
            }
        } else {
            // import records
            savingsList = Parser.readObjectRecords("savingsAccObj.txt");
        }

        // check if no prior record for cds exists
        if(!(new File("cdsObj.txt").exists())){
            // get data from the csv file
            cds = Parser.getRecords("./Database Files/cds.csv");

            cdList = new ArrayList<>();
            for(int i=1; i<cds.size(); i++) {
                // start at i=1, create new anonymous Checking obj, add to array
                cdList.add(new CD(cds.get(i)));
            }
        } else {
            // import records
            cdList = Parser.readObjectRecords("cdsObj.txt");
        }

        if(!(new File("loansObj.txt").exists())){
            loans = Parser.getRecords("./Database Files/loans.csv");

            loanList = new ArrayList<>();
            for(int i=1; i<loans.size(); i++) {
                // start at i=1, create new anonymous Checking obj, add to array
                loanList.add(new Loans(loans.get(i)));
            }
        } else {
            loanList = Parser.readObjectRecords("loansObj.txt");
        }

        if(new File("checkObj.txt").exists()){
            checkList = Parser.readObjectRecords("checkObj.txt");
        } else {
            checkList = new ArrayList<>();
        }
        
        // initialize IDs and Passwords
        IDandPass idAndPasswords = new IDandPass(customerList);
        
        // initialize login page -- UI starts here
        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo(),customerList, checkingList, savingsList, cdList, loanList,checkList); // generate login page

        /* 
        //debug print log
        System.out.println("Printing customers");
        HelperFunc.printList(customerList);
        System.out.println("\nPrinting Checking Accounts");
        HelperFunc.printList(checkingList);
        System.out.println("\nPrinting Savings Accounts");
        HelperFunc.printList(savingsList);
        System.out.println("\nPrinting CDs");
        HelperFunc.printList(cdList);
        System.out.println("\nPrinting loans");
        HelperFunc.printList(loanList);
        */

        /*
        // credit card test
        HelperFunc.chargeCreditCard(loanList, "2342", 5000);
        HelperFunc.updateLoans(loanList);
        
        HelperFunc.chargeCreditCard(loanList, "2342", 1000);
        HelperFunc.updateLoans(loanList);
        
        HelperFunc.chargeCreditCard(loanList, "2342", 1000);
        HelperFunc.updateLoans(loanList);
        
        HelperFunc.chargeCreditCard(loanList, "2342", 5000);
        HelperFunc.updateLoans(loanList);
        
        HelperFunc.chargeCreditCard(loanList, "2342", 3000);
        HelperFunc.updateLoans(loanList);
        
        HelperFunc.chargeCreditCard(loanList, "2342", 3000);
        HelperFunc.updateLoans(loanList);
        
        HelperFunc.chargeCreditCard(loanList, "2342", 3000);
        HelperFunc.updateLoans(loanList);
        
        System.out.println("\nPrinting loans");
        HelperFunc.printList(loanList);
        
        HelperFunc.printPurchaseHistory(loanList, "2342");
        */
             
        // check demo
        // Simulate Customer Ronald Jones writing checks
        //HelperFunc.createCheck(checkList, 1, 1, 50.00, "Checking");
        //HelperFunc.createCheck(checkList, 1, 2, 80.00, "Checking");
        //HelperFunc.createCheck(checkList, 1, 3, 100.00, "Checking");

        //System.out.println("\nPrinting Checks");
        //HelperFunc.printList(checkList);

    } // end main method
} // end Main class