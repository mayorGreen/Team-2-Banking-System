package Classes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  
import java.util.List;

public class Checking extends Account
{
    boolean goldDiamondAccount;
    double interestRate;
    ArrayList<Check> checksList = new ArrayList<>(); // list of checks tied to this account
    int nextCheckNumber;
    boolean hasBackupAccount;
    int backupAccountNumber;
    int numOfOverdrafts;
    Date dateCreated;
    Date todaysDate;


    public Checking(){
        super();
        goldDiamondAccount = false;
        interestRate = 0;
        nextCheckNumber = 1;
        hasBackupAccount = false;
        numOfOverdrafts = 0;
        dateCreated = new Date();
    }

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

    }

    @Override
    void withdrawAmt(double amt) {
        balance -= Math.abs(amt);
        if(!goldDiamondAccount)balance -= 0.5;
        balanceCheck();
        if(balance < 0) {
            numOfOverdrafts++;
            balance -= 20.0;
        }
    }

    @Override
    void depositAmt(double amt) {
        balance += Math.abs(amt);
        if(!goldDiamondAccount)balance -= 0.5;
        balanceCheck();
    }


    @Override
    void creditAccount(double amt) {
        super.creditAccount(amt);
        balanceCheck();
    }

    @Override
    void debitAccount(double amt) {
        super.debitAccount(amt);
        balanceCheck();
        if(balance < 0) {
            numOfOverdrafts++;
            balance -= 20.0;
        }
    }

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
