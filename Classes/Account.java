package Classes;

import java.io.Serializable;

// parent class for bank accounts
public class Account implements Serializable{
    int accountNumber;
    String accountCustID;
    double balance;
    boolean paymentIsCurrent;
    boolean loanPenalty;

    public Account(){
        balance = 0.00;
        accountCustID = "012345678";
        accountNumber = 0;
        paymentIsCurrent = true;
        loanPenalty = false;
    }

    void setBalance(double balance){
        this.balance = balance;
    }

    void setAccountNumber(int x){
        accountNumber = x;
    }

    void setAccountCustID(String accountCustID) {
        this.accountCustID = accountCustID;
    }

    void setPaymentIsCurrent(boolean bool) {
        paymentIsCurrent = bool;
    }

    void setLoanPenalty(boolean bool) {
        loanPenalty = bool;
    }

    void creditAccount(double amt) {
        balance += Math.abs(amt);
    }

    void debitAccount(double amt) {
        balance -= Math.abs(amt);
    }


    public int getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }

}
