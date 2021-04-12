import javax.swing.*;
import Classes.*;


class Main {
    public static void main(String[] args) {
        IDandPass idAndPasswords = new IDandPass(); // initialize id and passwords --- this can be replaced for simply acct numbers instead
        LoginPage loginPage = new LoginPage(idAndPasswords.getLoginInfo()); // generate login page

    }
}