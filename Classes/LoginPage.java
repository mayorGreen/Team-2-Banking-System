package Classes;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
public class LoginPage extends JFrame implements ActionListener{

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JButton endButton = new JButton("End");
    JTextField userIDField = new JTextField();
    JPasswordField userPassField = new JPasswordField();

    ButtonGroup buttons = new ButtonGroup();
    JRadioButton customerButton = new JRadioButton("Customer");
    JRadioButton tellerButton = new JRadioButton("Teller");
    JRadioButton managerButton = new JRadioButton("Manager");
    

    JLabel userIDLabel = new JLabel("userID");
    JLabel userPassLabel = new JLabel("password");
    JLabel messageLabel = new JLabel("Login");

    HashMap<String,String> customerLoginInfo = new HashMap<>();
    HashMap<String,String> tellerLoginInfo = new HashMap<>();
    HashMap<String,String> managerLoginInfo = new HashMap<>();

    private List<Customer> customerList;
    private List<Checking> checkingList;
    private List<SavingsAccount> savingsList;
    private List<CD> cdList;
    private List<Loans> loanList;

    public LoginPage(List<HashMap<String, String>> list, List<Customer> customerList, List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, List<Loans> loanList) {

        // FIRST UPDATE TIME FOR ALL ACCOUNTS
        HelperFunc.updateDate(checkingList, savingsList);
        // save changes
        //HelperFunc.updateChecking(checkingList);
        //HelperFunc.updateSavings(savingsList);

        this.customerList = customerList;
        this.checkingList = checkingList;
        this.savingsList = savingsList;
        this.cdList = cdList;
        this.loanList = loanList;

        customerLoginInfo = list.get(0);
        tellerLoginInfo = list.get(1);
        managerLoginInfo = list.get(2);

        buttons.add(customerButton);
        buttons.add(tellerButton);
        buttons.add(managerButton);

        userIDLabel.setBounds(50,100,75,25);
        userPassLabel.setBounds(50,150,75,25);
        messageLabel.setBounds(0,0,400,50);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 32));

        userIDField.setBounds(125,125,200,25);
        userPassField.setBounds(125,175,200,25);

        resetButton.setBounds(75,250,100,25);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);

        loginButton.setBounds(225,250,100,25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        endButton.setBounds(150,300,100,25);
        endButton.addActionListener(this);
        endButton.setFocusable(false);

        customerButton.setBounds(50,50,100,25);
        customerButton.setHorizontalTextPosition(SwingConstants.TRAILING);
        customerButton.addActionListener(this);

        tellerButton.setBounds(150,50,100,25);
        tellerButton.setHorizontalTextPosition(SwingConstants.TRAILING);
        tellerButton.addActionListener(this);

        managerButton.setBounds(250,50,100,25);
        managerButton.setHorizontalTextPosition(SwingConstants.TRAILING);
        managerButton.addActionListener(this);

        frame.add(customerButton);
        frame.add(tellerButton);
        frame.add(managerButton);
        frame.add(userIDLabel);
        frame.add(userPassLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPassField);

        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(endButton);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == resetButton){
            userIDField.setText("");
            userPassField.setText("");
            messageLabel.setText("Login");
        }
        if(e.getSource() == loginButton && customerButton.isSelected()){
            String userID = userIDField.getText();
            String password = String.valueOf(userPassField.getPassword());

            if(customerLoginInfo.containsKey(userID)){
                if(customerLoginInfo.get(userID).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    frame.dispose();
                    CustomerPage customerPage = new CustomerPage(userID, customerList, checkingList, savingsList, cdList, loanList);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect Password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Incorrect Username");
            }
        }

        if(e.getSource() == loginButton && tellerButton.isSelected()){
            String userID = userIDField.getText();
            String password = String.valueOf(userPassField.getPassword());

            if(tellerLoginInfo.containsKey(userID)){
                if(tellerLoginInfo.get(userID).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    frame.dispose();
                    TellerPage tellerPage = new TellerPage(userID, customerList, checkingList, savingsList, cdList, loanList);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect Password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Incorrect Username");
            }
        }

        if(e.getSource() == loginButton && managerButton.isSelected()){
            String userID = userIDField.getText();
            String password = String.valueOf(userPassField.getPassword());

            if(managerLoginInfo.containsKey(userID)){
                if(managerLoginInfo.get(userID).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    frame.dispose();
                    ManagerPage managerPage = new ManagerPage(userID, customerList, checkingList, savingsList, cdList, loanList);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect Password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Incorrect Username");
            }
        }
        if(e.getSource() == endButton) {
            frame.dispose();
            System.exit(0);
        }
    }
}
