package Classes;

import java.util.List;

public class HelperFunc {

    public static Customer createCustomer(String ssn, String streetAddress, String city, String state, String zip, String firstName, String lastName) {
        return new Customer(ssn,streetAddress,city,state,zip,firstName,lastName);
    }

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

}
