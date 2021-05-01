package Classes;

import java.io.Serializable;


// class for Checks
public class Check implements Serializable {

    int accountNum; // account number check is associated with
    int checkNum; // unique check number
    double checkAmount; // amount for which check is valued
    int routingNum = 123456789; // bank routing number does not change for local checks
    boolean checkStopped; // raied if a stop payment is put on this check
    String accountTypeDeposit; // type of account foreign check is to be deposited to
    int accountNumDeposit; // account number foreign check is to be deposited to
    boolean incomingCheck;
    String accountType;

    public Check(int accountNum, int checkNum, double checkAmount, String accountType){
        this.accountNum = accountNum;
        this.checkNum = checkNum;
        this.checkAmount = checkAmount;
        this.accountType = accountType;
        checkStopped = false;
        incomingCheck = false;
    }

    public Check(int accountNum, int routingNum, int checkNum, double checkAmount, String accountType){
        this.accountNum = accountNum;
        this.checkNum = checkNum;
        this.checkAmount = checkAmount;
        this.routingNum = routingNum;
        this.accountType = accountType;
        accountTypeDeposit = accountType;
        checkStopped = false;
        incomingCheck = false;
    }
    
    // getters
    public boolean isCheckStopped() {
        return checkStopped;
    }
    public int getAccountNum() {
        return accountNum;
    }
    public int getCheckNum() {
        return checkNum;
    }
    public double getCheckAmount() {
        return checkAmount;
    }
    public int getRoutingNum() {
        return routingNum;
    }
    public int getAccountNumDeposit() {
        return accountNumDeposit;
    }
    public String getAccountTypeDeposit() {
        return accountTypeDeposit;
    }
    public boolean isIncomingCheck() {
        return incomingCheck;
    }
    public String getAccountType() {
        return accountType;
    }

    // setters

    public void setCheckStopped(boolean checkStopped) {
        this.checkStopped = checkStopped;
    }
    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }
    public void setRoutingNum(int routingNum) {
        this.routingNum = routingNum;
    }
    public void setCheckAmount(double checkAmount) {
        this.checkAmount = checkAmount;
    }
    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }
    public void setAccountTypeDeposit(String accountTypeDeposit) {
        this.accountTypeDeposit = accountTypeDeposit;
    }
    public void setAccountNumDeposit(int accountNumDeposit) {
        this.accountNumDeposit = accountNumDeposit;
    }
    public void setIncomingCheck(boolean incomingCheck) {
        this.incomingCheck = incomingCheck;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNum +
        " Check Number: " + checkNum +
        " Check Amount: "+ checkAmount +
        " Check Stopped: " + checkStopped +
        " Account type of check: " + accountType+
        " Is outside check?: " + incomingCheck +
        " Account type to be deposited to: " + accountTypeDeposit + 
        " Number of depositing account: " + accountNumDeposit;
    }
} // end Check
