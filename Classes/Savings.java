package Classes;

public class Savings extends Account
{
    public double interestRate;
    public String openedDate;

    public Savings(String line)
    {
        String[] split = line.split(",");
        customerID = Integer.parseInt(split[0]);
        accountNumber = Integer.parseInt(split[1]);
        accountBalance = Double.parseDouble(split[2]);
        interestRate = Double.parseDouble(split[3]);
        openedDate = split[4];
    }

}
