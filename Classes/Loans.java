package Classes;

public class Loans extends Account
{

    public Loans(String line)
    {
        String[] split = line.split(",");
        accountNumber = Integer.parseInt(split[0]);
        customerID = Integer.parseInt(split[1]);
        accountBalance = Double.parseDouble(split[2]);
    }
}
