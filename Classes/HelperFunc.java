package Classes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// some helper functions vital for system functionality
public class HelperFunc {

    private HelperFunc(){}

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
                break;
            }
        }
    }
    
    
    // update functions for saving changed data -- call these after every change in data
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
    public static void updateChecks(List<Check> checkList){
        Parser.writeObjectRecords(checkList, "checkObj.txt");
    }
    public static void updateDate(List<Checking> checkingList, List<SavingsAccount> savingsList){
        Date today = new Date();
        for(Checking acct : checkingList) {
            acct.setTodaysDate(today);
        }
        for(SavingsAccount acct : savingsList) {
            acct.setTodaysDate(today);
        }
    }
    
    // finds and returns checking account object from list given acct num
    public static Checking getCheckingObj(List<Checking> checkingList, int accountNum) throws NullPointerException{
        Checking acct = null;
        try {
            for(int i=0; i<checkingList.size(); i++){
                if(checkingList.get(i).getAccountNumber() == accountNum) acct = checkingList.get(i);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("ERROR in getCheckingObj, account not found");
            e.printStackTrace();
        }
        return acct;

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

    // finds card if exists, returns index of card in list
    public static int findCardChecking(List<Checking> checkingList, String cardNum){
        int cardIndex = -1;
        for(int i=0; i<checkingList.size(); i++){
            if(checkingList.get(i).getCard().getCardNumber().equals(cardNum)){
                 cardIndex = i;
                break;
            }
        }
        return cardIndex;
    }

    public static int findCardSavings(List<SavingsAccount> savingsList, String cardNum){
        int cardIndex = -1;
        for(int i=0; i<savingsList.size(); i++){
            if(savingsList.get(i).getCard().getCardNumber().equals(cardNum)){
                 cardIndex = i;
                break;
            }
        }
        return cardIndex;
    }

    public static boolean pinCheck(ATMCard workingCard, String pin){
        boolean isCorrect = false;
        if(workingCard.getPinNum().equals(pin)) isCorrect = true;
        return isCorrect;
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

    public static double getCheckingBalance(List<Checking> checkingList, int accountNum){
        double balance = 0;
        for(int i=0; i<checkingList.size(); i++){
            if(checkingList.get(i).getAccountNumber() == accountNum) {
                balance = checkingList.get(i).getAccountBalance();
                break;
            }
        }
        return balance;
    }

    public static double getSavingsBalance(List<SavingsAccount> savingsList, int accountNum){
        double balance = 0;
        for(int i=0; i<savingsList.size(); i++){
            if(savingsList.get(i).getAccountNumber() == accountNum) {
                balance = savingsList.get(i).getAccountBalance();
                break;
            }
        }
        return balance;
    }

    // this method withdraws the requested amount from the given savings account
    // the date is checked to ensure that money is withdrawn a maximum amount of 2 times per day
    public static void withdrawSavings(List<SavingsAccount> savingsList, int accountNum, double withdrawAmt){
        System.out.println("Entering withdrawSavings for acct num: " + accountNum);
        int savingsIndex = getSavings(savingsList, accountNum);
        System.out.println("Today's date is: " + new Date());
        System.out.println("Last withdrawal date is " + savingsList.get(savingsIndex).getLastWithdrawal().getDate());
        // check if new day
        if((new Date().getDate()) != (savingsList.get(savingsIndex).getLastWithdrawal().getDate())){
            System.out.println("Withdrawals today: " + savingsList.get(savingsIndex).getWithdrawalsToday());
            savingsList.get(savingsIndex).setWithdrawalsToday(0);
        }

        // check if at max withdrawals today
        if(savingsList.get(savingsIndex).getWithdrawalsToday() == 2){
            System.out.println("Maximum amount of withdrawals today");
        } else {
            // check if withdraw amount is greater than what's in account
            if(withdrawAmt > savingsList.get(savingsIndex).getAccountBalance()){
                System.out.println("Amount requested greater than what is in account");
            } else {
                // withdraw requested amount, increment withdrawals today, set last withdrawal date
                savingsList.get(savingsIndex).withdrawAmt(withdrawAmt);
                savingsList.get(savingsIndex).incrementWithdrawals(); // increment withdrawals today
                savingsList.get(savingsIndex).setLastWithdrawal(new Date());
            }
        }
    }

    public static void depositToChecking(List<Checking> checkingList, int accountNum, double depositAmount){
        int accountIndex = getChecking(checkingList, accountNum);
        checkingList.get(accountIndex).depositAmt(depositAmount);
    }
    public static void depositToSavings(List<SavingsAccount> savingsList, int accountNum, double depositAmount){
        int accountIndex = getSavings(savingsList, accountNum);
        savingsList.get(accountIndex).depositAmt(depositAmount);
    }


    // this method withdraws an amount from a checking account and does multiple checks for backup accounts and overdraft protection
    public static void withdrawCheckingWithSafety(List<Checking> checkingList, List<SavingsAccount> savingsList, int accountNum, double withdrawAmt){
        double amountToWithdraw = withdrawAmt; // amount remaining to be withdrawn
        int accountIndex = getChecking(checkingList, accountNum); // index of account in list
        int savingsIndex = -1;
        Checking workingAccount = getCheckingObj(checkingList, accountNum); // dummy account for extracting data
        if(workingAccount == null) {
            // check for null pointer
            System.out.println("Error in withdrawCheckingWithSafety. Account obj could not be found");
            return;
        }
        double currentBalance = workingAccount.getAccountBalance(); // current balance in checking
        boolean hasBackupAccount = workingAccount.hasBackupAccount(); // value for if account has backup
        int backupAccountNumber; // account number of savings backup

        // if account has a backup, set variables
        if(hasBackupAccount) {
            backupAccountNumber = workingAccount.getBackupAccountNumber(); // set backup acct num
            savingsIndex = getSavings(savingsList, backupAccountNumber);
        }
        
        // amount requested to withdraw is less than current account balance
        if (amountToWithdraw <= currentBalance){
            checkingList.get(accountIndex).withdrawAmt(amountToWithdraw); // directly manipulate account
        }

        // amount requested to withdraw is greater than what is in the account
        // account has a savings backup tied to it
        if (amountToWithdraw > currentBalance && hasBackupAccount){
            amountToWithdraw -= currentBalance;
            checkingList.get(accountIndex).setBalance(0); // set account balance to $0.00
            savingsList.get(savingsIndex).withdrawAmt(amountToWithdraw); // pull remaining amount from backup acct
        }

        // amount requested to withdraw is greater than what is in the account
        // account has NO backup
        if (amountToWithdraw > currentBalance && !hasBackupAccount) {
            checkingList.get(accountIndex).withdrawAmt(amountToWithdraw);
        }
    } // end withdrawCheckingWithSafety




    // These functions can be used by teller and manager

    public static void creditAccount(String custID, Checking account, double amount) {
        account.creditAccount(amount);
    }
    public static void creditAccount(String custID, SavingsAccount account, double amount) {
        account.creditAccount(amount);
    }
    public static void createCheck(List<Check> checkList, String accountNum, String checkNum, String checkAmount) {
        checkList.add(new Check(Integer.parseInt(accountNum), Integer.parseInt(checkNum), Double.parseDouble(checkAmount)));
    }
    public static void stopCheck(List<Check> checkList, String accountNumber, String checkNumber){
        // stops check
        int accountNum = Integer.parseInt(accountNumber);
        int checkNum = Integer.parseInt(checkNumber);
        for(Check check : checkList){
            if(check.getAccountNum() == accountNum && check.getCheckNum() == checkNum){
                check.setCheckStopped(true);
                return;
            }
        }

    } // end stopCheck

    public static List<String> accountsLookup(List<Checking> checkingList, List<SavingsAccount> savingsList, String custID){
        ArrayList<String> accounts = new ArrayList<>();
        for(Checking acct : checkingList){
            if (acct.getAccountCustID().equals(custID)){
                accounts.add("Checking " + acct.getAccountNumber());
            }
        }
        for(SavingsAccount acct : savingsList){
            if (acct.getAccountCustID().equals(custID)){
                accounts.add("Savings " + acct.getAccountNumber());
            }
        }
        return accounts; // return list of accounts
    }




} // end HelperFunc
