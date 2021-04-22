package Classes;

import java.util.List;

// some helper functions vital for system functionality
public class HelperFunc {

    // creates a new customer object
    public static Customer createCustomer(String ssn, String streetAddress, String city, String state, String zip, String firstName, String lastName) {
        return new Customer(ssn,streetAddress,city,state,zip,firstName,lastName);
    }

    // creates a new customer object and inserts it into given customer list
    public static void createCustomerAndAdd(List<Customer> custList, String ssn, String streetAddress, String city, String state, String zip, String firstName, String lastName){
        Customer workingCustomer = new Customer(ssn,streetAddress,city,state,zip,firstName,lastName);
        custList.add(workingCustomer);
    }

    // takes list of customers, searches list for matching customer id, removes customer
    public static void deleteCustomer(List<Customer> custList, String customerID){
        for(int i=0; i<custList.size(); i++){
            if(custList.get(i).getSsn().equals(customerID)){
                custList.remove(i);
            }
        }
    }


    // update functions for saving changed data call these after every change in data
    // TODO: Make these thread safe later
    public static void updateCustomers(List<Customer> custList){
        Parser.writeObjectRecords(custList, "customerObj.txt");
    }
    public static void updateChecking(List<Checking> checkingList){
        Parser.writeObjectRecords(checkingList, "checkingAccObj.txt");
    }
    public static void updateSavings(List<SavingsAccount> savingsList){
        Parser.writeObjectRecords(savingsList, "savingsAccObj.txt");
    }
    public static void updateCD(List<CD> cdList){
        Parser.writeObjectRecords(cdList, "cdsObj.txt");
    }
    public static void updateLoans(List<Loans> loanList){
        Parser.writeObjectRecords(loanList, "loansObj.txt");
    }

    // returns an index location of the checking account with the given account number
    // returns -1 if account does not exist
    public static int getChecking(List<Checking> checkingList, int accountNumber){
        int index = -1;
        for(int i = 0; i<checkingList.size(); i++) {
            if(checkingList.get(i).getAccountNumber() == accountNumber){
                index = i;
                break;
            }
        }
        return index;
    }

    // returns an index location of the savings account with the given account number
    // returns -1 if account does not exist
    public static int getSavings(List<SavingsAccount> savingsList, int accountNumber){
        int index = -1;
        for(int i = 0; i<savingsList.size(); i++) {
            if(savingsList.get(i).getAccountNumber() == accountNumber){
                index = i;
                break;
            }
        }
        return index;
    }


    // These functions can be used by teller and manager

    public static void creditAccount(String custID, Checking account, double amount) {
        account.creditAccount(amount);
    }
    public static void creditAccount(String custID, SavingsAccount account, double amount) {
        account.creditAccount(amount);
    }
    public static void stopCheck(int accountNumber, int checkNumber){
        // stops check
    }

} // end HelperFunc
