package Classes;

import java.io.Serializable;

public class Check implements Serializable {
    int accountNum;
    int checkNum;
    double checkAmount;
    int routingNum = 123456789; // bank routing number does not change
    boolean checkStopped;

    public Check(int accountNum, int checkNum, double checkAmount){
        this.accountNum = accountNum;
        this.checkNum = checkNum;
        this.checkAmount = checkAmount;
        checkStopped = false;
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

    public boolean isCheckStopped() {
        return checkStopped;
    }
}
