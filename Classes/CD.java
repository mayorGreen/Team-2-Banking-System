package Classes;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CD extends SavingsAccount
{
    protected Date dueDate;

    public CD(List<String> cds){
        super();
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

    @Override
    void withdrawAmt(double amt) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void depositAmt(double amt) {
        // TODO Auto-generated method stub
        
    }

}
