package Classes;

import java.util.List;

public class SavingsAccount extends Account{
    private double interestRate;
    private String dateCreated;

    // generic constructor
    public SavingsAccount(){
        super();
        interestRate = 0.0;
        dateCreated = "1/1/1970";
    }

    public SavingsAccount(List<String> savAcc){
        super();
        setAccountCustID(savAcc.get(0));
        setAccountNumber(Integer.parseInt(savAcc.get(1))); 
        setBalance(Double.parseDouble(savAcc.get(2)));
        interestRate = Double.parseDouble(savAcc.get(3));
        dateCreated = savAcc.get(4);

    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getInterestRate() {
        return interestRate;
    }
    public String getDateCreated() {
        return dateCreated;
    }

    void compoundInterest() {
        // TODO: compound interest formula
    }
    
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + " CustID: " + accountCustID + " Balance: " 
        + balance + " Current Interest Rate: " + interestRate + " Date Account Opened: " + dateCreated;
    }
}
