package Classes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.util.List;

// Checking account class
public class Checking extends Account
{
    private boolean goldDiamondAccount; // raised if account balance > $1000.00
    private double interestRate; // interest rate for account, only goldDiamond accounts accrue interest
    private int nextCheckNumber; // next check number associated with account
    private boolean hasBackupAccount; // raised if account has backup account associated for overdrdaft protection
    private int backupAccountNumber; // account number of backup account
    private int numOfOverdrafts; // number of overdrafts logged on account
    private Date dateCreated; // date account created
    private Date todaysDate; // today's date --- needed for interest purposed on GD account
    private ATMCard card; // ATM card associated with the account

    // generic constructor
    public Checking(){
        super();
        goldDiamondAccount = false;
        interestRate = 0;
        nextCheckNumber = 1;
        hasBackupAccount = false;
        numOfOverdrafts = 0;
        dateCreated = new Date();
        todaysDate = new Date();
    }

    // Constructor
    public Checking(List<String> acct){
        super();
        goldDiamondAccount = false;
        hasBackupAccount = false;
        interestRate = 0;
        nextCheckNumber = 1;
        accountCustID = acct.get(0);
        accountNumber  = Integer.parseInt(acct.get(1));
        balance = Double.parseDouble(acct.get(3));
        if(acct.get(4).equals("1")){
            hasBackupAccount = true;
            backupAccountNumber = Integer.parseInt(acct.get(5));
        }
        numOfOverdrafts = Integer.parseInt(acct.get(6));
        try {
            dateCreated = new SimpleDateFormat("MM/dd/yyyy").parse(acct.get(7));
        } catch (ParseException e) {
            System.out.println("Error in trying to parse date in Checking");
            e.printStackTrace();
        }

        // create an atm card for this account
        card = new ATMCard(accountCustID, 0, accountNumber, ("0" +accountNumber + accountCustID.substring(0,2)), accountCustID.substring(accountCustID.length()-4));

    } // end Constructor

    // withdraw method, charges account a fee if it is not GD. calls balanceCheck to update account status
    // if withdraw is greater than what is in account, and there is no backup account, applies an overdraft fee
    @Override
    public void withdrawAmt(double amt) {
        System.out.println("Now withdrawing $" + amt + " from checking account " + this.getAccountNumber());
        balance -= Math.abs(amt);
        if(!goldDiamondAccount)balance -= 0.5;
        balanceCheck();
        if(balance < 0 && (!hasBackupAccount)) {
            System.out.println("Checking account " + this.getAccountNumber() + " has been overdrafted");
            numOfOverdrafts++;
            balance -= 20.0;
        }
    }

    // deposit method, charges account a fee if it is not GD. calls balanceCheck to update account status
    @Override
    public void depositAmt(double amt) {
        System.out.println("Depositing $" + amt + " into checking account " + this.getAccountNumber());
        balance += Math.abs(amt);
        if(!goldDiamondAccount)balance -= 0.5;
        balanceCheck();
    }

    // credit account method, ignores transaction fee
    @Override
    public void creditAccount(double amt) {
        System.out.println("Crediting $" + amt + " to checking account " + this.getAccountNumber());
        super.creditAccount(amt);
        balanceCheck();
    }

    // debit account method, ignores transaction fee
    @Override
    public void debitAccount(double amt) {
        System.out.println("Debiting $" + amt + " from checking account " + this.getAccountNumber());
        super.debitAccount(amt);
        balanceCheck();
        if(balance < 0) {
            numOfOverdrafts++;
            balance -= 20.0;
        }
    }

    // this method checks the current balance and verifes what type of account the account should be
    public void balanceCheck(){
        if(balance < 1000.00){
            goldDiamondAccount = false;
            System.out.println("Checking account: " + this.getAccountNumber() + " is a TMB account");
        } else{
            goldDiamondAccount = true;
            System.out.println("Checking account: " + this.getAccountNumber() + " is a Gold/Diamond account");
        }
    }

    // creates an atm card for this account
    public void createCard(){
        card = new ATMCard(accountCustID, 0, accountNumber, ("0" +accountNumber + accountCustID.substring(0,2)), accountCustID.substring(accountCustID.length()-4));
    }

    // getters
    public Date getDateCreated() {
        return dateCreated;
    }
    public ATMCard getCard() {
        return card;
    }
    public int getBackupAccountNumber() {
        return backupAccountNumber;
    }
    public boolean hasBackupAccount() {
        return hasBackupAccount;
    }
    public int getNextCheckNumber() {
        return nextCheckNumber;
    }
    public boolean isGoldDiamondAccount() {
        return goldDiamondAccount;
    }

    // setters
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setTodaysDate(Date today){
        todaysDate = today;
    }
    public void setCard(ATMCard card) {
        this.card = card;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setHasBackupAccount(boolean hasBackupAccount) {
        this.hasBackupAccount = hasBackupAccount;
    }
    public void setBackupAccountNumber(int backupAccountNumber) {
        this.backupAccountNumber = backupAccountNumber;
        setHasBackupAccount(true);
    }

    @Override
    public String toString(){
        return "[Checking Acount] " + "Account Number: " + accountNumber + " CustID: " + accountCustID + " Balance: " 
        + balance + " Current Interest Rate: " + interestRate + " Number of Overdrafts: " + numOfOverdrafts + 
        " Date Account Opened: " + dateCreated + " ATM card num: " + card.getCardNumber() + " ATM card PIN: " + card.getPinNum();
    }

} // end Checking