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
    private List<Check> checkList;

    private int workingAcctNum;
    private String workingAcctType;
    private ATMCard workingCard;

    JFrame mainFrame = new JFrame();

    JPanel panelContainer = new JPanel();

    JPanel initPanel = new JPanel(); // initPanel is the insert card screen, for sim purposes, enter card num
    JPanel panel0 = new JPanel(); // panel 0 is the enter pin screen
    JPanel panel1 = new JPanel(); // panel 1 is for selecting checking or savings acct
    JPanel panel2 = new JPanel(); // panel 2 is for selecting action for account (balance, withdraw, deposit)
    JPanel panel3 = new JPanel(); // panel 3 is for Balance Inquiries
    JPanel panel4 = new JPanel(); // panel 4 is for Withdrawals
    JPanel panel5 = new JPanel(); // panel 5 is for Deposits
    JPanel panel6 = new JPanel(); // panel 6 is for entering withdrawal amount
    JPanel panel7 = new JPanel(); // panel 7 is transaction confirmation

    JPanel[] panels = {initPanel,panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7};
 

    CardLayout cl = new CardLayout();

    JLabel welcomeLabel = new JLabel("Hello");

    JLabel selectAmount = new JLabel("Select Amount");

    // initPanel elements
    JLabel cardNumLabel = new JLabel("Insert ATM Card");
    JTextField cardNumField = new JTextField();
    JButton cardNumButton = new JButton("Submit");
    JButton programEndButton = new JButton("End");

    // panel 0 elements
    JLabel cardPinLabel = new JLabel("Please Enter Pin");
    JTextField cardPinField = new JTextField();
    JButton cardPinButton = new JButton("Submit");
    JButton cardPinCancelButton = new JButton("Cancel");


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
    JButton twentyButton = new JButton("$20");
    JButton fourtyButton = new JButton("$40");
    JButton sixtyButton = new JButton("$60");
    JButton eightyButton = new JButton("$80");
    JButton hundredButton = new JButton("$100");
    JButton customAmountButton = new JButton("Enter An Amount");

    // panel 5 elements
    // this panel has it's own back button because it functions differently, it will both return deposit and go back
    JButton returnFundsButton = new JButton("Return Funds");
    JButton depositBackButton = new JButton("Back");
    JButton depositSubmitButton = new JButton("Submit");
    JLabel insertFundsLabel = new JLabel("Insert Checks/Bills");
    JTextField depositAmtField = new JTextField("0.00");
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
                        depositButton,twentyButton,fourtyButton,
                        sixtyButton,eightyButton,hundredButton,
                        customAmountButton,returnFundsButton,depositBackButton,
                        depositSubmitButton,withdrawMinusButton,withdrawPlusButton,
                        withdrawCustomSubmitButton,backButton,endButton,
                        cardNumButton,cardPinButton,cardPinCancelButton,programEndButton};
    
    CustomerPage(List<Customer> customerList, List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, List<Loans> loanList, List<Check> checkList){

        this.customerList = customerList;
        this.checkingList = checkingList;
        this.savingsList = savingsList;
        this.cdList = cdList;
        this.loanList = loanList;
        this.checkList = checkList;

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

        twentyButton.setBounds(125,125,250,80); // position 1

        fourtyButton.setBounds(125,225,250,80); // position 2

        sixtyButton.setBounds(125,325,250,80); // position 3

        eightyButton.setBounds(600,125,250,80); // position 4

        hundredButton.setBounds(600,225,250,80); // position 5

        customAmountButton.setBounds(600,325,250,100); // position 6

        
        // set frame properties
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setTitle("Welcome!"); // window title
        mainFrame.add(panelContainer);
        mainFrame.setSize(1000,700);
        mainFrame.setVisible(true);

        // define init panel elements
        cardNumLabel.setBounds(400,200,350,50);
        cardNumLabel.setFont(buttonFont);
        cardNumField.setBounds(400,300,200,50);
        cardNumButton.setBounds(400,375,200,75);
        programEndButton.setBounds(600,535,250,80);
        programEndButton.setFont(buttonFont);

        // define panel 0 panel elements
        cardPinLabel.setBounds(400,200,350,50);
        cardPinLabel.setFont(buttonFont);
        cardPinField.setBounds(400,300,200,50);
        cardPinButton.setBounds(600,535,250,80);
        cardPinCancelButton.setBounds(125,535,250,80);

        // set welcome label
        welcomeLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        welcomeLabel.setFont(buttonFont);
        welcomeLabel.setText("MyBank");
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
        balanceAmount.setBounds(200 ,250,600,50);
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
        depositAmtField.setBounds(400,250,200,100);
        depositAmtField.setHorizontalAlignment(SwingConstants.CENTER);
        depositAmtField.setFont(buttonFont);


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

        withdrawCustomSubmitButton.setBounds(400, 375, 200, 50);

        // define panel 7 elements
        confirmationLabel.setBounds(0,0,mainFrame.getWidth(),500);
        confirmationLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmationLabel.setHorizontalTextPosition(SwingConstants.CENTER);



        // add elements to initPanel

        initPanel.add(cardNumLabel);
        initPanel.add(cardNumField);
        initPanel.add(cardNumButton);
        initPanel.add(programEndButton);
        
        // add elements to panel 0
        
        panel0.add(cardPinLabel);
        panel0.add(cardPinField);
        panel0.add(cardPinButton);
        panel0.add(cardPinCancelButton);
        panel0.add(endButton);

        // add elements to panel 1

        panel1.add(welcomeLabel);
        panel1.add(checkingButton);
        panel1.add(savingsButton);

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

        panel4.add(twentyButton);
        panel4.add(fourtyButton);
        panel4.add(sixtyButton);
        panel4.add(eightyButton);
        panel4.add(hundredButton);
        panel4.add(customAmountButton);

        // add elements to panel 5

        panel5.add(depositBackButton);
        panel5.add(depositSubmitButton);
        panel5.add(returnFundsButton);
        panel5.add(insertFundsLabel);
        panel5.add(depositAmtField);

        // add elements to panel 6

        panel6.add(enterAmtLabel);
        panel6.add(enterAmt);
        panel6.add(withdrawMinusButton);
        panel6.add(withdrawPlusButton);
        panel6.add(withdrawCustomSubmitButton);

        // add elements to panel 7

        panel7.add(confirmationLabel);


        // add panels to container
        panelContainer.add(initPanel, "init");
        panelContainer.add(panel0, "0");
        panelContainer.add(panel1, "1");
        panelContainer.add(panel2, "2");
        panelContainer.add(panel3, "3");
        panelContainer.add(panel4, "4");
        panelContainer.add(panel5, "5");
        panelContainer.add(panel6, "6");
        panelContainer.add(panel7, "7");
        cl.show(panelContainer, "init"); // show init panel

    }

    // button actions
    @Override
    public void actionPerformed(ActionEvent e) {

        // user inserts their atm card
        if(e.getSource() == cardNumButton){
            // check if valid card
            if(cardNumField.getText().equals("")){
                System.out.println("invalid card");
            } else if(cardNumField.getText().charAt(0) == '0'){
                // card belongs to checking account, look for account
                int accIndex = HelperFunc.findCardChecking(checkingList, cardNumField.getText());
                if (accIndex != -1){
                    // set working card and acct num
                    System.out.println("Found card at index " + accIndex);
                    workingCard = checkingList.get(accIndex).getCard();
                    workingAcctNum = workingCard.getAccountNumber();
                    workingAcctType = workingCard.getAccountType();
                    panel0.add(endButton);
                    cl.show(panelContainer, "0");
                } else {
                    System.out.println("Card does not exist");
                }

            } else if(cardNumField.getText().charAt(0) == '1'){
                int accIndex = HelperFunc.findCardSavings(savingsList, cardNumField.getText());
                if (accIndex != -1){
                    workingCard = savingsList.get(accIndex).getCard();
                    workingAcctNum = workingCard.getAccountNumber();
                    workingAcctType = workingCard.getAccountType();
                    panel0.add(endButton);
                    cl.show(panelContainer, "0");
                } else {
                    System.out.println("Card does not exist");
                }
            }
        }


        if(e.getSource() == cardPinCancelButton){
            initPanel.add(endButton);
            cardNumField.setText("");
            cardPinField.setText("");
            cl.show(panelContainer, "init");
        }

        if(e.getSource() == cardPinButton){
            if(HelperFunc.pinCheck(workingCard, cardPinField.getText())){
                panel1.add(endButton);
                if(workingAcctType.equals("Checking")){
                    savingsButton.setEnabled(false);
                    checkingButton.setEnabled(true);
                } else if(workingAcctType.equals("Savings")){
                    checkingButton.setEnabled(false);
                    savingsButton.setEnabled(true);
                }
                cl.show(panelContainer, "1");
            } else System.out.println("Incorrect Pin Number");
        } 

        // User selects back
        if(e.getSource() == backButton) {
            panel1.add(endButton);
            cl.show(panelContainer, "1");
        }
        // User selects end on ATM card screen
        if(e.getSource() == programEndButton){
            mainFrame.dispose();
            IDandPass idAndPasswords = new IDandPass(customerList);
            new LoginPage(idAndPasswords.getLoginInfo(), customerList, checkingList, savingsList, cdList, loanList, checkList);
        }

        if(e.getSource() == endButton){
            cardNumField.setText("");
            cardPinField.setText("");
            cl.show(panelContainer, "init");
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
            if(workingAcctType.equals("Checking")){
            balanceAmount.setText("Balance: $" + HelperFunc.getCheckingBalance(checkingList, workingAcctNum));
        } else if(workingAcctType.equals("Savings")){
            balanceAmount.setText("Balance: $" + HelperFunc.getSavingsBalance(savingsList, workingAcctNum));
        }
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

        // withdraw buttons
        if(e.getSource() == twentyButton){
            if(workingAcctType.equals("Checking")){
                HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, workingAcctNum, 20);
                HelperFunc.updateChecking(checkingList); // update checking accts
                HelperFunc.updateSavings(savingsList); // update savings accts
            } else if(workingAcctType.equals("Savings")){
                HelperFunc.withdrawSavings(savingsList, workingAcctNum, 20);
                HelperFunc.updateSavings(savingsList);
            }
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }
        if(e.getSource() == fourtyButton){
            if(workingAcctType.equals("Checking")){
                HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, workingAcctNum, 40);
                HelperFunc.updateChecking(checkingList); // update checking accts
                HelperFunc.updateSavings(savingsList); // update savings accts
            } else if(workingAcctType.equals("Savings")){
                HelperFunc.withdrawSavings(savingsList, workingAcctNum, 40);
                HelperFunc.updateSavings(savingsList);
            }
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }
        if(e.getSource() == sixtyButton){
            if(workingAcctType.equals("Checking")){
                HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, workingAcctNum, 60);
                HelperFunc.updateChecking(checkingList); // update checking accts
                HelperFunc.updateSavings(savingsList); // update savings accts
            } else if(workingAcctType.equals("Savings")){
                HelperFunc.withdrawSavings(savingsList, workingAcctNum, 60);
                HelperFunc.updateSavings(savingsList);
            }
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }
        if(e.getSource() == eightyButton){
            if(workingAcctType.equals("Checking")){
                HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, workingAcctNum, 80);
                HelperFunc.updateChecking(checkingList); // update checking accts
                HelperFunc.updateSavings(savingsList); // update savings accts
            } else if(workingAcctType.equals("Savings")){
                HelperFunc.withdrawSavings(savingsList, workingAcctNum, 80);
                HelperFunc.updateSavings(savingsList);
            }
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }
        if(e.getSource() == hundredButton){
            if(workingAcctType.equals("Checking")){
                HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, workingAcctNum, 100);
                HelperFunc.updateChecking(checkingList); // update checking accts
                HelperFunc.updateSavings(savingsList); // update savings accts
            } else if(workingAcctType.equals("Savings")){
                HelperFunc.withdrawSavings(savingsList, workingAcctNum, 100);
                HelperFunc.updateSavings(savingsList);
            }
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }

        // user selects deposit
        if(e.getSource() == depositButton){
            panel5.add(backButton);
            cl.show(panelContainer, "5");
        }

        // user selects back button on DEPOSIT screen
        if(e.getSource() == depositBackButton){
            // return money
            depositAmtField.setText("0.00");
            panel1.add(endButton);
            cl.show(panelContainer, "1");
        }


        // return funds
        if(e.getSource() == returnFundsButton){
            // return money
            depositAmtField.setText("0.00");
        }
        
        // user selects submit button on DEPOSIT screen
        if(e.getSource() == depositSubmitButton) {
            // UPDATE ACCOUNT BALANCE
            boolean parsable = HelperFunc.isParsableNumber(depositAmtField.getText());
            if(parsable){
                depositAmt = Double.parseDouble(depositAmtField.getText());
                if(workingAcctType.equals("Checking")){
                    HelperFunc.depositToChecking(checkingList, workingAcctNum, depositAmt);
                    HelperFunc.updateChecking(checkingList); // update checking accts
                } else if (workingAcctType.equals("Savings")){
                    HelperFunc.depositToSavings(savingsList, workingAcctNum, depositAmt);
                    HelperFunc.updateSavings(savingsList); // update savings accts
                }
                confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
                panel7.add(endButton);
                cl.show(panelContainer, "7");
            } else {
                System.out.println("Error in deposit input: Unparsable number");
            }
        }

        // user selects withdraw custom amount
        if(e.getSource() == customAmountButton) {
            panel6.add(endButton);
            panel6.add(backButton);
            cl.show(panelContainer, "6");
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

        // user submits custom withdraw amount
        if(e.getSource() == withdrawCustomSubmitButton) {
            double amtToWithdraw = possibleMoneyValues2[currentVal];
            if(workingAcctType.equals("Checking")){
                HelperFunc.withdrawCheckingWithSafety(checkingList, savingsList, workingAcctNum, amtToWithdraw);
                HelperFunc.updateChecking(checkingList);
            } else if(workingAcctType.equals("Savings")){
                HelperFunc.withdrawSavings(savingsList, workingAcctNum, amtToWithdraw);
                HelperFunc.updateSavings(savingsList);
            }
            confirmationLabel.setText("<HTML><p style=\"text-align:center;\">Thank you for your Transaction!<br>Have a nice day!</p></HTML>");
            panel7.add(endButton);
            cl.show(panelContainer, "7");
        }

    }
}
