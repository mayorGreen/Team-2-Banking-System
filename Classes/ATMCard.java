package Classes;

import java.io.Serializable;

// class for ATM cards
// ATM cards may be assigned to checking accounts or savings accounts
public class ATMCard implements Serializable {
    String accountCustID;
    String accountType;
    private String cardNumber;
    private String pinNum;

    // Constructor
    public ATMCard(String accountCustID, int type, String cardNumber, String pinNum){
        this.accountCustID = accountCustID;
        if(type == 0) {
            accountType = "Checking";
        } else if (type == 1) {
            accountType = "Savings";
        } else if (type == 2) {
            accountType = "Credit Card";
        }
        this.cardNumber = cardNumber;
        this.pinNum = pinNum;
    }

    // getters
    public String getAccountCustID() {
        return accountCustID;
    }
    public String getAccountType() {
        return accountType;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getPinNum() {
        return pinNum;
    }

    // setters
    public void setAccountCustID(String accountCustID) {
        this.accountCustID = accountCustID;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setPinNum(String pinNum) {
        this.pinNum = pinNum;
    }


} // end ATMCard
