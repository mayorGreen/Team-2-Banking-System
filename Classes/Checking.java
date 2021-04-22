package Classes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.util.List;

// Checking account class
public class Checking extends Account
{
    boolean goldDiamondAccount; // raised if account balance > $1000.00
    double interestRate; // interest rate for account, only goldDiamond accounts accrue interest
    ArrayList<Check> checksList = new ArrayList<>(); // list of checks tied to this account
    int nextCheckNumber; // next check number associated with account
    boolean hasBackupAccount; // raised if account has backup account associated for overdrdaft protection
    int backupAccountNumber; // account number of backup account
    int numOfOverdrafts; // number of overdrafts logged on account
    Date dateCreated; // date account created
    Date todaysDate; // today's date --- needed for interest purposed on GD account

    // generic constructor
    public Checking(){
        super();
        goldDiamondAccount = false;
        interestRate = 0;
        nextCheckNumber = 1;
        hasBackupAccount = false;
        numOfOverdrafts = 0;
        dateCreated = new Date();
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

    } // end Constructor

    // withdraw method, charges account a fee if it is not GD. calls balanceCheck
    // if withdraw is greater
    @Override
    void withdrawAmt(double amt) {
        balance -= Math.abs(amt);
        if(!goldDiamondAccount)balance -= 0.5;
        balanceCheck();
        if(balance < 0) {
            numOfOverdrafts++;
            balance -= 20.0;
        } // TODO check for overdraft protection, pull data from the file using helper method
          // then update both files, it's gonna be a bit messsy but should work
    }

    // deposit method, charges account a fee if it is not GD. calls balanceCheck
    @Override
    void depositAmt(double amt) {
        balance += Math.abs(amt);
        if(!goldDiamondAccount)balance -= 0.5;
        balanceCheck();
    }

    // credit account method, ignores transaction fee
    @Override
    void creditAccount(double amt) {
        super.creditAccount(amt);
        balanceCheck();
    }
    // debit account method, ignores transaction fee
    @Override
    void debitAccount(double amt) {
        super.debitAccount(amt);
        balanceCheck();
        if(balance < 0) {
            numOfOverdrafts++;
            balance -= 20.0;
        }
    }

    // this method checks the current balance and verifes what type of account the account should be
    void balanceCheck(){
        if(balance < 1000.00){
            goldDiamondAccount = false;
        } else{
            goldDiamondAccount = true;
        }
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    void setTodaysDate(Date today){
        todaysDate = today;
    }

    @Override
    public String toString(){
        return "Account Number: " + accountNumber + " CustID: " + accountCustID + " Balance: " 
        + balance + " Current Interest Rate: " + interestRate + " Number of Overdrafts: " + numOfOverdrafts + 
        " Date Account Opened: " + dateCreated;
    }

}
