package Classes;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
// TODO add radio button to select from checking/savings account when inputting account number

public class TellerPage extends JFrame implements ActionListener {
	
	private List<Customer> customerList; // customer data
    private List<Checking> checkingList; // checking account data
    private List<SavingsAccount> savingsList; // savings account data
    private List<CD> cdList; // CD data
    private List<Loans> loanList; // loan data
	private List<Check> checkList; // check data
	private List<String> accountList = new ArrayList<>(); // account list for this customer id

	private int workingAcctNum; // currently selected account's account number
    private String workingAcctType; // currently selected account's type. i.e. Checking, Savings

	// dummy data for transaction history
	String[] listTestData = {"Transaction 1   -    $20.23", 
							"Transaction 2   -    $122.30", 
							"Transaction 3   -    $25.55",
							"Transaction 4   -    $1.99",
							"Transaction 5   -    $82.05",
							"Transaction 6   -    $50.00",
							"Transaction 7   -    $12.63",
							"Transaction 8   -    $8.69",
							"Transaction 9   -    $4.20",
							"Transaction 10   -    $15.78",
							"Transaction 11   -    $1.22",
							"Transaction 12   -    $150.33",
							"Transaction 13   -    $3.36",
							"Transaction 14   -    $27.39",
							"Transaction 15   -    $222.36",
							"Transaction 16   -    $19.99",
							"Transaction 17   -    $10.00",
							"Transaction 18   -    $14.67",
							"Transaction 19   -    $28.31",
							"Transaction 20   -    $93.12",
							"Transaction 21   -    $12.22",
							"Transaction 22   -    $44.21",
							"Transaction 23   -    $56.36",
							"Transaction 24   -    $7.14",
							"Transaction 25   -    $5.23"};

	JFrame mainFrame = new JFrame();
	JPanel panelContainer = new JPanel(); // panel container
	JPanel lookupPanel = new JPanel(); // customer id lookup panel
	JPanel accountSelectPanel = new JPanel(); // account selection panel
	JPanel panel1 = new JPanel(); // Teller landing page. enter acct number lookup here.
	JPanel panel2 = new JPanel(); // Account Lookup Successful
	JPanel panel3 = new JPanel(); // stop payment page
	JPanel panel4 = new JPanel(); // balance inquiry page
	JPanel panel5 = new JPanel(); // debits inquiry page
	JPanel panel6 = new JPanel(); // credit account page
	JPanel panel7 = new JPanel(); // credit check to account page
	JPanel panel8 = new JPanel(); // debit account page
	JPanel panel9 = new JPanel(); // account transfer page
	JPanel panel10 = new JPanel(); // confirmation page

	JPanel[] panels = {panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,lookupPanel,accountSelectPanel};

	// utility elements
	JLabel bankNameLabel = new JLabel("My Bank");
	JButton backButton = new JButton("Back"); // sends teller back to option panel 2
	JButton exitButton = new JButton("Exit"); // sends teller back to account lookup
	JButton logoutButton = new JButton("Logout"); // logs teller out

	// lookupPanel -- teller looks up custoemr id
	JLabel custIDLabel = new JLabel("Enter Customer ID");
	JTextField custIdField = new JTextField();
	JButton custIDSubmitButton = new JButton("Submit");
	String[] accountArray;
	ArrayList<String[]> accounts;
	
	// acountSelectPanel
	JList<String> accountJList = new  JList<>();
	JScrollPane acctScrollPane = new JScrollPane(accountJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	JButton accountSubmitButton = new JButton("Submit");

	// panel 1 elements -- teller login
	JScrollPane accountsListPane = new JScrollPane();
	JTextField acctNumLookupField = new JTextField();
	JButton acctNumLookupButton = new JButton("Submit");
	JLabel acctNumLookupFailLabel = new JLabel(); // used if account number lookup fails

	// panel 2 elements -- lookup successful
	JButton stopPaymentButton = new JButton("Stop Payment");
	JButton balanceInquiryButton = new JButton("Balance Inquiry");
	JButton debitsInquiryButton = new JButton("Debits Inquiry");
	JButton creditAccountButton = new JButton("Credit Account");
	JButton debitAccountButton = new JButton("Debit Account");
	JButton transferCashButton = new JButton("Transfer Cash");

	// panel 3 elements -- stop payment
	JLabel stopPaymentLabel = new JLabel("Stop Payment");
	JLabel stopCheckNumLabel = new JLabel("Check #");
	JTextField stopCheckNumField = new JTextField();
	JButton submitStopButton = new JButton("Submit");

	// panel 4 elements -- balance inquiry
	JLabel balanceInquiryLabel = new JLabel("Balance Inquiry");
    JLabel balanceAmountLabel = new JLabel("Balance: $0.00");

	// panel 5 elements -- debits inquiry
	JList<String> debitList = new JList<>();
	JLabel debitListLabel = new JLabel("Debits Inquiry");
	JScrollPane scrollPane = new JScrollPane(debitList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	// panel 6 elements -- credit account
	JLabel enterCreditAmtLabel = new JLabel("Enter Credit Amount:");
	JLabel creditAcctLabel = new JLabel("Credit Account");
	JTextField creditAmountField = new JTextField();
	JButton checkDepositButton = new JButton("Deposit Check Instead");
	JButton creditSubmitButton = new JButton("Submit");

	// panel 7 elements -- credit check to account
	JLabel checkDepositLabel = new JLabel("Check Deposit");
	JLabel checkAccountLabel = new JLabel("Account Number:");
	JLabel checkRoutingLabel = new JLabel("Routing Number:");
	JLabel checkAmtLabel = new JLabel("Amount on Check:");
	JLabel checkNumLabel =new JLabel("Check Number:");
	JTextField checkAccountField = new JTextField();
	JTextField checkRoutingField = new JTextField();
	JTextField checkAmountField = new JTextField();
	JTextField checkNumberField = new JTextField();
	JButton checkDepositSubmitButton = new JButton("Submit");

	// panel 8 elements -- debit account
	JLabel debitAmountLabel = new JLabel("Debit Amount:");
	JTextField debitAmountField = new JTextField();
	JButton debitSubmitButton = new JButton("Submit");

	// panel 9 account transfer
	JLabel transferLabel = new JLabel("Cash Transfer");
	JLabel fromAcctLabel = new JLabel("From Account #:");
	JLabel toAcctLabel = new JLabel("To Account #:");
	JLabel transferAmountLabel = new JLabel("Amount:");
	JTextField transferAmountField = new JTextField();
	JButton transferSubmitButton = new JButton("Submit");
	JComboBox<String> selectFromAccount = new JComboBox<>();
	JComboBox<String> selectToAccount = new JComboBox<>();

	// panel 10 -- transaction complete, return to panel 2
	JLabel confirmationLabel = new JLabel("Transction Complete");
	
	// common button positions and dimensions
	Rectangle header = new Rectangle(300,25,400,100);
	Rectangle bottomRight = new Rectangle(600,535,250,80);
	Rectangle bottomLeft = new Rectangle(125,535,250,80);
	Rectangle center = new Rectangle(400,250,200,50);

	// buttons array for easy modification
	JButton[] buttons = {backButton,exitButton,logoutButton,acctNumLookupButton,
						stopPaymentButton,balanceInquiryButton,
						debitsInquiryButton,creditAccountButton,
						debitAccountButton,transferCashButton,
						submitStopButton,checkDepositButton,
						creditSubmitButton,checkDepositSubmitButton,
						debitSubmitButton,transferSubmitButton,
						custIDSubmitButton,accountSubmitButton};

	
	CardLayout cl = new CardLayout();
	Font labelFont = new Font("Arial", Font.PLAIN, 28); // default label font
	Font buttonFont = new Font("Arial", Font.PLAIN, 26); // default button font

	// constructor
    TellerPage(String userID, List<Customer> customerList, List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, List<Loans> loanList, List<Check> checkList){

		this.customerList = customerList;
        this.checkingList = checkingList;
        this.savingsList = savingsList;
        this.cdList = cdList;
        this.loanList = loanList;
		this.checkList = checkList;

		panelContainer.setLayout(cl);

		// assign panel layouts
		for(JPanel panel : panels){
			panel.setLayout(null);
		}

		// set frame properties
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setTitle("Welcome " + userID + "!"); // window title
		mainFrame.add(panelContainer);
		mainFrame.setSize(1000,700);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);

		// set button fonts, action listeners
		for(JButton button : buttons){
			button.setFont(buttonFont);
			button.setFocusable(false);
			button.addActionListener(this);
		}

		// define utility elements
		exitButton.setBounds(bottomRight);

		// define lookuppanel
		custIDLabel.setBounds(header);
		custIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custIDLabel.setFont(labelFont);
		custIdField.setBounds(center);
		custIDSubmitButton.setBounds(bottomRight);
		// add elements to lookuppanel
		lookupPanel.add(custIDLabel);
		lookupPanel.add(custIdField);
		lookupPanel.add(custIDSubmitButton);

		// define account select panel
		acctScrollPane.setBounds(100,100,800,400);
		accountSubmitButton.setBounds(bottomRight);
		// add elements to account select panel
		accountSelectPanel.add(acctScrollPane);
		accountSelectPanel.add(accountSubmitButton);

		// define panel 1 elements
		bankNameLabel.setBounds(header);
		bankNameLabel.setFocusable(true);
		bankNameLabel.setFont(labelFont);
		bankNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		acctNumLookupField.setForeground(Color.gray);
		acctNumLookupField.setText("Enter Account Number");
		acctNumLookupField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				JTextField source = (JTextField)e.getComponent();
				source.setForeground(Color.black);
				source.setText("");
				source.removeFocusListener(this);
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				return;
			}
		});
		acctNumLookupField.setBounds(center);
		acctNumLookupField.setFont(new Font("Arial", Font.PLAIN, 16));
		logoutButton.setBounds(bottomLeft);
		backButton.setBounds(bottomLeft);
		acctNumLookupButton.setBounds(bottomRight);
		// add elements to panel 1
		panel1.add(bankNameLabel);
		panel1.add(acctNumLookupField);
		panel1.add(acctNumLookupButton);
		panel1.add(acctNumLookupFailLabel);
		panel1.add(logoutButton);

		// define panel 2 elements -- this is the navigation panel
		stopPaymentButton.setBounds(125,150,250,80);
		balanceInquiryButton.setBounds(125,250,250,80);
		debitsInquiryButton.setBounds(125,350,250,80);
		creditAccountButton.setBounds(600,150,250,80);
		debitAccountButton.setBounds(600,250,250,80);
		transferCashButton.setBounds(600,350,250,80);
		// add elements to panel 2
		panel2.add(stopPaymentButton);
		panel2.add(balanceInquiryButton);
		panel2.add(debitsInquiryButton);
		panel2.add(creditAccountButton);
		panel2.add(debitAccountButton);
		panel2.add(transferCashButton);
		panel2.add(exitButton);
		
		// define panel 3 elements
		stopPaymentLabel.setFont(labelFont);
		stopPaymentLabel.setBounds(header);
		stopPaymentLabel.setFocusable(true);
		stopPaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stopCheckNumLabel.setFont(labelFont);
		stopCheckNumLabel.setBounds(200,250,175,50);
		stopCheckNumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		stopCheckNumField.setBounds(center);
		submitStopButton.setBounds(bottomRight);
		// add elements to panel 3
		panel3.add(stopCheckNumLabel);
		panel3.add(stopPaymentLabel);
		panel3.add(stopCheckNumField);
		panel3.add(submitStopButton);

		// define panel 4 elements
		balanceInquiryLabel.setFont(labelFont);
		balanceInquiryLabel.setBounds(header);
		balanceInquiryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		balanceAmountLabel.setBounds(300,250,400,50);
		balanceAmountLabel.setFont(labelFont);
		balanceAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// add elements to panel 4
		panel4.add(balanceInquiryLabel);
		panel4.add(balanceAmountLabel);

		// define panel 5 elements
		debitListLabel.setFont(labelFont);
		debitListLabel.setBounds(header);
		debitListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		debitList.setListData(listTestData);
		scrollPane.setBounds(100,125,800,300);
		// add elements to panel 5
		panel5.add(scrollPane);
		panel5.add(debitListLabel);

		// define panel 6 elements
		enterCreditAmtLabel.setFont(labelFont);
		enterCreditAmtLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enterCreditAmtLabel.setBounds(center);
		creditAcctLabel.setFont(labelFont);
		creditAcctLabel.setBounds(header);
		creditAcctLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditAmountField.setBounds(center);
		checkDepositButton.setBounds(350,350,300,50);
		creditSubmitButton.setBounds(bottomRight);
		// add elements to panel 6
		panel6.add(creditAcctLabel);
		panel6.add(creditAmountField);
		panel6.add(checkDepositButton);
		panel6.add(creditSubmitButton);
		panel6.add(enterCreditAmtLabel);

		// define panel 7 elements
		checkAmtLabel.setFont(labelFont);
		checkNumLabel.setFont(labelFont);
		checkDepositLabel.setFont(labelFont);
		checkAccountLabel.setFont(labelFont);
		checkRoutingLabel.setFont(labelFont);
		checkAccountLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		checkRoutingLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		checkAmtLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		checkNumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		checkDepositLabel.setHorizontalAlignment(SwingConstants.CENTER);
		checkDepositLabel.setBounds(header);
		checkAccountField.setBounds(400,50,200,50);
		checkAccountLabel.setBounds(100,50,275,50);
		checkRoutingField.setBounds(400,150,200,50);
		checkRoutingLabel.setBounds(100,150,275,50);
		checkAmountField.setBounds(400,250,200,50);
		checkNumberField.setBounds(400,350,200,50);
		checkAmtLabel.setBounds(100,250,275,50);
		checkNumLabel.setBounds(100,350,275,50);
		checkDepositSubmitButton.setBounds(bottomRight);
		// add elements to panel 7
		panel7.add(checkAccountField);
		panel7.add(checkAccountLabel);
		panel7.add(checkRoutingField);
		panel7.add(checkRoutingLabel);
		panel7.add(checkAmtLabel);
		panel7.add(checkAmountField);
		panel7.add(checkNumLabel);
		panel7.add(checkNumberField);
		panel7.add(checkDepositLabel);
		panel7.add(checkDepositSubmitButton);

		// define panel 8 elements
		debitSubmitButton.setBounds(bottomRight);
		debitAmountLabel.setBounds(100,250,275,50);
		debitAmountLabel.setFont(labelFont);
		debitAmountLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		debitAmountField.setBounds(center);
		// add elements to panel 8
		panel8.add(debitAmountLabel);
		panel8.add(debitAmountField);
		panel8.add(debitSubmitButton);

		// define panel 9 elements
		transferLabel.setFont(labelFont);
		fromAcctLabel.setFont(labelFont);
		toAcctLabel.setFont(labelFont);
		transferAmountLabel.setFont(labelFont);
		fromAcctLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		toAcctLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		transferAmountLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		transferLabel.setHorizontalAlignment(SwingConstants.CENTER);
		transferLabel.setBounds(header);
		fromAcctLabel.setBounds(100,225,275,50);
		toAcctLabel.setBounds(100,300,275,50);
		transferAmountLabel.setBounds(100,375,275,50);
		transferSubmitButton.setBounds(bottomRight);
		transferAmountField.setBounds(400,375,200,50);;
		// combo boxes
		selectFromAccount.setBounds(400,225,200,50);
		selectFromAccount.addActionListener(this);
		selectFromAccount.setVisible(true);
		selectToAccount.setBounds(400,300,200,50);
		selectToAccount.addActionListener(this);
		selectToAccount.setVisible(true);
		// add elements to panel 9
		panel9.add(transferLabel);
		panel9.add(fromAcctLabel);
		panel9.add(toAcctLabel);
		panel9.add(transferAmountLabel);
		panel9.add(transferAmountField);
		panel9.add(transferSubmitButton);
		panel9.add(selectFromAccount);
		panel9.add(selectToAccount);
		
		// define panel 10 elements
		confirmationLabel.setFont(labelFont);
		confirmationLabel.setBounds(300,200,400,150);
		confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// add elements to panel 10
		panel10.add(confirmationLabel);

		// add panels to container
		int i = 1;
		for(JPanel panel : panels){
			panelContainer.add(panel, ("" + i));
			i++;
		}

		cl.show(panelContainer, "11");

    } // end constructor

	// button actions
	@Override
	public void actionPerformed(ActionEvent e) {

		// lookup customer id, get accounts
		if((e.getSource() == custIDSubmitButton) && !(custIdField.getText().equals(""))){
			accountList = HelperFunc.accountsLookup(checkingList, savingsList, custIdField.getText());
			accountArray = new String[accountList.size()];
			accounts = new ArrayList<>();
			for(int i=0; i<accountList.size(); i++){
				accountArray[i] = accountList.get(i);
				String[] values = accountArray[i].split(" ");
				accounts.add(values);
			}
			accountJList.setListData(accountArray);
			cl.show(panelContainer, "12");
		}

		// grab data from selected account
		if(e.getSource() == accountSubmitButton){
			int accountSelectIndex = accountJList.getSelectedIndex();
			if(accountSelectIndex != -1){
				System.out.println(accounts.get(accountSelectIndex)[0]);
				workingAcctType = accounts.get(accountSelectIndex)[0]; // set selected account type
				System.out.println(accounts.get(accountSelectIndex)[1]);
				workingAcctNum = Integer.parseInt(accounts.get(accountSelectIndex)[1]); // set selected accout num
				cl.show(panelContainer, "2");
			}
		}

		// logout button pressed
		if(e.getSource() == logoutButton) {
			mainFrame.dispose();
            IDandPass idAndPasswords = new IDandPass(customerList);
            new LoginPage(idAndPasswords.getLoginInfo(), customerList, checkingList, savingsList, cdList, loanList, checkList);
		}
		
		// checks account number, if valid, move to panel 2 options screen
		if(e.getSource() == acctNumLookupButton) {
			panel2.add(bankNameLabel);
			cl.show(panelContainer, "2");
		}

		// back button pressed
		if(e.getSource() == backButton) {
			cl.show(panelContainer, "2");
		}

		// exit button pressed, go back to account lookup
		if(e.getSource() == exitButton) {
			panel1.add(bankNameLabel);
			acctNumLookupField.setForeground(Color.gray);
			acctNumLookupField.setText("Enter Account Number");
			acctNumLookupField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e){
					JTextField source = (JTextField)e.getComponent();
					source.setForeground(Color.black);
					source.setText("");
					source.removeFocusListener(this);
				}
				@Override
				public void focusLost(FocusEvent e) {
					return;
				}
			});
			custIdField.setText("");
			cl.show(panelContainer, "11");
		}

		// panel 2 buttons
		// go to stop payment page
		if(e.getSource() == stopPaymentButton){
			panel3.add(backButton);
			cl.show(panelContainer, "3");
		}

		// get account balance, send to balance inquiry page
		if(e.getSource() == balanceInquiryButton){
			panel4.add(backButton);
			if(workingAcctType.equals("Checking")){
				balanceAmountLabel.setText("Balance: $" + HelperFunc.getCheckingBalance(checkingList, workingAcctNum));
			} else if(workingAcctType.equals("Savings")){
				balanceAmountLabel.setText("Balance: $" + HelperFunc.getSavingsBalance(savingsList, workingAcctNum));
			}
			cl.show(panelContainer, "4");
		}

		// send to debit inquiry page
		if(e.getSource() == debitsInquiryButton) {
			panel5.add(backButton);
			cl.show(panelContainer, "5");
		}

		// send to credit account page
		if(e.getSource() == creditAccountButton) {
			panel6.add(backButton);
			cl.show(panelContainer, "6");
		}
		
		// send to debit account page
		if(e.getSource() == debitAccountButton) {
			panel8.add(backButton);
			cl.show(panelContainer, "8");
		}

		// send to transfer cash page
		if(e.getSource() == transferCashButton) {
			// repopulate combo boxes
			selectFromAccount.removeAllItems();
			selectToAccount.removeAllItems();
			for(int i = 0; i<accountList.size(); i++){
				selectFromAccount.addItem(accountList.get(i));
				selectToAccount.addItem(accountList.get(i));
			}
			panel9.add(backButton);
			cl.show(panelContainer, "9");
		}

		// panel 3 buttons (stop payment)
		if(e.getSource() == submitStopButton) {
			if(HelperFunc.isParsableNumber(stopCheckNumField.getText())){
				HelperFunc.stopCheck(checkList, workingAcctNum, stopCheckNumField.getText(), workingAcctType);
				HelperFunc.updateChecks(checkList); // save changes
				// send teller to confirmation page
				panel10.add(backButton);
				cl.show(panelContainer, "10");
			}
		}

		// panel 4 buttons (balance inquiry) N/A

		// panel 5 buttons (debits inquiry) N/A

		// panel 6 buttons (credit account page)
		// send to check deposit page
		if(e.getSource() == checkDepositButton) {
			creditAmountField.setText("");
			panel7.add(backButton);
			cl.show(panelContainer, "7");
		}

		// credit account for amount in field
		if(e.getSource() == creditSubmitButton) {
			// check if amt in box > 0
			// credit account for amount in box
			if(HelperFunc.isParsableNumber(creditAmountField.getText())){
				double creditAmt = Double.parseDouble(creditAmountField.getText());
				if(workingAcctType.equals("Checking")){
					HelperFunc.creditCheckingAccount(checkingList, workingAcctNum, creditAmt);
					HelperFunc.updateChecking(checkingList); // save changes
				} else if (workingAcctType.equals("Savings")){
					HelperFunc.creditSavingsAccount(savingsList, workingAcctNum, creditAmt);
					HelperFunc.updateSavings(savingsList); // save changes
				}
				panel10.add(backButton);
				cl.show(panelContainer, "10");
			} else {
				JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only", "Parse Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// panel 7 buttons (credit check to account page)
		if(e.getSource() == checkDepositSubmitButton) {
			// check if amount > 0
			// credit account for amount on check
			String checkAcct = checkAccountField.getText();
			String routingNum = checkRoutingField.getText();
			String checkAmt = checkAmountField.getText();
			String checkNum = checkNumberField.getText();
			boolean parsable = true;
			parsable = HelperFunc.isParsableNumber(checkAcct);
			if(parsable) parsable = HelperFunc.isParsableNumber(routingNum);
			if(parsable) parsable = HelperFunc.isParsableNumber(checkAmt);
			if(parsable) parsable = HelperFunc.isParsableNumber(checkNum);
			if(parsable) {
				HelperFunc.createCheck(checkList, Integer.parseInt(checkAcct), Integer.parseInt(routingNum), Integer.parseInt(checkNum),
										Double.parseDouble(checkAmt), workingAcctNum, workingAcctType);
				HelperFunc.updateChecks(checkList); // save changes

				// send to confirmation page
				panel10.add(backButton);
				cl.show(panelContainer, "10");
			}
		}

		// panel 8 buttons (debit account)
		if(e.getSource() == debitSubmitButton) {
			// check if amount > 0
			// debit account for the amount
			if(HelperFunc.isParsableNumber(debitAmountField.getText())){
				double depositAmount = Double.parseDouble(debitAmountField.getText());
				if(workingAcctType.equals("Checking")){
					HelperFunc.debitCheckingAccount(checkingList, workingAcctNum, depositAmount);
					HelperFunc.updateChecking(checkingList); // save changes
				} else if (workingAcctType.equals("Savings")){
					HelperFunc.debitSavingsAccount(savingsList, workingAcctNum, depositAmount);
					HelperFunc.updateSavings(savingsList); // save changes
				}
			}
			// send to confirmation page
			debitAmountField.setText("");
			panel10.add(backButton);
			cl.show(panelContainer, "10");
		}

		// panel 9 button (transfer page)
		if(e.getSource() == transferSubmitButton) {
			// get combo box selections and perform transfer
			int fromAccountSelected = selectFromAccount.getSelectedIndex();
			int toAccountSelected = selectToAccount.getSelectedIndex();
			if(fromAccountSelected != -1 && toAccountSelected != -1 && !transferAmountField.getText().equals("")){
				if(HelperFunc.isParsableNumber(transferAmountField.getText())){
					String fromAccountType = accounts.get(fromAccountSelected)[0]; // get source account type
					int fromAccountNum = Integer.parseInt(accounts.get(fromAccountSelected)[1]); // get source account number

					String toAccountType = accounts.get(toAccountSelected)[0];// get destination account type
					int toAccountNum = Integer.parseInt(accounts.get(toAccountSelected)[1]); // destination account number
					
					String msg = HelperFunc.transferMoney(checkingList, savingsList, 
						fromAccountType, fromAccountNum,
						toAccountType, toAccountNum, 
						Double.parseDouble(transferAmountField.getText()));
					// add updates
					HelperFunc.updateChecking(checkingList); // save changes
					HelperFunc.updateSavings(savingsList); // save changes
					String[] options = {"OK", "Back"};
					int input = JOptionPane.showOptionDialog(null, msg, "Transfer Status", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

					if(input == JOptionPane.OK_OPTION){
						// send back a page
						cl.show(panelContainer, "2");
						transferAmountField.setText("");
					} else {
						transferAmountField.setText("");
					}
				}
			}
		} // end cash transfer action
		
	} // end button actions
    
} // end TellerPage