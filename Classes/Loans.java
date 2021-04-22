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

        if(list.get(8).equals("TRUE")) {
            missedPayment = true;
        } else {
            missedPayment = false;
        }
    }

    @Override
    void withdrawAmt(double amt) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void depositAmt(double amt) {
        // TODO Auto-generated method stub
        
    }

} // end Loans
