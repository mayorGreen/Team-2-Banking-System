package Classes;

import java.io.Serializable;

// parent class for bank accounts. Bare accounts cannot be instantiated, therefore
// the class is declared as abstract
public abstract class Account implements Serializable{
    int accountNumber;
    String accountCustID; // ssn of associated customer
    double balance; // current balance of account
    boolean paymentIsCurrent; // for loans/credit cards, flag for if they're up to date on payments
    boolean loanPenalty;

    protected Account(){
        balance = 0.00;
        accountCustID = "012345678";
        accountNumber = 0;
        paymentIsCurrent = true;
        loanPenalty = false;
    }

    // setters
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

    // getters
    public int getAccountNumber() {
        return accountNumber;
    }
    public double getAccountBalance() {
        return balance;
    }
    public String getAccountCustID() {
        return accountCustID;
    }

    // account functions
    void creditAccount(double amt) {
        balance += Math.abs(amt);
    }

    void debitAccount(double amt) {
        balance -= Math.abs(amt);
    }

    // withdrawing and depositing to different types of accounts
    // has different effects
    abstract void withdrawAmt(double amt);

    abstract void depositAmt(double amt);

} // End Account
