package Classes;

// CreditCard is a subclass of ATMCard, essentially just the same class
// with a different name for conventional purposes
public class CreditCard extends ATMCard{
    public CreditCard(String accountCustID, int type, int loanID, String cardNumber){
        super(accountCustID, type, loanID, cardNumber, "");
    }
}