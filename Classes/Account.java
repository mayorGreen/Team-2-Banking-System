package Classes;
import java.time.LocalDate;
import java.time.LocalDateTime;


// parent class for bank accounts
public class Account {
    private double balance;
    private boolean paymentIsCurrent;
    private boolean loanPenalty;

    public Account(){
        balance = 0.00;
        paymentIsCurrent = true;
        loanPenalty = false;
    }

}
