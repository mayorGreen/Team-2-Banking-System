package Classes;

import java.io.Serializable;

// class for ATM cards
// ATM cards may be assigned to checking accounts or savings accounts
public class ATMCard implements Serializable {

    String accountCustID; // id of customer linked to this ATM card
    String accountType; // type of account this card is linked to
    private int accountNumber; // account number of the acocunt this card is linked to
    private String cardNumber; // this card's number
    private String pinNum; // this cards PIN number

    // Constructor
    public ATMCard(String accountCustID, int type, int accountNumber, String cardNumber, String pinNum){
        this.accountCustID = accountCustID;
        this.accountNumber = accountNumber;
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
    public int getAccountNumber() {
        return accountNumber;
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