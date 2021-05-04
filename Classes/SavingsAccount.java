package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// savings account class. extends account
public class SavingsAccount extends Account{

    protected double interestRate; // interest rate
    protected Date dateCreated; // date account created
    private ATMCard card; // ATM card associated with the account
    private Date lastWithdrawal; // time of last withdrawal
    private Date todaysDate; // todays date
    private int withdrawalsToday; // number of withdrawals today, limited to two per day

    // generic constructor
    public SavingsAccount(){
        super();
        interestRate = 0.0;
        withdrawalsToday = 0;
        dateCreated = new Date();
        todaysDate = new Date();
    }

    // Constructor
    public SavingsAccount(List<String> savAcc){
        super();
        withdrawalsToday = 0;
        accountCustID=savAcc.get(0);
        accountNumber = Integer.parseInt(savAcc.get(1)); 
        balance = Double.parseDouble(savAcc.get(2));
        interestRate = Double.parseDouble(savAcc.get(3));
        try {
            dateCreated = new SimpleDateFormat("MM/dd/yyyy").parse(savAcc.get(4));
            lastWithdrawal = dateCreated;
        } catch (ParseException e) {
            System.out.println("Error in trying to parse date in Savings");
            e.printStackTrace();
        }
        // create an atm card for this account
        card = new ATMCard(accountCustID, 1, accountNumber, ("1" + accountNumber + accountCustID.substring(0,2)), accountCustID.substring(accountCustID.length()-4));
    }

    // create an atm card for this account
    public void createCard(){
        card = new ATMCard(accountCustID, 1, accountNumber, ("1" + accountNumber + accountCustID.substring(0,2)), accountCustID.substring(accountCustID.length()-4));
    }

    // increment withdrawals today
    public void incrementWithdrawals() {
        withdrawalsToday++;
    }
    
    // getters
    public double getInterestRate() {
        return interestRate;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public ATMCard getCard() {
        return card;
    }
    public int getWithdrawalsToday() {
        return withdrawalsToday;
    }
    public Date getLastWithdrawal() {
        return lastWithdrawal;
    }
    
    // setters
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setTodaysDate(Date todaysDate) {
        this.todaysDate = todaysDate;
    }
    public void setLastWithdrawal(Date lastWithdrawal) {
        this.lastWithdrawal = lastWithdrawal;
    }
    public void setWithdrawalsToday(int withdrawalsToday) {
        this.withdrawalsToday = withdrawalsToday;
    }

    @Override
    void withdrawAmt(double amt) {
        System.out.println("Withdrawing $" + amt + " from Savings account: " + accountNumber);
        if (amt <= balance) {
            balance -= amt;
        } else {
            System.out.println("Insufficient Funds in savings account");
            balance -= 20.00;
        }
    }

    @Override
    void depositAmt(double amt) {
        System.out.println("Depositing $" + amt + " to Savings account: " + accountNumber);
        balance += amt;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " CustID: " + accountCustID + " Balance: " 
        + balance + " Current Interest Rate: " + interestRate + " Date Account Opened: " + dateCreated + " Withdrawals Today: " + withdrawalsToday;
    }

} // end SavingsAccount