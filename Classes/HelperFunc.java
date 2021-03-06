package Classes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// this class stores vital program functions necessary for data manipulation and account creation
public class HelperFunc {

    private HelperFunc(){} // private constructor

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

    // creates a new checking account and adds it to checking list
    public static String createCheckingAndAdd(List<Checking> checkingList, String customerID, int accountNum, double balance, double interestRate){
        String message = "";
        Checking accountToAdd = new Checking();
        // make sure account number is unique
        boolean uniqueID = true;
        for(Checking account : checkingList) {
            if (account.getAccountNumber() == accountNum) {
                uniqueID = false;}
        }
        if(uniqueID){
            accountToAdd.setAccountCustID(customerID);
            accountToAdd.setAccountNumber(accountNum);
            accountToAdd.setBalance(balance);
            accountToAdd.setInterestRate(interestRate);
            accountToAdd.balanceCheck();
            accountToAdd.createCard();
            checkingList.add(accountToAdd);
            message = "Account successfully created";
        } else {
            message = "This account number is already taken, please choose another";
        }
        return message;
    } // end createCheckingAndAdd

    // create a new checking account with a backup account and add it to the checking list
    public static String createCheckingAndAddWithBackup(List<Checking> checkingList, String customerID, int accountNum, double balance, double interestRate, int backupAccountNum){
        String message = "";
        Checking accountToAdd = new Checking();
        // make sure account number is unique
        boolean uniqueID = true;
        for(Checking account : checkingList) {
            if (account.getAccountNumber() == accountNum) {
                uniqueID = false;}
        }
        if(uniqueID){
            accountToAdd.setAccountCustID(customerID);
            accountToAdd.setAccountNumber(accountNum);
            accountToAdd.setBackupAccountNumber(backupAccountNum);
            accountToAdd.setBalance(balance);
            accountToAdd.setInterestRate(interestRate);
            accountToAdd.balanceCheck();
            accountToAdd.createCard();
            checkingList.add(accountToAdd);
            if(accountToAdd.isGoldDiamondAccount())
                message = "Gold/Diamond Account successfully created";
            else {
                message = "TMB Account successfully created";
            }
        } else {
            message = "This account number is already taken, please choose another";
        }
        return message;
    } // end createCheckingAndAddWithBackup

    // creates a new savings account and adds it to checking list
    public static String createSavingsAndAdd(List<SavingsAccount> savingsList, String customerID, int accountNum, double balance, double interestRate){
        String message = "";
        SavingsAccount accountToAdd = new SavingsAccount();
        // make sure account number is unique
        boolean uniqueID = true;
        for(SavingsAccount account : savingsList) {
            if (account.getAccountNumber() == accountNum) {
                uniqueID = false;
                message = "This account number is already taken, please choose another";
            }
        }

        if(uniqueID){
            accountToAdd.setAccountCustID(customerID);
            accountToAdd.setAccountNumber(accountNum);
            accountToAdd.setBalance(balance);
            accountToAdd.setInterestRate(interestRate);
            accountToAdd.createCard();
            savingsList.add(accountToAdd);
            message = "Account successfully created";
        }
        return message;
    } // end createSavingsAndAdd
    
    // creates a new CD account and adds it to the cd database
    public static String createCDAndAdd(List<CD> cdList, String customerID, String dueDate, int accountNum, double balance, double interestRate){
        String message = "";
        CD accountToAdd = new CD();
        // make sure account number is unique
        boolean uniqueID = true;
        for(CD account : cdList) {
            if (account.getAccountNumber() == accountNum) {
                uniqueID = false;
                message = "This account number is already tied to an account";
            }
        }
        
        if(uniqueID){
            try {
                // check if date is formatted correctly
                accountToAdd.setDueDate(new SimpleDateFormat("MM/dd/yyyy").parse(dueDate));
                accountToAdd.setDateCreated(new Date());
                accountToAdd.setAccountCustID(customerID);
                accountToAdd.setAccountNumber(accountNum);
                accountToAdd.setBalance(balance);
                accountToAdd.setInterestRate(interestRate);
                cdList.add(accountToAdd);
                message = "CD successfully created.";
            } catch (Exception e) {
                System.out.println("Error in trying to parse date in CD const for createCDAndAdd()");
                message = "Unable to create this account";
                e.printStackTrace();
            }
        }
        return message;
    } // end createCDAndAdd

    // creates a new loan of given type and adds it to the loan database
    public static String createLoanAndAdd(List<Loans> loanList, int loanID, String accountCustID, double balance, double interestRate, String type, int specialInfo){
        String message = "";
        boolean uniqueID = true;
        // check if loanid is unique
        for(Loans loan : loanList){
            if(loan.getLoanID() == loanID){
                uniqueID = false;
                message = "This account number is already taken, please choose another";
            }
        }
        if(uniqueID){
            Loans loanToAdd = new Loans();
            Date dueDate = new Date();
            dueDate.setMonth((dueDate.getMonth()+1)%12);
            dueDate.setHours(0);
            dueDate.setMinutes(0);
            dueDate.setSeconds(0);
            loanToAdd.setLoanID(loanID);
            loanToAdd.setAccountCustID(accountCustID);
            loanToAdd.setBalance(balance);
            loanToAdd.setInterestRate(interestRate);
            loanToAdd.setType(type);
            loanToAdd.setSpecialInfo(specialInfo);
            switch (type)// sets the number of years on a loan and sets loan to true or false based on if it is a loan or credit card
            {
                case "Short Term":
                case "Long Term":
                    loanToAdd.setYears(specialInfo);
                    break;
                case "Credit Card":
                    CreditCard card = new CreditCard(accountCustID, 2, loanID,("2" + loanID + accountCustID.substring(0,2)));
                    loanToAdd.setCard(card);
                    loanToAdd.setLimit(specialInfo);
                    break;
                default: break;
            }
            loanToAdd.calculateMonthlyPayment(type);
            loanToAdd.setDueDate(dueDate);

            message = "Loan successfully created for loanID: " + loanID;
            loanList.add(loanToAdd);
        }
        return message;
    } // end createLoanAndAdd
    
    // function for assigning a backup savings account to a checking account
    public static String assignBackupAccount(List<Checking> checkingList, List<SavingsAccount> savingsList, int accountNum, int backupAccountNum){
        String message = "";
        int c = getChecking(checkingList, accountNum);
        if(c == -1) return "The checking account " + accountNum + " does not exist"; // check if account exists

        int s = getSavings(savingsList, backupAccountNum);
        if(s == -1) return "The savings account " + backupAccountNum + " does not exist"; // check if account exists

        checkingList.get(accountNum).setBackupAccountNumber(backupAccountNum);
        message = "Account " + accountNum + " successfully assigned backup savings acocunt with account number: " + backupAccountNum;
        return message;
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
            System.out.println("ERROR in getCheckingObj, account not found");
            e.printStackTrace();
        }
        return acct;

    }

    
    // returns an index location of the checking acocunt with the given card number
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
    
    // returns an index location of the savings account with the given card number
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
    
    // function for pin verifiaction
    public static boolean pinCheck(ATMCard workingCard, String pin){
        boolean isCorrect = false;
        if(workingCard.getPinNum().equals(pin)) isCorrect = true;
        return isCorrect;
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
    } // end getSavings
    // returns an index location of the cd account with the given account number
    // returns -1 if account does not exist
    public static int getCD(List<CD> cdList, int accountNumber){
        int index = -1;
        for(int i = 0; i<cdList.size(); i++) {
            if(cdList.get(i).getAccountNumber() == accountNumber){
                index = i;
                break;
            }
        }
        return index;
    }

    // returns current balance for given checking account
    public static double getCheckingBalance(List<Checking> checkingList, int accountNum){
        double balance = 0;
        for(int i=0; i<checkingList.size(); i++){
            if(checkingList.get(i).getAccountNumber() == accountNum) {
                balance = checkingList.get(i).getAccountBalance();
                break;
            }
        }
        return balance;
    } // end getCheckingBalance

    // returns balance for given savings account
    public static double getSavingsBalance(List<SavingsAccount> savingsList, int accountNum){
        double balance = 0;
        for(int i=0; i<savingsList.size(); i++){
            if(savingsList.get(i).getAccountNumber() == accountNum) {
                balance = savingsList.get(i).getAccountBalance();
                break;
            }
        }
        return balance;
    } // end getSavingsBalance

    // returns balance for given cd account
    public static double getCDBalance(List<CD> cdList, int accountNum){
        double balance = 0;
        for(int i=0; i<cdList.size(); i++){
            if(cdList.get(i).getAccountNumber() == accountNum) {
                balance = cdList.get(i).getAccountBalance();
                break;
            }
        }
        return balance;
    } // end getCDBalance
    
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
    } // end withdrawSavings

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

    public static void chargeCreditCard(List<Loans> loanList, String cardNumber, double amount){
        for(Loans loan : loanList){
            if(loan.getType().equals("Credit Card")){
                if(loan.getCard().getCardNumber().equals(cardNumber)){
                    loan.withdrawAmt(amount);
                    return;
                }
            }
        }
    } // add a charge to a credit card

    // prints credit card transaction history
    public static void printPurchaseHistory(List<Loans> loanList, String cardNumber){
        for(Loans loan : loanList){
            if(loan.getType().equals("Credit Card")){
                if(loan.getCard().getCardNumber().equals(cardNumber)){
                    loan.getPurchaseHistory().forEach((String s) -> System.out.println(s));
                    return;
                }
            }
        }
    } // print purchase history for a credit card



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
    public static void creditCDAccount(List<CD> cdList, int workingAcctNum, double amount){
        int acctIndex = getCD(cdList, workingAcctNum);
        cdList.get(acctIndex).creditAccount(amount);
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
    public static void debitCDAccount(List<CD> cdList, int workingAcctNum, double amount){
        int acctIndex = getCD(cdList, workingAcctNum);
        cdList.get(acctIndex).withdrawAmt(amount);
    }

    // ------------------------ CREATE AND STOP CHECK FUNCTIONS FOR TELLER/MANAGER ------------------------
    // function for creating local checks
    public static void createCheck(List<Check> checkList, int accountNum, int checkNum, double checkAmount, String workingAcountType) {
        Check checkToAdd = new Check(accountNum, checkNum, checkAmount, workingAcountType);
        checkList.add(checkToAdd);
    } // end createCheck

    // function for creating foreign checks
    public static void createCheck(List<Check> checkList, int accountNum, int routingNum, int checkNum, double checkAmount, int workingAcctNum, String workingAcctType) {
        Check checkToAdd = new Check(accountNum, routingNum, checkNum, checkAmount, workingAcctType);
        checkToAdd.setAccountNumDeposit(workingAcctNum);
        checkToAdd.setIncomingCheck(true);
        checkList.add(checkToAdd);
    } // end createCheck

    // function for stopping a local check
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

    // function for processing checks currently in check queue
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
    public static List<String> accountsLookup(List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, String custID){
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
        for(CD acct : cdList){
            if (acct.getAccountCustID().equals(custID)){
                accounts.add("CD " + acct.getAccountNumber());
            }
        }
        return accounts; // return list of accounts
    } // end accountsLookup

    // returns a list of all accounts in database
    public static List<String> accountsReport(List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList){
        ArrayList<String> accounts = new ArrayList<>();
        for(Checking acct : checkingList){
                accounts.add(acct.toString());
        }
        for(SavingsAccount acct : savingsList){
                accounts.add(acct.toString());
        }
        for(CD acct : cdList){
                accounts.add(acct.toString());
        }
        return accounts; // return list of accounts
    } // end accountsReport

    public static List<String> loansReport(List<Loans> loanList){
        ArrayList<String> accounts = new ArrayList<>();
        for(Loans loan : loanList){
            accounts.add(loan.toString());
        }
        return accounts;
    } // end loansReport

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
    } // end isParsableNumber

    // transfer money between two accounts
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
    } // end transferMoney

} // end HelperFunc