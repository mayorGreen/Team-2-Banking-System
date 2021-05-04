package Classes;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

// This is the class for generating the login page, from here the user may access the atm interface, teller interface, or manager interface
public class LoginPage extends JFrame implements ActionListener{
    
    private List<Customer> customerList; // customer data
    private List<Checking> checkingList; // checking account data
    private List<SavingsAccount> savingsList; // savings account data
    private List<CD> cdList; // CD data
    private List<Loans> loanList; // loan data
    private List<Check> checkList; // check data

    // initialize ui components
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JButton endButton = new JButton("End");
    JTextField userIDField = new JTextField();
    JPasswordField userPassField = new JPasswordField();

    ButtonGroup buttons = new ButtonGroup();
    JButton atmButton = new JButton("ATM");
    JRadioButton tellerButton = new JRadioButton("Teller");
    JRadioButton managerButton = new JRadioButton("Manager");
    
    JLabel userIDLabel = new JLabel("userID");
    JLabel userPassLabel = new JLabel("password");
    JLabel messageLabel = new JLabel("Login");

    HashMap<String,String> customerLoginInfo = new HashMap<>(); // customer login info hashmap
    HashMap<String,String> tellerLoginInfo = new HashMap<>();
    HashMap<String,String> managerLoginInfo = new HashMap<>();

    // constructor accepts data read in from main method
    public LoginPage(List<HashMap<String, String>> list, List<Customer> customerList, List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, List<Loans> loanList, List<Check> checkList) {

        // Update today's date for checking and savings accounts
        HelperFunc.updateDate(checkingList, savingsList);

        // set data objects from read in data
        this.customerList = customerList;
        this.checkingList = checkingList;
        this.savingsList = savingsList;
        this.cdList = cdList;
        this.loanList = loanList;
        this.checkList = checkList;

        // assign login info
        customerLoginInfo = list.get(0);
        tellerLoginInfo = list.get(1);
        managerLoginInfo = list.get(2);

        // add buttons to button group
        buttons.add(tellerButton);
        buttons.add(managerButton);

        // define ui elements
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

        atmButton.setBounds(50,50,80,25);
        atmButton.setHorizontalTextPosition(SwingConstants.CENTER);
        atmButton.addActionListener(this);
        atmButton.setFocusable(false);

        tellerButton.setBounds(150,50,100,25);
        tellerButton.setHorizontalTextPosition(SwingConstants.TRAILING);
        tellerButton.addActionListener(this);

        managerButton.setBounds(250,50,100,25);
        managerButton.setHorizontalTextPosition(SwingConstants.TRAILING);
        managerButton.addActionListener(this);

        // add all elements to the frame
        frame.add(atmButton);
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
    } // end constructor

    // button actions
    @Override
    public void actionPerformed(ActionEvent e){

        // clear data in login and pass fields
        if(e.getSource() == resetButton){
            userIDField.setText("");
            userPassField.setText("");
            messageLabel.setText("Login");
        }

        // send user to atm interface, pass the database data to the atm and dispose of this window
        if(e.getSource() == atmButton){
            frame.dispose();
            CustomerPage customerPage = new CustomerPage(customerList, checkingList, savingsList, cdList, loanList, checkList);
        }

        // send user to teller interface, pass the database data to the teller and dispose of this window
        if(e.getSource() == loginButton && tellerButton.isSelected()){
            // get data in userID and password fields
            String userID = userIDField.getText();
            String password = String.valueOf(userPassField.getPassword());

            // verify login info against given values -- if valid, send user to teller page, else display error
            if(tellerLoginInfo.containsKey(userID)){
                if(tellerLoginInfo.get(userID).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    frame.dispose();
                    TellerPage tellerPage = new TellerPage(userID, customerList, checkingList, savingsList, cdList, loanList, checkList);
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect Password");
                }
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Incorrect Username");
            }
        }

        // send user to manager interface, pass the database data to the manager and dispose of this window
        if(e.getSource() == loginButton && managerButton.isSelected()){
            // get data in userID and password fields
            String userID = userIDField.getText();
            String password = String.valueOf(userPassField.getPassword());

            // verify login info against given values -- if valid, send user to manager page, else display error
            if(managerLoginInfo.containsKey(userID)){
                if(managerLoginInfo.get(userID).equals(password)){
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    frame.dispose();
                    ManagerPage managerPage = new ManagerPage(userID, customerList, checkingList, savingsList, cdList, loanList, checkList);
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
    } // end button actions
} // end LoginPage