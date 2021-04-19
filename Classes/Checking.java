package Classes;

public class Checking extends Account
{
    public String accountType;
    public double accountBalance;
    public int backupAccount;
    public int backupAccountNumber;
    public int overDrafts;
    public String openedDate;

    public Checking(String line)
    {
        String[] split = line.split(",");
        customerID = Integer.parseInt(split[0]);
        accountNumber = Integer.parseInt(split[1]);
        accountType = split[2];
        accountBalance = Double.parseDouble(split[3]);
        backupAccount = Integer.parseInt(split[4]);
        backupAccountNumber = Integer.parseInt(split[5]);
        overDrafts = Integer.parseInt(split[6]);
        openedDate = split[4];
    }

}
