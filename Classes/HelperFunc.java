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

    // functions for printing data as plaintext
    public static <T> void printList(List<T> list) {
        for(T obj : list) System.out.println(obj);
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
    // DEPOSIT FUNCTIONS FOR CUSTOMERS
    // used by customers via atm, accounts for implicit transaction fees
    public static void depositToChecking(List<Checking> checkingList, int accountNum, double depositAmount){
        int accountIndex = getChecking(checkingList, accountNum);
        checkingList.get(accountIndex).depositAmt(depositAmount);
    }
    public static void depositToSavings(List<SavingsAccount> savingsList, int accountNum, double depositAmount){
        int accountIndex = getSavings(savingsList, accountNum);
        savingsList.get(accountIndex).depositAmt(depositAmount);
    }

    // WITHDRAW FUNCTIONS FOR CUSTOMERS
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
        // TODO: MOVE THIS TO CHECKING TOO
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



    // this method withdraws an amount from a checking account and does multiple checks for backup accounts and overdraft protection
    public static void withdrawCheckingWithSafety(List<Checking> checkingList, List<SavingsAccount> savingsList, int accountNum, double withdrawAmt){
        // TODO: ADD WITHDRAWAL CHECK
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



    // CREDIT FUNCTIONS FOR TELLER/MANAGER
    // These functions can be used by teller and manager
    // used by tellers and managers via banking panel. Differs from previous methods in that it ignores transaction fees
    public static void creditCheckingAccount(List<Checking> checkingList, int workingAcctNum, double amount) {
        int acctIndex = getChecking(checkingList, workingAcctNum);
        checkingList.get(acctIndex).creditAccount(amount);
    }
    public static void creditSavingsAccount(List<SavingsAccount> savingsList, int workingAcctNum, double amount) {
        int acctIndex = getSavings(savingsList, workingAcctNum);
        savingsList.get(acctIndex).creditAccount(amount);
    }

    // DEBIT FUNCTIONS FOR TELLER/MANAGER
    public static void debitCheckingAccount(List<Checking> checkingList, int workingAcctNum, double amount) {
        int acctIndex = getChecking(checkingList, workingAcctNum);
        checkingList.get(acctIndex).debitAccount(amount);
    }
    public static void debitSavingsAccount(List<SavingsAccount> savingsList, int workingAcctNum, double amount) {
        int acctIndex = getSavings(savingsList, workingAcctNum);
        savingsList.get(acctIndex).debitAccount(amount);
    }

    // CREATE AND STOP CHECK FUNCTIONS FOR TELLER/MANAGER
    public static void createCheck(List<Check> checkList, int accountNum, int checkNum, double checkAmount, String workingAcountType) {
        Check checkToAdd = new Check(accountNum, checkNum, checkAmount, workingAcountType);
        checkList.add(checkToAdd);
    }
    public static void createCheck(List<Check> checkList, int accountNum, int routingNum, int checkNum, double checkAmount, int workingAcctNum, String workingAcctType) {
        Check checkToAdd = new Check(accountNum, routingNum, checkNum, checkAmount, workingAcctType);
        checkToAdd.setAccountNumDeposit(workingAcctNum);
        checkToAdd.setIncomingCheck(true);
        checkList.add(checkToAdd);
    }
    public static void stopCheck(List<Check> checkList, int accountNumber, String checkNumber, String accountType){
        // stops check
        int accountNum = accountNumber;
        int checkNum = Integer.parseInt(checkNumber);
        for(Check check : checkList){
            if(check.getAccountNum() == accountNum && check.getCheckNum() == checkNum && check.getRoutingNum() == 123456789 && check.getAccountType().equals(accountType)){
                check.setCheckStopped(true);
                return;
            }
        }

    } // end stopCheck

    public static void processChecks(List<Check> checkList, List<Checking> checkingList, List<SavingsAccount> savingsList){
        for(Check check : checkList){
            // first, check if check is stopped
            System.out.println("Processing Check");
            if(!check.isCheckStopped()){
                System.out.println("Check is not stopped, processing check");
                // next determine if check is coming in or going out
                if(check.isIncomingCheck()){ // check is coming in, credit this customer's account
                    if(check.getAccountTypeDeposit().equals("Checking")){
                        creditCheckingAccount(checkingList, check.getAccountNumDeposit(), check.getCheckAmount());
                    } else if (check.getAccountType().equals("Savings")){
                        creditSavingsAccount(savingsList, check.getAccountNumDeposit(), check.getCheckAmount());
                    }
                } else { // check is outgoing, charge this customer's account
                    if(check.getAccountType().equals("Checking")){
                        debitCheckingAccount(checkingList, check.getAccountNum(), check.getCheckAmount());
                    } else if(check.getAccountType().equals("Savings")){
                        debitSavingsAccount(savingsList, check.getAccountNum(), check.getAccountNum());
                    }
                }
            } else {
                // check is stopped, charge this customer $15 fee
                if(check.isCheckStopped() && !check.isIncomingCheck()){
                    System.out.println("Check is stopped, charging customer fee");
                    if(check.getAccountType().equals("Checking")){
                        debitCheckingAccount(checkingList, check.getAccountNum(), 15.00);
                    } else if (check.getAccountType().equals("Savings")){
                        debitSavingsAccount(savingsList, check.getAccountNum(), 15.00);
                    }
                }
            }
        } // end for loop
        System.out.println("Check Processing Complete");
    } // end proccessChecks


    // Account lookup process for teller and manager pages
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

    // pass in string, determine if it's a parsable value
    public static boolean isParsableNumber(String str){
        boolean parsable = true;
        int decimalPoints = 0;
        for(char character : str.toCharArray()){
            if (character == '.') decimalPoints++;
            if (!(character >= '0' && character <= '9' || character == '.')) {
                System.out.println("incompatible character");
                parsable = false;
            }
        }
        if (decimalPoints > 1) parsable = false;
        return parsable;
    }


    public static String transferMoney(List<Checking> checkingList, List<SavingsAccount> savingsList, 
    String fromAccountType, int fromAccountNum, String toAccountType, int toAccountNum, double transferAmount){
        String confirmationMessage = "";
        int accIndex1;
        int accIndex2;

        if(fromAccountType.equals("Checking") && toAccountType.equals("Checking")){ // transfer from a checking to checking
            // find accounts
            accIndex1 = getChecking(checkingList, fromAccountNum);
            accIndex2 = getChecking(checkingList, toAccountNum);
            if(checkingList.get(accIndex1).getAccountBalance() < transferAmount){
                confirmationMessage = fromAccountType + " account " + fromAccountNum + " does not have enough funds to cover this transaction.";
            } else {
                checkingList.get(accIndex1).debitAccount(transferAmount); //take money out of source account
                checkingList.get(accIndex2).creditAccount(transferAmount); // put money into destination account
                confirmationMessage = "Transfer of $" + transferAmount + " from " + fromAccountType + " account " + fromAccountNum + " to " + toAccountType + " account " + toAccountNum + " complete.";
            }

        } else if(fromAccountType.equals("Checking") && toAccountType.equals("Savings")){ // transfer from a checking to savings
            accIndex1 = getChecking(checkingList, fromAccountNum);
            accIndex2 = getSavings(savingsList, toAccountNum);
            if(checkingList.get(accIndex1).getAccountBalance() < transferAmount){
                confirmationMessage = fromAccountType + " account " + fromAccountNum + " does not have enough funds to cover this transaction.";
            } else {
                checkingList.get(accIndex1).debitAccount(transferAmount); //take money out of source account
                savingsList.get(accIndex2).creditAccount(transferAmount); // put money into destination account
                confirmationMessage = "Transfer of $" + transferAmount + " from " + fromAccountType + " account " + fromAccountNum + " to " + toAccountType + " account " + toAccountNum + " complete.";
            }

        } else if(fromAccountType.equals("Savings") && toAccountType.equals("Checking")){ // transfer from a savings to checking
            accIndex1 = getSavings(savingsList, fromAccountNum);
            accIndex2 = getChecking(checkingList, toAccountNum);
            if(savingsList.get(accIndex1).getAccountBalance() < transferAmount){
                confirmationMessage = fromAccountType + " account " + fromAccountNum + " does not have enough funds to cover this transaction.";
            } else {
                savingsList.get(accIndex1).debitAccount(transferAmount); //take money out of source account
                checkingList.get(accIndex2).creditAccount(transferAmount); // put money into destination account
                confirmationMessage = "Transfer of $" + transferAmount + " from " + fromAccountType + " account " + fromAccountNum + " to " + toAccountType + " account " + toAccountNum + " complete.";
            }

        } else if(fromAccountType.equals("Savings") && toAccountType.equals("Savings")){ // transfer from a savings to savings
            accIndex1 = getSavings(savingsList, fromAccountNum);
            accIndex2 = getSavings(savingsList, toAccountNum);
            if(savingsList.get(accIndex1).getAccountBalance() < transferAmount){
                confirmationMessage = fromAccountType + " account " + fromAccountNum + " does not have enough funds to cover this transaction.";
            } else {
                savingsList.get(accIndex1).debitAccount(transferAmount); //take money out of source account
                savingsList.get(accIndex2).creditAccount(transferAmount); // put money into destination account
                confirmationMessage = "Transfer of $" + transferAmount + " from " + fromAccountType + " account " + fromAccountNum + " to " + toAccountType + " account " + toAccountNum + " complete.";
            }
        }
        
        System.out.println(confirmationMessage);
        return confirmationMessage;
    }

} // end HelperFunc
