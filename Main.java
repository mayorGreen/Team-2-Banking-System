import Classes.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


class Main{

    public static void main(String[] args) {

        /** This is the beginning of the program, it's going to check for database data
         * and import it into the program, however, the presence of object files implies that
         * newer data is available, and the program will prefer to import these files instead. 
        */ 

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

        //HelperFunc.updateCustomers(customerList);

        // data manip demoo
        System.out.println("checking balance for first account");
        System.out.println("Balance: " + checkingList.get(0).getAccountBalance());
        System.out.println("Depositing $3");
        checkingList.get(0).depositAmt(3);
        System.out.println("New Balance is: " + checkingList.get(0).getAccountBalance());
        System.out.println("Updating System");
        HelperFunc.updateChecking(checkingList);
        IDandPass idAndPasswords = new IDandPass(customerList); // initialize id and passwords --- this can be replaced for simply acct numbers instead
        //idAndPasswords.listUsers(); // debug
        
        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo(),customerList, checkingList, savingsList, cdList, loanList); // generate login page
        System.out.println(customerList.get(0).toString()); // print first cust

        /* // withdrawCheckingWithbackup test - works!
        HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, 1, 300);
        HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, 1, 1000);
        HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, 1, 20);

        // withdrawSavings Test - works!
        HelperFunc.withdrawSavings(savingsList, 1, 20);
        HelperFunc.withdrawSavings(savingsList, 1, 20);
        HelperFunc.withdrawSavings(savingsList, 1, 20);
        */
    } // end main method
} // end Main class