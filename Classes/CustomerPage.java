package Classes;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Font;

public class CustomerPage extends JFrame implements ActionListener {
    JFrame mainFrame = new JFrame();

    JPanel panelContainer = new JPanel();

    JPanel panel1 = new JPanel(); // panel 1 is for selecting checking or savings acct
    JPanel panel2 = new JPanel(); // panel 2 is for selecting action for account (balance, withdraw, deposit)
    JPanel panel3 = new JPanel(); // panel 3 is for Balance Inquiries
    JPanel panel4 = new JPanel(); // panel 4 is for Withdrawals
    JPanel panel5 = new JPanel(); // panel 5 is for Deposits
    JPanel panel6 = new JPanel(); // panel 6 is for entering withdrawal amount
    JPanel panel7 = new JPanel(); // panel 7 is transaction confirmation
 

    CardLayout cl = new CardLayout();

    JLabel hellonew = new JLabel("you made it");
    JLabel welcomeLabel = new JLabel("Hello");

    JLabel checkingLabel = new JLabel("Checking");
    JLabel savingsLabel = new JLabel("Savings");
    JLabel selectAmount = new JLabel("Select Amount");
    JLabel balanceInquiry = new JLabel("Balance Inquiry");
    JLabel balanceAmount = new JLabel("Balance: ");


    // panel 1 buttons
    JButton checkingButton = new JButton("Checking");
    JButton savingsButton = new JButton("Savings");

    // panel 2 buttons
    JButton balanceButton = new JButton("Balance Inquiry");
    JButton withdrawButton = new JButton("Withdraw");
    JButton depositButton = new JButton("Deposit");

    // panel 4 buttons
    JButton tenButton = new JButton("$10");
    JButton twentyButton = new JButton("$20");
    JButton fourtyButton = new JButton("$40");
    JButton fiftyButton = new JButton("$50");
    JButton hundredButton = new JButton("$100");
    JButton customAmountButton = new JButton("Enter An Amount");

    // utility buttons
    JButton returnDeposiButton = new JButton("Return Deposit");
    JButton backButton = new JButton("Back");
    JButton endButton = new JButton("End");


    Font buttonFont = new Font("Arial", Font.PLAIN, 28); // button font
    CustomerPage(String userID){

        panelContainer.setLayout(cl);

        // set button fonts
        checkingButton.setFont(buttonFont);
        savingsButton.setFont(buttonFont);
        balanceButton.setFont(buttonFont);
        withdrawButton.setFont(buttonFont);
        depositButton.setFont(buttonFont);
        tenButton.setFont(buttonFont);
        twentyButton.setFont(buttonFont);
        fourtyButton.setFont(buttonFont);
        fiftyButton.setFont(buttonFont);
        hundredButton.setFont(buttonFont);
        customAmountButton.setFont(buttonFont);
        backButton.setFont(buttonFont);
        endButton.setFont(buttonFont);

        // add ActionListeners and set button bounds
        checkingButton.addActionListener(this);
        checkingButton.setBounds(125,125,250,80); // position 1

        savingsButton.addActionListener(this);
        savingsButton.setBounds(125,225,250,80); // postion 2
        
        endButton.addActionListener(this);
        endButton.setBounds(600,535,250,80); // end position

        backButton.addActionListener(this);
        backButton.setBounds(125,535,250,80); // back position

        balanceButton.addActionListener(this);
        balanceButton.setBounds(125,125,250,80); // position 1

        withdrawButton.addActionListener(this);
        withdrawButton.setBounds(125,225,250,80); // postion 2

        depositButton.addActionListener(this);
        depositButton.setBounds(125,325,250,80); // position 3

        tenButton.addActionListener(this);
        tenButton.setBounds(125,125,250,80); // position 1

        twentyButton.addActionListener(this);
        twentyButton.setBounds(125,225,250,80); // position 2

        fourtyButton.addActionListener(this);
        fourtyButton.setBounds(125,325,250,80); // position 3

        fiftyButton.addActionListener(this);
        fiftyButton.setBounds(600,125,250,80); // position 4

        hundredButton.addActionListener(this);
        hundredButton.setBounds(600,225,250,80); // position 5

        customAmountButton.addActionListener(this);
        customAmountButton.setBounds(600,325,250,80); // position 6
        



        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Welcome " + userID + "!"); // window title
        mainFrame.add(panelContainer);
        mainFrame.setSize(1000,700);
        mainFrame.setVisible(true);


        
        welcomeLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        welcomeLabel.setFont(buttonFont);
        welcomeLabel.setText("Hello " + userID);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);



        checkingLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        checkingLabel.setFont(buttonFont);
        checkingLabel.setHorizontalAlignment(JLabel.CENTER);
        checkingLabel.setHorizontalTextPosition(JLabel.CENTER);

        savingsLabel.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        savingsLabel.setFont(buttonFont);
        savingsLabel.setHorizontalAlignment(JLabel.CENTER);
        savingsLabel.setHorizontalTextPosition(JLabel.CENTER);

        selectAmount.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        selectAmount.setFont(buttonFont);
        selectAmount.setHorizontalAlignment(JLabel.CENTER);
        selectAmount.setHorizontalTextPosition(JLabel.CENTER);

        balanceInquiry.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        balanceInquiry.setFont(buttonFont);
        balanceInquiry.setHorizontalAlignment(JLabel.CENTER);
        balanceInquiry.setHorizontalTextPosition(JLabel.CENTER);

        balanceAmount.setBounds(0,0,mainFrame.getWidth(),100); // position 0
        balanceAmount.setFont(buttonFont);
        balanceAmount.setHorizontalAlignment(JLabel.CENTER);
        balanceAmount.setHorizontalTextPosition(JLabel.CENTER);

        // add elements to panel 1
        panel1.setLayout(null);
        panel1.add(welcomeLabel);
        panel1.add(checkingButton);
        panel1.add(savingsButton);
        panel1.add(endButton);

        // add elements to panel 2
        panel2.setLayout(null);
        panel2.add(balanceButton);
        panel2.add(withdrawButton);
        panel2.add(depositButton);
        panel2.add(backButton);

        // add elements to panel 3
        panel3.setLayout(null);


        // add elements to panel 4
        panel4.setLayout(null);
        panel4.add(tenButton);
        panel4.add(twentyButton);
        panel4.add(fourtyButton);
        panel4.add(fiftyButton);
        panel4.add(hundredButton);
        panel4.add(customAmountButton);


        // add elements to panel 5
        panel5.setLayout(null);


        // add elements to panel 6
        panel6.setLayout(null);

        
        // add elements to panel 7
        panel7.setLayout(null);



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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == endButton){
            mainFrame.dispose();
            IDandPass idAndPasswords = new IDandPass();
            new LoginPage(idAndPasswords.getLoginInfo());

        }

        if(e.getSource() == checkingButton) {
            System.out.println("done");
            panel2.add(endButton);
            panel2.add(backButton);
            cl.show(panelContainer, "2");
        }

        if(e.getSource() == savingsButton) {
            System.out.println("done");
            panel2.add(endButton);
            panel2.add(backButton);
            cl.show(panelContainer, "2");
        }

        if(e.getSource() == withdrawButton) {
            panel4.add(endButton);
            panel4.add(backButton);
            cl.show(panelContainer, "4");
        }

        if(e.getSource() == backButton) {
            panel1.add(endButton);
            cl.show(panelContainer, "1");
        }
    }
}
