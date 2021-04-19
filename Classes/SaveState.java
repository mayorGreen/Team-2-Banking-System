package Classes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaveState
{
    //save state for savings accounts
    public SaveState (Savings savings) throws IOException
    {
        Writer fileWriter = new FileWriter("Database Files/savings.csv", false);
        //Don't have anything yet, but we will give it the savings account and hopefully rewrite over the savings file.
        //We will take the array(list) of the savings account and rewrite all of the data (with the changes) to the savings file
        //this will be the procedure for all files

    }

    //save state for checking accounts
    public SaveState (Checking checking)
    {

    }

    //save state for loans
    public SaveState (Loans loans)
    {

    }

    //save state for customers
    public SaveState (Customer customer)
    {

    }
}
