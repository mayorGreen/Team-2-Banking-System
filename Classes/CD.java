package Classes;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

// This class is for handling CDs and is a subclass of SavingsAccount
// CDs have mostly the same functions 
public class CD extends SavingsAccount
{
    protected Date dueDate; // date that the customer may begin to withdraw money from CD
    boolean earlyWithdrawPenalty; //  raises true when customer withdraws money before the due date

    // generic constructor
    public CD(){
        super();
        earlyWithdrawPenalty = false;
    }

    // Constructor
    public CD(List<String> cds){
        super();
        earlyWithdrawPenalty = false;
        accountCustID = cds.get(0);
        accountNumber = Integer.parseInt(cds.get(1));
        balance = Double.parseDouble(cds.get(2));
        interestRate = Double.parseDouble(cds.get(3));
        try {
            dateCreated = new SimpleDateFormat("MM/dd/yyyy").parse(cds.get(4));
            dueDate = new SimpleDateFormat("MM/dd/yyyy").parse(cds.get(5));
        } catch (ParseException e) {
            System.out.println("Error in trying to parse date in Savings");
            e.printStackTrace();
        }
    }

    // getters
    public Date getDueDate() {
        return dueDate;
    }
    public boolean isEarlyWithdrawPenalty() {
        return earlyWithdrawPenalty;
    }

    // setters
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setEarlyWithdrawPenalty(boolean earlyWithdrawPenalty) {
        this.earlyWithdrawPenalty = earlyWithdrawPenalty;
    }

    // checks current time against due date, if withdraw date is before due date, penalizes account
    @Override
    void withdrawAmt(double amt) {
        // check current time with due date
        if((new Date().compareTo(dueDate)) < 0) {
            earlyWithdrawPenalty = true;
            System.out.println("Early withdrawal from CD #" + getAccountNumber());
            super.withdrawAmt(amt);
        } else {
            super.withdrawAmt(amt);
        }
    }
    
    // same as super class
    @Override
    void depositAmt(double amt) {
        super.depositAmt(amt);
    }

    @Override
    public String toString() {
        return "[CD] " + "Account Number: "+accountNumber+" CustID: "+accountCustID+" Account Balance: "+balance+" Interest Rate: "+interestRate+
        " Account Opened: "+dateCreated+" Due Date: "+ dueDate + " Early Withdrawal? " + earlyWithdrawPenalty;
    }

} // end CD