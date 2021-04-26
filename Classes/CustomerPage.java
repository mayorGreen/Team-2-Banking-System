package Classes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.*;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Font;

public class CustomerPage extends JFrame implements ActionListener {

    private List<Customer> customerList;
    private List<Checking> checkingList;
    private List<SavingsAccount> savingsList;
    private List<CD> cdList;
    private List<Loans> loanList;

    JFrame mainFrame = new JFrame();

    JPanel panelContainer = new JPanel();

    JPanel panel1 = new JPanel(); // panel 1 is for selecting checking or savings acct
    JPanel panel2 = new JPanel(); // panel 2 is for selecting action for account (balance, withdraw, deposit)
    JPanel panel3 = new JPanel(); // panel 3 is for Balance Inquiries
    JPanel panel4 = new JPanel(); // panel 4 is for Withdrawals
    JPanel panel5 = new JPanel(); // panel 5 is for Deposits
    JPanel panel6 = new JPanel(); // panel 6 is for entering withdrawal amount
    JPanel panel7 = new JPanel(); // panel 7 is transaction confirmation

    JPanel[] panels = {panel1,panel2,panel3,panel4,panel5,panel6,panel7};
 

    CardLayout cl = new CardLayout();

    JLabel welcomeLabel = new JLabel("Hello");

    JLabel selectAmount = new JLabel("Select Amount");


    // panel 1 elements
    JButton checkingButton = new JButton("Checking");
    JButton savingsButton = new JButton("Savings");

    // panel 2 elements
    JLabel accountLabel = new JLabel();
    JButton balanceButton = new JButton("Balance Inquiry");
    JButton withdrawButton = new JButton("Withdraw");
    JButton depositButton = new JButton("Deposit");

    // panel 3 elements
    JLabel balanceInquiryLabel = new JLabel("Balance Inquiry");
    JLabel balanceAmount = new JLabel("Balance: $0.00");


    // panel 4 buttons
    JButton tenButton = new JButton("$10");
    JButton twentyButton = new JButton("$20");
    JButton fourtyButton = new JButton("$40");
    JButton fiftyButton = new JButton("$50");
    JButton hundredButton = new JButton("$100");
    JButton customAmountButton = new JButton("Enter An Amount");

    // panel 5 elements
    // this panel has it's own back button because it functions differently, it will both return deposit and go back
    JButton returnFundsButton = new JButton("Return Funds");
    JButton depositBackButton = new JButton("Back");
    JButton depositSubmitButton = new JButton("Submit");
    JLabel insertFundsLabel = new JLabel("Insert Checks/Bills");
    JLabel depositAmtLabel = new JLabel("$0.00");
    double depositAmt = 0;

    // panel 6 elements
    JTextField enterAmt = new JTextField("$0.00");
    String[] possibleMoneyValues = {"$0.00","$20.00","$40.00","$60.00","$80.00","$100.00","$120.00","$140.00","$160.00","$180.00","$200.00",};
    int[] possibleMoneyValues2 = {0,20,40,60,80,100,120,140,160,180,200};
    int currentVal = 0;
    JButton withdrawMinusButton = new JButton("-");
    JButton withdrawPlusButton = new JButton("+");
    JButton withdrawCustomSubmitButton = new JButton("Submit");
    JLabel enterAmtLabel = new JLabel("Enter an amount that is a multiple of $20");

    // panel 7 elements
    JLabel confirmationLabel = new JLabel();

    // utility buttons
    JButton backButton = new JButton("Back");
    JButton endButton = new JButton("End");
    
    Font buttonFont = new Font("Arial", Font.PLAIN, 28); // button font

    JButton[] buttons = {checkingButton,
                        savingsButton,balanceButton,withdrawButton,
                        depositButton,tenButton,twentyButton,
                        fourtyButton,fiftyButton,hundredButton,
                        customAmountButton,returnFundsButton,depositBackButton,
                        depositSubmitButton,withdrawMinusButton,withdrawPlusButton,
                        withdrawCustomSubmitButton,backButton,endButton};
    
    CustomerPage(String userID, List<Customer> customerList, List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, List<Loans> loanList){

        this.customerList = customerList;
        this.checkingList = checkingList;
        this.savingsList = savingsList;
        this.cdList = cdList;
        this.loanList = loanList;

        panelContainer.setLayout(cl);

        for(JPanel panel : panels){
			panel.setLayout(null);
		}

        // set button fonts, action listeners
		for(JButton button : buttons){
			button.setFont(buttonFont);
			button.setFocusable(false);
			button.addActionListener(this);
		}

        // set button fonts
        customAmountButton.setFont(new Font("Arial", Font.PLAIN, 22));

        // add ActionListeners and set button bounds
        
        checkingButton.setBounds(125,125,250,80); // position 1

        savingsButton.setBounds(125,225,250,80); // postion 2
     
        endButton.setBounds(600,535,250,80); // end position

        backButton.setBounds(125,535,250,80); // back position

        balanceButton.setBounds(125,125,250,80); // position 1

        withdrawButton.setBounds(125,225,250,80); // postion 2

        depositButton.setBounds(125,325,250,80); // position 3

        tenButton.setBounds(125,125,250,80); // position 1

        twentyButton.setBounds(125,225,250,80); // position 2

        fourtyButton.setBounds(125,325,250,80); // position 3

        fiftyButton.setBounds(600,125,250,80); // position 4

        hundredButton.setBounds(600,225,250,80); // position 5

        customAmountButton.setBounds(600,325,250,100); // position 6

        
        // set frame properties
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setTitle("Welcome " + userID + "!"); // window title
        mainFrame.add(panelContainer);
        mainFrame.setSize(1000,700);
        mainFrame.setVisible(true);

        // set welcome label
        welcomeLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        welcomeLabel.setFont(buttonFont);
        welcomeLabel.setText("Hello " + userID);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        accountLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        accountLabel.setFont(buttonFont);
        accountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        accountLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        selectAmount.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        selectAmount.setFont(buttonFont);
        selectAmount.setHorizontalAlignment(SwingConstants.CENTER);
        selectAmount.setHorizontalTextPosition(SwingConstants.CENTER);

        

        // define panel 3 elements
        balanceAmount.setHorizontalAlignment(SwingConstants.CENTER);
        balanceAmount.setHorizontalTextPosition(SwingConstants.CENTER);
        balanceAmount.setFont(buttonFont);
        balanceAmount.setBounds(400,250,200,50);
        balanceInquiryLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        balanceInquiryLabel.setFont(buttonFont);
        balanceInquiryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceInquiryLabel.setHorizontalTextPosition(SwingConstants.CENTER);



        // define panel 5 elements
        returnFundsButton.setBounds(375,350,250,80);
        depositBackButton.setBounds(125,535,250,80);
        depositSubmitButton.setBounds(600,535,250,80);
        insertFundsLabel.setBounds(0,0,mainFrame.getWidth(),300);
        insertFundsLabel.setFont(buttonFont);
        insertFundsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        insertFundsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        depositAmtLabel.setBounds(400,250,200,100);
        depositAmtLabel.setHorizontalAlignment(SwingConstants.CENTER);
        depositAmtLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        depositAmtLabel.setFont(buttonFont);


        // define panel 6 elements
        enterAmtLabel.setBounds(0,0,mainFrame.getWidth(),100);
        enterAmtLabel.setFont(buttonFont);
        enterAmtLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enterAmtLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        enterAmt.setBounds(400,300,200,50);
        enterAmt.setEditable(false);

        withdrawMinusButton.setBounds(325, 300, 50,50);
        withdrawMinusButton.setEnabled(false);

        withdrawPlusButton.setBounds(625, 300, 50,50);

        withdrawCustomSubmitButton.setBounds(450, 375, 100, 50);

        // define panel 7 elements
        confirmationLabel.setBounds(0,0,mainFrame.getWidth(),500);
        confirmationLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmationLabel.setHorizontalTextPosition(SwingConstants.CENTER);


        // add elements to panel 1

        panel1.add(welcomeLabel);
        panel1.add(checkingButton);
        panel1.add(savingsButton);
        panel1.add(endButton);

        // add elements to panel 2

        panel2.add(accountLabel);
        panel2.add(balanceButton);
        panel2.add(withdrawButton);
        panel2.add(depositButton);
        panel2.add(backButton);

        // add elements to panel 3

        panel3.add(balanceInquiryLabel);
        panel3.add(balanceAmount);


        // add elements to panel 4

        panel4.add(tenButton);
        panel4.add(twentyButton);
        panel4.add(fourtyButton);
        panel4.add(fiftyButton);
        panel4.add(hundredButton);
        panel4.add(customAmountButton);

        // add elements to panel 5

        panel5.add(depositBackButton);
        panel5.add(depositSubmitButton);
        panel5.add(returnFundsButton);
        panel5.add(insertFundsLabel);
        panel5.add(depositAmtLabel);

        // add elements to panel 6

        panel6.add(enterAmtLabel);
        panel6.add(enterAmt);
        panel6.add(withdrawMinusButton);
        panel6.add(withdrawPlusButton);
        panel6.add(withdrawCustomSubmitButton);

        // add elements to panel 7

        panel7.add(confirmationLabel);


        // add panels to container
        panelContainer.add(panel1, "1");
        panelContainer.add(panel2, "2");
        panelContainer.add(panel3, "3");
        panelContainer.add(panel4, "4");
        panelContainer.add(panel5, "5");
        panelContainer.add(panel6, "6");
        panelContainer.add(panel7, "7");
        cl.show(panelContainer, "1"); // show first panel

    }

    // button actions
    @Override
    public void actionPerformed(ActionEvent e) {

        // User selects back
        if(e.getSource() == backButton) {
            panel1.add(endButton);
            cl.show(panelContainer, "1");
        }
        // User selects end
        if(e.getSource() == endButton){
            mainFrame.dispose();
            IDandPass idAndPasswords = new IDandPass(customerList);
            new LoginPage(idAndPasswords.getLoginInfo(), customerList, checkingList, savingsList, cdList, loanList);
        }

        // User selects checking acct
        if(e.getSource() == checkingButton) {
            accountLabel.setText("Checking");
            panel2.add(endButton);
            panel2.add(backButton);
            cl.show(panelContainer, "2");
        }

        // User selects savings
        if(e.getSource() == savingsButton) {
            accountLabel.setText("Savings");
            panel2.add(endButton);
            panel2.add(backButton);
            cl.show(panelContainer, "2");
        }

        // User selects balance inquiry
        if(e.getSource() == balanceButton){
            panel3.add(backButton);
            panel3.add(endButton);
            cl.show(panelContainer, "3");
        }

        // User selects withdraw
        if(e.getSource() == withdrawButton) {
            panel4.add(endButton);
            panel4.add(backButton);
            cl.show(panelContainer, "4");
        }

        // user selects deposit
        if(e.getSource() == depositButton){
            panel5.add(backButton);
            cl.show(panelContainer, "5");
        }

        // user selects back button on DEPOSIT screen
        if(e.getSource() == depositBackButton){
            // return money
            depositAmtLabel.setText("$0.00");
            panel1.add(backButton);
            panel1.add(endButton);
            cl.show(panelContainer, "1");
        }
        
        // user selects submit button on DEPOSIT screen
        if(e.getSource() == depositSubmitButton) {
            // UPDATE ACCOUNT BALANCE
            
            // send user to confirmation screen
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }

        // user selects withdraw custom amount
        if(e.getSource() == customAmountButton) {
            panel6.add(endButton);
            panel6.add(backButton);
            cl.show(panelContainer, "6");
        }
        
        // user submits custom withdraw amount
        if(e.getSource() == withdrawCustomSubmitButton) {
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }

        // user presses '-' button on custom withdraw screen
        if(e.getSource() == withdrawMinusButton) {
            if(currentVal > 0) {
                enterAmt.setText(possibleMoneyValues[currentVal -1]);
                currentVal--;
                if(currentVal == 0) {withdrawMinusButton.setEnabled(false);}
            }
            if(withdrawPlusButton.isEnabled() == false) withdrawPlusButton.setEnabled(true);
        }

        // user presses '+' button on custom withdraw screen
        if(e.getSource() == withdrawPlusButton) {
            if(currentVal < 10) {
                enterAmt.setText(possibleMoneyValues[currentVal+1]);
                currentVal++;
                if(currentVal == 10) {withdrawPlusButton.setEnabled(false);}
            }
            if(withdrawMinusButton.isEnabled() == false) withdrawMinusButton.setEnabled(true);
        }
        

       
    }
}
