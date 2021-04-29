package Classes;

import java.io.Serializable;

// class for Checks
public class Check implements Serializable {

    int accountNum; // account number check is associated with
    int checkNum; // unique check number
    double checkAmount; // amount for which check is valued
    int routingNum = 123456789; // bank routing number does not change
    boolean checkStopped; // raied if a stop payment is put on this check

    public Check(int accountNum, int checkNum, double checkAmount){
        this.accountNum = accountNum;
        this.checkNum = checkNum;
        this.checkAmount = checkAmount;
        checkStopped = false;
    }
    
    public boolean isCheckStopped() {
        return checkStopped;
    }

    // getters
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

    // setters

    public void setCheckStopped(boolean checkStopped) {
        this.checkStopped = checkStopped;
    }

} // end Check
