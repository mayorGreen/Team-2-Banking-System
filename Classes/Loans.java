package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
// TODO implement credit card
// TODO implement compounding interest methods
// class for loans. extends account
public class Loans extends Account
{

    int loanID; // unique loan id
    double interestRate; // interest rate on loan
    Date dueDate; // payment due date
    Date paymentNotificationDate;
    double amountDue; // amount due for this month
    String type; // type of loan
    boolean missedPayment; // raised if a payment is missed on the account
    Date lastPaymentDate;
    CreditCard card; // if account type is "Credit Card" then a credit card will be created
    double limit; // if account type is credit card then a limit will be set
    int months; //months for the loan based on years
    int years; // years of loan based on "Long Term" and "Short Term"
    int specialInfo;

    // Constructor
    public Loans(List<String> list) {
        loanID = Integer.parseInt(list.get(0));
        accountCustID = list.get(1);
        balance = Double.parseDouble(list.get(2));
        interestRate = Double.parseDouble(list.get(3));
        try{
            dueDate = new SimpleDateFormat("MM/dd/yyyy H:mm").parse(list.get(4));
            lastPaymentDate = new SimpleDateFormat("MM/dd/yyyy").parse(list.get(9));
            if(!(list.get(5).equals(""))) 
                paymentNotificationDate = new SimpleDateFormat("MM/dd/yyyy").parse(list.get(5));
        } catch (ParseException e){
            System.out.println("Error in trying to parse date in Loans");
            e.printStackTrace();
        }
        amountDue = Double.parseDouble(list.get(6));
        type = list.get(7);
        specialInfo = Integer.parseInt(list.get(10));

        switch (type)// sets the number of years on a loan and sets loan to true or false based on if it is a loan or credit card
        {
            case "Short Term":
            case "Long Term":
                years = specialInfo;
                break;
            case "Credit Card":
                limit = specialInfo;
                card = new CreditCard(accountCustID, 2, (accountNumber + accountCustID.substring(0,2)));
                setLimit(specialInfo);
                break;
            default: break;
        }

        if(list.get(8).equals("TRUE")) {
            missedPayment = true;
        } else {
            missedPayment = false;
        }

        months = years * 12;
        System.out.println(specialInfo);
        calculateMonthlyPayment();
    }

    //method to calculate the monthly payment of each loan or credit card
    double calculateMonthlyPayment()
    {
            amountDue = (balance/months) + ((balance/2)*years*interestRate/months);

            if (missedPayment = true)
            {
                amountDue += 75;
            }

        //System.out.println(amountDue);
            return amountDue;
    }

    @Override
    void withdrawAmt(double amt) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void depositAmt(double amt) {
        // TODO Auto-generated method stub
        
    }

    // getters
    public double getLimit() {
        return limit;
    }
    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public double getAmountDue() {
        return amountDue;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public boolean isMissedPayment() {
        return missedPayment;
    }

    // setters
    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }
    public void setMissedPayment(boolean missedPayment) {
        this.missedPayment = missedPayment;
    }
    public void setLimit(double limit) {
        this.limit = limit;
    }

} // end Loans
