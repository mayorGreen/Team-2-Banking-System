package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// savings account class. extends account
public class SavingsAccount extends Account{

    protected double interestRate; // interest rate
    protected Date dateCreated; // date account created
    Date todaysDate;
    // TODO: implement a method to update the time for all accounts upon load
    // generic constructor
    public SavingsAccount(){
        super();
        interestRate = 0.0;
        dateCreated = new Date();
    }

    public SavingsAccount(List<String> savAcc){
        super();
        accountCustID=savAcc.get(0);
        accountNumber = Integer.parseInt(savAcc.get(1)); 
        balance = Double.parseDouble(savAcc.get(2));
        interestRate = Double.parseDouble(savAcc.get(3));
        try {
            dateCreated = new SimpleDateFormat("MM/dd/yyyy").parse(savAcc.get(4));
        } catch (ParseException e) {
            System.out.println("Error in trying to parse date in Savings");
            e.printStackTrace();
        }

    }
    // setters
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    // getters
    public double getInterestRate() {
        return interestRate;
    }
    public Date getDateCreated() {
        return dateCreated;
    }

    void compoundInterest() {
        // TODO: compound interest formula
    }
    
    @Override
    void withdrawAmt(double amt) {
        if (amt <= balance) {
            balance -= amt;
        } else {
            System.out.println("Insufficient Funds in savings account");
        }
    }

    @Override
    void depositAmt(double amt) {
        balance += amt;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " CustID: " + accountCustID + " Balance: " 
        + balance + " Current Interest Rate: " + interestRate + " Date Account Opened: " + dateCreated;
    }

} // end SavingsAccount
