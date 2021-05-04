package Classes;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;

// This is the manager page, from here the manager can create new accounts, customers, as well as manage customer accounts
public class ManagerPage extends JFrame implements ActionListener {

	private List<Customer> customerList; // customer data
    private List<Checking> checkingList; // checking account data
    private List<SavingsAccount> savingsList; // savings account data
    private List<CD> cdList; // CD data
    private List<Loans> loanList; // loan data
	private List<Check> checkList; // check data
	private List<String> accountList = new ArrayList<>(); // account list

	private int workingAcctNum; // currently selected account's account number
    private String workingAcctType; // currently selected account's type. i.e. Checking, Savings

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
	JPanel newCustomerPanel = new JPanel(); // create new customer panel
	JPanel newAccountPanel = new JPanel(); // create new account select panel
	JPanel newCCPanel = new JPanel(); // create new credit card panel
	JPanel newCheckingPanel = new JPanel(); // create new checking account panel
	JPanel newSavingsPanel = new JPanel(); // create new savings account panel
	JPanel newCDPanel = new JPanel(); // create new CD account panel
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
    JPanel panel11 = new JPanel(); // create short term loan page
    JPanel panel12 = new JPanel(); // create long term loan page
    JPanel panel13 = new JPanel(); // loan payment page

	JPanel[] panels = {panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10,panel11,panel12,panel13,
						lookupPanel,accountSelectPanel,newCustomerPanel,newAccountPanel,newCCPanel,newCheckingPanel,newSavingsPanel,newCDPanel};

	JLabel bankNameLabel = new JLabel("My Bank");
	JButton backButton = new JButton("Back"); // sends manager back to option panel 2
	JButton exitButton = new JButton("Exit"); // sends manager back to account lookup
	JButton logoutButton = new JButton("Logout"); // logs manager out
	JButton lookupReturnButton = new JButton("Back"); // returns manager to account lookup page
	
	// lookupPanel elements -- manager looks up custoemr id
	JLabel custIDLabel = new JLabel("Enter Customer ID");
	JTextField custIdField = new JTextField();
	JButton custIDSubmitButton = new JButton("Submit");
	String[] accountArray;
	ArrayList<String[]> accounts;
	JButton createCustomerButton = new JButton("New Customer");	// create new cust
	JButton createAccountButton = new JButton("New Account");// create new acct
	JButton processChecksButton = new JButton("Process Checks");// process checks
	
	// newAccountPanel elements
	JButton shortTermLoanButton = new JButton("Short Term Loan");
	JButton longTermLoanButton = new JButton("Long Term Loan");
	JButton creditCardButton = new JButton("Credit Card");
	JButton checkingButton = new JButton("Checking Account");
	JButton savingsButton = new JButton("Savings Account");
	JButton cdButton = new JButton("CD Account");

	// newCheckingPanel elements
	JLabel chCustIDLabel = new JLabel("Customer ID:");	
	JLabel chAccountNumLabel = new JLabel("Account Number:");
	JLabel chBalanceLabel = new JLabel("Balance:");
	JLabel chInterestRateLabel = new JLabel("Interest Rate:");
	JLabel chBackupAccountLabel = new JLabel("Backup Account Number:");
	JCheckBox assignBackupAccount = new JCheckBox("Assign Backup Account?");
	JTextField chCustIDField = new JTextField();
	JTextField chAccountNumField = new JTextField();
	JTextField chBalanceField = new JTextField();
	JTextField chInterestRateField = new JTextField();
	JTextField chBackupAccountField = new JTextField();
	JButton newCheckingSubmitButton = new JButton("Submit");

	// newSavingsPanel elements
	JLabel saCustIDLabel = new JLabel("Customer ID:");	
	JLabel saAccountNumLabel = new JLabel("Account Number:");
	JLabel saBalanceLabel = new JLabel("Balance:");
	JLabel saInterestRateLabel = new JLabel("Interest Rate:");
	JTextField saCustIDField = new JTextField();
	JTextField saAccountNumField = new JTextField();
	JTextField saBalanceField = new JTextField();
	JTextField saInterestRateField = new JTextField();
	JButton newSavingsSubmitButton = new JButton("Submit");

	// newCDPanel elements
	JLabel cdCustIDLabel = new JLabel("Customer ID:");	
	JLabel cdAccountNumLabel = new JLabel("Account Number:");
	JLabel cdDueDateLabel = new JLabel("Due Date (MM/dd/yyyy):");
	JLabel cdBalanceLabel = new JLabel("Balance:");
	JLabel cdInterestRateLabel = new JLabel("Interest Rate:");
	JTextField cdCustIDField = new JTextField();
	JTextField cdAccountNumField = new JTextField();
	JTextField cdDueDateField = new JTextField();
	JTextField cdBalanceField = new JTextField();
	JTextField cdInterestRateField = new JTextField();
	JButton newCDSubmitButton = new JButton("Submit");

	// newCustomerPanel elements -- enter customer data here, add to customer list
	JLabel ssnLabel = new JLabel("SSN:");
	JLabel streetAddressLabel = new JLabel("Street Address:");
	JLabel cityLabel = new JLabel("City:");
	JLabel stateLabel = new JLabel("State:");
	JLabel zipLabel = new JLabel("Zip Code:");
	JLabel firstNameLabel = new JLabel("First Name:");
	JLabel lastNameLabel = new JLabel("Last Name:");
	JTextField ssnField = new JTextField();
	JTextField streetAddressField = new JTextField();
	JTextField cityField = new JTextField();
	JTextField stateField = new JTextField();
	JTextField zipField = new JTextField();
	JTextField firstNameField = new JTextField();
	JTextField lastNameField = new JTextField();
	JButton createCustomerSubmitButton = new JButton("Submit");

	// newCCPanel elements
	JLabel ccLoanIDLabel = new JLabel("Loan ID:");
	JLabel ccCustomerIDLabel = new JLabel("Customer ID:");
	JLabel ccInterestRateLabel = new JLabel("Interest Rate");
	JLabel ccLimitLabel = new JLabel("Card Limit:");
	JTextField ccLoanIDField = new JTextField();
	JTextField ccCustomerIDField = new JTextField();
	JTextField ccInterestRateField = new JTextField();  
	JTextField ccLimitField = new JTextField();
	JButton newCCSubmitButton = new JButton("Submit");

	// acountSelectPanel elements
	JList<String> accountJList = new  JList<>();
	JScrollPane acctScrollPane = new JScrollPane(accountJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
	ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
    JButton loanPaymentButton = new JButton("LoanPayment");
	
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
	JScrollPane scrollPane = new JScrollPane(debitList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
	ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
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
	JComboBox<String> selectToAccount = new JComboBox<>();	// panel 10 -- transaction complete, return to panel 2
	JLabel confirmationLabel = new JLabel("Transction Complete");
	
    // panel 11 -- new short term loan page
	JLabel shortLoanIDLabel = new JLabel("Loan ID:");
	JLabel amountBorrowedLabel = new JLabel("Amount Borrowed:");
	JLabel interestRateLabel = new JLabel("Interest Rate:");
	JLabel paymentPlanLabel = new JLabel("Years to repay:");
	JLabel shortLoanCustIDLabel = new JLabel("Customer ID");
	JTextField shortLoanIDField = new JTextField();
	JTextField amountBorrowedField = new JTextField();
	JTextField interestRateField = new JTextField();
	JTextField paymentPlanField = new JTextField();
	JTextField shortLoanCustIDField = new JTextField();
	JButton shortLoanSubmitButton = new JButton("Submit");
	
    // panel 12 -- long term loan page
	JLabel longLoanIDLabel = new JLabel("Loan ID:");
	JLabel longAmountBorrowedLabel = new JLabel("Amount Borrowed");
	JLabel loanTermSelectLabel = new JLabel("Select Loan Term:");
	JRadioButton fifteenButton = new JRadioButton("15yr");
	JRadioButton thirtyButton = new JRadioButton("30yr");
	ButtonGroup loanButtons = new ButtonGroup();
	JLabel longInterestRateLabel = new JLabel("Interest Rate:");
	JLabel longLoanCustIDLabel = new JLabel("Customer ID:");
	JTextField longLoanIDField = new JTextField();
	JTextField longAmountBorrowedField = new JTextField();
	JTextField longInterestRateField = new JTextField();
	JTextField longLoanCustIDField = new JTextField();
	JButton longLoanSubmitButton = new JButton("Submit");
	
    // panel 13 -- loan payment page
	JScrollPane loanListWindow = new JScrollPane();
	JLabel loanPaymentAmtLabel = new JLabel("Enter Payment Amount");
	JTextField loanPaymentAmtField = new JTextField();
	JButton loanCompletePaymentButton = new JButton("Submit");
	
	// common button positions and dimensions
	Rectangle header = new Rectangle(300,25,400,100);
	Rectangle bottomRight = new Rectangle(600,535,250,80);
	Rectangle bottomLeft = new Rectangle(125,535,250,80);
	Rectangle center = new Rectangle(400,250,200,50);
	
	// button array for easy modification
	JButton[] buttons = {backButton,exitButton,logoutButton,acctNumLookupButton,
		stopPaymentButton,balanceInquiryButton,
		debitsInquiryButton,creditAccountButton,
		debitAccountButton,transferCashButton,
		submitStopButton,checkDepositButton,
		creditSubmitButton,checkDepositSubmitButton,
		debitSubmitButton,transferSubmitButton,
		shortTermLoanButton,longTermLoanButton,
		loanPaymentButton,shortLoanSubmitButton,
		longLoanSubmitButton,loanCompletePaymentButton,
		custIDSubmitButton,accountSubmitButton,
		createCustomerButton,createAccountButton,
		createCustomerSubmitButton,lookupReturnButton,
		processChecksButton,newCCSubmitButton,
		creditCardButton,newCheckingSubmitButton,
		newSavingsSubmitButton,newCDSubmitButton,
		checkingButton,savingsButton,
		cdButton};
		
	CardLayout cl = new CardLayout();
	Font labelFont = new Font("Arial", Font.PLAIN, 28); // default label font
	Font buttonFont = new Font("Arial", Font.PLAIN, 26); // default button font

	// constructor
	ManagerPage(String userID, List<Customer> customerList, List<Checking> checkingList, List<SavingsAccount> savingsList, List<CD> cdList, List<Loans> loanList, List<Check> checkList){

		// save all data on window close
		mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HelperFunc.updateChecking(checkingList);
                HelperFunc.updateSavings(savingsList);
                HelperFunc.updateCD(cdList);
                HelperFunc.updateCustomers(customerList);
                HelperFunc.updateChecks(checkList);
                HelperFunc.updateLoans(loanList);
                System.out.println("Closing");
                e.getWindow().dispose();
            }
        });
			
		this.customerList = customerList;
		this.checkingList = checkingList;
		this.savingsList = savingsList;
		this.cdList = cdList;
		this.loanList = loanList;
		this.checkList = checkList;
		
		// add buttons to button group
		loanButtons.add(fifteenButton);
		loanButtons.add(thirtyButton);
		
		panelContainer.setLayout(cl);
		
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
		
		// set button fonts, action listeners for JRadioButtons
		fifteenButton.addActionListener(this);
		fifteenButton.setFont(new Font("Arial", Font.PLAIN, 22));
		thirtyButton.addActionListener(this);
		thirtyButton.setFont(new Font("Arial", Font.PLAIN, 22));
		
		// define utility elements
		exitButton.setBounds(bottomRight);
		lookupReturnButton.setBounds(bottomLeft);
		
		// define lookuppanel
		custIDLabel.setBounds(585,175,250,80);
		custIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custIDLabel.setFont(labelFont);
		custIdField.setBounds(585,275,250,80);
		custIDSubmitButton.setBounds(585,425,250,80);
		processChecksButton.setBounds(150,205,250,80);
		createCustomerButton.setBounds(150,315,250,80);
		createAccountButton.setBounds(150,425,250,80);
		
		// add elements to lookuppanel
		lookupPanel.add(processChecksButton);
		lookupPanel.add(custIDLabel);
		lookupPanel.add(custIdField);
		lookupPanel.add(custIDSubmitButton);
		lookupPanel.add(createCustomerButton);
		lookupPanel.add(createAccountButton);
		
		// define new customer panel elements
		ssnField.setBounds(450,50,250,50);
		streetAddressField.setBounds(450,115,250,50);
		cityField.setBounds(450,180,250,50);
		stateField.setBounds(450,245,250,50);
		zipField.setBounds(450,310,250,50);
		firstNameField.setBounds(450,375,250,50);
		lastNameField.setBounds(450,440,250,50);
		ssnLabel.setBounds(175,50,250,50);
		streetAddressLabel.setBounds(175,115,250,50);
		cityLabel.setBounds(175,180,250,50);
		stateLabel.setBounds(175,245,250,50);
		zipLabel.setBounds(175,310,250,50);
		firstNameLabel.setBounds(175,375,250,50);
		lastNameLabel.setBounds(175,440,250,50);
		
		ssnLabel.setFont(labelFont);
		streetAddressLabel.setFont(labelFont);
		cityLabel.setFont(labelFont);
		stateLabel.setFont(labelFont);
		zipLabel.setFont(labelFont);
		firstNameLabel.setFont(labelFont);
		lastNameLabel.setFont(labelFont);
		
		ssnLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		streetAddressLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cityLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		stateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		zipLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		firstNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lastNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		createCustomerSubmitButton.setBounds(bottomRight);
		
		// add elements to new customer panel
		// add fields
		newCustomerPanel.add(ssnField);
		newCustomerPanel.add(streetAddressField);
		newCustomerPanel.add(cityField);
		newCustomerPanel.add(stateField);
		newCustomerPanel.add(zipField);
		newCustomerPanel.add(firstNameField);
		newCustomerPanel.add(lastNameField);
		// add labels
		newCustomerPanel.add(ssnLabel);
		newCustomerPanel.add(streetAddressLabel);
		newCustomerPanel.add(cityLabel);
		newCustomerPanel.add(stateLabel);
		newCustomerPanel.add(zipLabel);
		newCustomerPanel.add(firstNameLabel);
		newCustomerPanel.add(lastNameLabel);
		newCustomerPanel.add(createCustomerSubmitButton);
		
		// New account panel
		// define new account select panel elements
		checkingButton.setBounds(125,175,250,80);
		savingsButton.setBounds(125,275,250,80);
		cdButton.setBounds(125,375,250,80);
		
		shortTermLoanButton.setBounds(600,175,250,80);
		longTermLoanButton.setBounds(600,275,250,80);
		creditCardButton.setBounds(600,375,250,80);
		
		// add elements to new account panel
		newAccountPanel.add(shortTermLoanButton);
		newAccountPanel.add(longTermLoanButton);
		newAccountPanel.add(creditCardButton);
		newAccountPanel.add(checkingButton);
		newAccountPanel.add(savingsButton);
		newAccountPanel.add(cdButton);

		// newCheckingPanel
		//define newCheckingPanel elements
		chCustIDLabel.setFont(labelFont);
		chAccountNumLabel.setFont(labelFont);
		chBalanceLabel.setFont(labelFont);
		chInterestRateLabel.setFont(labelFont);
		chBackupAccountLabel.setFont(labelFont);
		
		chCustIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		chAccountNumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		chBalanceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		chInterestRateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		chBackupAccountLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		chCustIDLabel.setBounds(200,125,250,50);
		chAccountNumLabel.setBounds(200,200,250,50);
		chBalanceLabel.setBounds(200,275,250,50);
		chInterestRateLabel.setBounds(200,350,250,50);
		chBackupAccountLabel.setBounds(100,425,350,50);
		
		chCustIDField.setBounds(500,125,200,50);
		chAccountNumField.setBounds(500,200,200,50);
		chBalanceField.setBounds(500,275,200,50);
		chInterestRateField.setBounds(500,350,200,50);
		chBackupAccountField.setBounds(500,425,200,50);

		assignBackupAccount.setBounds(720,425,220,50);
		assignBackupAccount.setFont(new Font("Arial", Font.PLAIN, 16));
		
		newCheckingSubmitButton.setBounds(bottomRight);
		
		// add elements to newCheckingPanel
		newCheckingPanel.add(chCustIDLabel);
		newCheckingPanel.add(chAccountNumLabel);
		newCheckingPanel.add(chBalanceLabel);
		newCheckingPanel.add(chInterestRateLabel);
		newCheckingPanel.add(chBackupAccountLabel);

		newCheckingPanel.add(chCustIDField);
		newCheckingPanel.add(chAccountNumField);
		newCheckingPanel.add(chBalanceField);
		newCheckingPanel.add(chInterestRateField);
		newCheckingPanel.add(chBackupAccountField);
		newCheckingPanel.add(assignBackupAccount);
		newCheckingPanel.add(newCheckingSubmitButton);

		// newSavingsPanel
		// define newSavingsPanel elements
		saCustIDLabel.setFont(labelFont);
		saAccountNumLabel.setFont(labelFont);
		saBalanceLabel.setFont(labelFont);
		saInterestRateLabel.setFont(labelFont);

		saCustIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		saAccountNumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		saBalanceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		saInterestRateLabel.setHorizontalAlignment(SwingConstants.TRAILING);

		saCustIDLabel.setBounds(200,125,250,50);
		saAccountNumLabel.setBounds(200,200,250,50);
		saBalanceLabel.setBounds(200,275,250,50);
		saInterestRateLabel.setBounds(200,350,250,50);

		saCustIDField.setBounds(500,125,200,50);
		saAccountNumField.setBounds(500,200,200,50);
		saBalanceField.setBounds(500,275,200,50);
		saInterestRateField.setBounds(500,350,200,50);

		newSavingsSubmitButton.setBounds(bottomRight);

		// add newSavingsPanel elements
		newSavingsPanel.add(saCustIDLabel);
		newSavingsPanel.add(saAccountNumLabel);
		newSavingsPanel.add(saBalanceLabel);
		newSavingsPanel.add(saInterestRateLabel);
		newSavingsPanel.add(saCustIDField);
		newSavingsPanel.add(saAccountNumField);
		newSavingsPanel.add(saBalanceField);
		newSavingsPanel.add(saInterestRateField);
		newSavingsPanel.add(newSavingsSubmitButton);

		// define newCDPanel elements
		cdCustIDLabel.setFont(labelFont);
		cdAccountNumLabel.setFont(labelFont);
		cdDueDateLabel.setFont(labelFont);
		cdBalanceLabel.setFont(labelFont);
		cdInterestRateLabel.setFont(labelFont);

		cdCustIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cdAccountNumLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cdDueDateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cdBalanceLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cdInterestRateLabel.setHorizontalAlignment(SwingConstants.TRAILING);

		cdCustIDLabel.setBounds(200,125,250,50);
		cdAccountNumLabel.setBounds(200,200,250,50);
		cdDueDateLabel.setBounds(100,275,350,50);
		cdBalanceLabel.setBounds(150,350,300,50);
		cdInterestRateLabel.setBounds(150,425,300,50);

		cdCustIDField.setBounds(500,125,200,50);
		cdAccountNumField.setBounds(500,200,200,50);
		cdDueDateField.setBounds(500,275,200,50);
		cdBalanceField.setBounds(500,350,200,50);
		cdInterestRateField.setBounds(500,425,200,50);

		newCDSubmitButton.setBounds(bottomRight);

		// add newCDPanel elements
		newCDPanel.add(cdCustIDLabel);
		newCDPanel.add(cdAccountNumLabel);
		newCDPanel.add(cdDueDateLabel);
		newCDPanel.add(cdBalanceLabel);
		newCDPanel.add(cdInterestRateLabel);
		newCDPanel.add(cdCustIDField);
		newCDPanel.add(cdAccountNumField);
		newCDPanel.add(cdDueDateField);
		newCDPanel.add(cdBalanceField);
		newCDPanel.add(cdInterestRateField);
		newCDPanel.add(newCDSubmitButton);

		// define new credit card panel elements
		ccCustomerIDLabel.setFont(labelFont);
		ccInterestRateLabel.setFont(labelFont);
		ccLoanIDLabel.setFont(labelFont);
		ccLimitLabel.setFont(labelFont);
		
		ccCustomerIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		ccInterestRateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		ccLoanIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		ccLimitLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		ccLoanIDLabel.setBounds(200,150,250,50);
		ccCustomerIDLabel.setBounds(200,225,250,50);
		ccInterestRateLabel.setBounds(200,300,250,50);
		ccLimitLabel.setBounds(150,375,300,50);
		
		ccLoanIDField.setBounds(500,150,200,50);
		ccCustomerIDField.setBounds(500,225,200,50);
		ccInterestRateField.setBounds(500,300,200,50);
		ccLimitField.setBounds(500,375,200,50);

		newCCSubmitButton.setBounds(bottomRight);

		// add newCCPanel elements
		newCCPanel.add(ccLoanIDLabel);
		newCCPanel.add(ccCustomerIDLabel);
		newCCPanel.add(ccInterestRateLabel);
		newCCPanel.add(ccLimitLabel);
		newCCPanel.add(ccLoanIDField);
		newCCPanel.add(ccCustomerIDField);
		newCCPanel.add(ccInterestRateField);
		newCCPanel.add(ccLimitField);
		newCCPanel.add(newCCSubmitButton);

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
		stopPaymentButton.setBounds(125,125,250,80);
		balanceInquiryButton.setBounds(125,225,250,80);
		debitsInquiryButton.setBounds(125,325,250,80);
		creditAccountButton.setBounds(600,125,250,80);
		debitAccountButton.setBounds(600,225,250,80);
		transferCashButton.setBounds(600,325,250,80);
        //loanPaymentButton.setBounds(600,425,250,80);

		// add elements to panel 2
		panel2.add(stopPaymentButton);
		panel2.add(balanceInquiryButton);
		panel2.add(debitsInquiryButton);
		panel2.add(creditAccountButton);
		panel2.add(debitAccountButton);
		panel2.add(transferCashButton);
		panel2.add(exitButton);
        //panel2.add(loanPaymentButton);
		
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
		

		// define panel 11 elements
		amountBorrowedLabel.setFont(labelFont);
		interestRateLabel.setFont(labelFont);
		paymentPlanLabel.setFont(labelFont);
		shortLoanIDLabel.setFont(labelFont);
		shortLoanCustIDLabel.setFont(labelFont);
		
		shortLoanIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		amountBorrowedLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		interestRateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		paymentPlanLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		shortLoanCustIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		shortLoanIDLabel.setBounds(200,125,250,50);
		amountBorrowedLabel.setBounds(200,200,250,50);
		interestRateLabel.setBounds(200,275,250,50);
		paymentPlanLabel.setBounds(150,350,300,50);
		shortLoanCustIDLabel.setBounds(150,425,300,50);
		
		shortLoanIDField.setBounds(500,125,200,50);
		amountBorrowedField.setBounds(500,200,200,50);
		interestRateField.setBounds(500,275,200,50);
		paymentPlanField.setBounds(500,350,200,50);
		shortLoanCustIDField.setBounds(500,425,200,50);

		shortLoanSubmitButton.setBounds(bottomRight);
		
		// add elements to panel 11
		panel11.add(shortLoanIDLabel);
		panel11.add(shortLoanIDField);
		panel11.add(shortLoanCustIDLabel);
		panel11.add(shortLoanCustIDField);
		panel11.add(amountBorrowedLabel);
		panel11.add(interestRateLabel);
		panel11.add(paymentPlanLabel);
		panel11.add(amountBorrowedField);
		panel11.add(interestRateField);
		panel11.add(paymentPlanField);
		panel11.add(shortLoanSubmitButton);

		// define panel 12 elements
		longLoanIDLabel.setFont(labelFont);
		longAmountBorrowedLabel.setFont(labelFont);
		loanTermSelectLabel.setFont(labelFont);
		longInterestRateLabel.setFont(labelFont);
		longLoanCustIDLabel.setFont(labelFont);
		longLoanSubmitButton.setFont(buttonFont);

		longLoanIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		longAmountBorrowedLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		loanTermSelectLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		longInterestRateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		longLoanCustIDLabel.setHorizontalAlignment(SwingConstants.TRAILING);


		fifteenButton.setBounds(500,275,100,50);
		thirtyButton.setBounds(600,275,100,50);

		longLoanIDLabel.setBounds(200,125,250,50);
		longAmountBorrowedLabel.setBounds(200,200,250,50);
		loanTermSelectLabel.setBounds(200,275,250,50);
		longInterestRateLabel.setBounds(200,350,250,50);
		longLoanCustIDLabel.setBounds(150,425,300,50);

		longLoanIDField.setBounds(500,125,200,50);
		longAmountBorrowedField.setBounds(500,200,200,50);
		longInterestRateField.setBounds(500,350,200,50);
		longLoanCustIDField.setBounds(500,425,200,50);

		longLoanSubmitButton.setBounds(bottomRight);

		// add elements to panel 12
		panel12.add(longLoanIDLabel);
		panel12.add(longLoanIDField);
		panel12.add(longAmountBorrowedLabel);
		panel12.add(loanTermSelectLabel);
		panel12.add(fifteenButton);
		panel12.add(thirtyButton);
		panel12.add(longInterestRateLabel);
		panel12.add(longLoanCustIDLabel);
		panel12.add(longAmountBorrowedField);
		panel12.add(longInterestRateField);
		panel12.add(longLoanCustIDField);
		panel12.add(longLoanSubmitButton);

		// define panel 13 elements
		loanPaymentAmtLabel.setFont(labelFont);
		loanPaymentAmtLabel.setBounds(150,300,300,50);
		loanPaymentAmtLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		loanPaymentAmtField.setBounds(500,300,200,50);
		loanCompletePaymentButton.setBounds(bottomRight);

		// add elements to panel 13
		//panel13.add(loanListWindow);
		panel13.add(loanPaymentAmtLabel);
		panel13.add(loanPaymentAmtField);
		panel13.add(loanCompletePaymentButton);
	
		// add panels to container
		int i = 1;
		for(JPanel panel : panels){
			panelContainer.add(panel, ("" + i));
			i++;
		}

		cl.show(panelContainer, "14");

    }

	// contains all action listener actions
	@Override
	public void actionPerformed(ActionEvent e) {

		// lookup customer id, get accounts
		if((e.getSource() == custIDSubmitButton) && !(custIdField.getText().equals(""))){
			accountList = HelperFunc.accountsLookup(checkingList, savingsList, cdList, custIdField.getText());
			accountArray = new String[accountList.size()];
			accounts = new ArrayList<>();
			for(int i=0; i<accountList.size(); i++){
				accountArray[i] = accountList.get(i);
				String[] values = accountArray[i].split(" ");
				accounts.add(values);
			}
			accountJList.setListData(accountArray);
			accountSelectPanel.add(lookupReturnButton);
			cl.show(panelContainer, "15");
		}
		// returns manager to first screen
		if(e.getSource() == lookupReturnButton){
			custIdField.setText("");
			cl.show(panelContainer, "14");
		}

		// process checks in queue
		if(e.getSource() == processChecksButton){
			if(checkList.size() > 0){
				HelperFunc.processChecks(checkList, checkingList, savingsList);
				checkList.clear(); // remove checks from list
				HelperFunc.updateChecks(checkList); // save changes
				HelperFunc.updateChecking(checkingList); // save changes
				HelperFunc.updateSavings(savingsList); // save changes
				JOptionPane.showMessageDialog(null, "Checks successfully processed.", "Alert", JOptionPane.INFORMATION_MESSAGE);
			} else{
				JOptionPane.showMessageDialog(null, "No checks to process right now.", "Alert", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		// send to create account page
		if(e.getSource() == createAccountButton){
			newAccountPanel.add(lookupReturnButton);
			cl.show(panelContainer, "17");
		}

		// send to short term loan creation page
		if(e.getSource() == shortTermLoanButton){
			shortLoanCustIDField.setText("");
			amountBorrowedField.setText("");
			interestRateField.setText("");
			paymentPlanField.setText("");
			panel11.add(lookupReturnButton);
			cl.show(panelContainer, "11");
		}

		// send to long term loan creation page
		if(e.getSource() == longTermLoanButton){
			longLoanIDField.setText("");
			longAmountBorrowedField.setText("");
			longInterestRateField.setText("");
			longLoanCustIDField.setText("");
			panel12.add(lookupReturnButton);
			cl.show(panelContainer, "12");
		}

		// send to credit card creation page
		if(e.getSource() == creditCardButton){
			ccLoanIDField.setText("");
			ccCustomerIDField.setText("");
			ccInterestRateField.setText("");
			ccLimitField.setText("");
			newCCPanel.add(lookupReturnButton);
			cl.show(panelContainer, "18");
		}

		// send to checking account creation page
		if(e.getSource() == checkingButton){
			chCustIDField.setText("");
			chAccountNumField.setText("");
			chBalanceField.setText("");
			chInterestRateField.setText("");
			chBackupAccountField.setText("");
			newCheckingPanel.add(lookupReturnButton);
			cl.show(panelContainer, "19");
		}

		// send to savings account creation page
		if(e.getSource() == savingsButton){
			saCustIDField.setText("");
			saAccountNumField.setText("");
			saBalanceField.setText("");
			saInterestRateField.setText("");
			newSavingsPanel.add(lookupReturnButton);
			cl.show(panelContainer, "20");
		}

		// send to cd account creation page
		if(e.getSource() == cdButton){
			cdCustIDField.setText("");
			cdAccountNumField.setText("");
			cdDueDateField.setText("");
			cdBalanceField.setText("");
			cdInterestRateField.setText("");
			newCDPanel.add(lookupReturnButton);
			cl.show(panelContainer, "21");
		}

		// create new checking account for entered data
		if(e.getSource() == newCheckingSubmitButton){
			boolean parsable = true;
			parsable = HelperFunc.isParsableNumber(chAccountNumField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(chBalanceField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(chInterestRateField.getText());

			if(parsable && !assignBackupAccount.isSelected()) {
				String mssg = HelperFunc.createCheckingAndAdd(checkingList,
				chCustIDField.getText(), 
				Integer.parseInt(chAccountNumField.getText()),
				Double.parseDouble(chBalanceField.getText()),
				Double.parseDouble(chInterestRateField.getText()));

				HelperFunc.updateChecking(checkingList); // save changes
				JOptionPane.showMessageDialog(null, mssg, "New Checking Account", JOptionPane.INFORMATION_MESSAGE);
				cl.show(panelContainer, "14");
			} else if(assignBackupAccount.isSelected()){
				parsable = HelperFunc.isParsableNumber(chBackupAccountField.getText());
				if(parsable){
					String mssg = HelperFunc.createCheckingAndAddWithBackup(checkingList,
					chCustIDField.getText(),
					Integer.parseInt(chAccountNumField.getText()),
					Double.parseDouble(chBalanceField.getText()),
					Double.parseDouble(chInterestRateField.getText()),
					Integer.parseInt(chBackupAccountField.getText()));

					HelperFunc.updateChecking(checkingList); // save changes
					JOptionPane.showMessageDialog(null, mssg, "New Checking Account", JOptionPane.INFORMATION_MESSAGE);
					cl.show(panelContainer, "14");
				} else {
					JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only for Account Number, Balance,and Interest Rate,", "Parse Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		// create new savings account for entered data
		if(e.getSource() == newSavingsSubmitButton){
			boolean parsable = true;
			parsable = HelperFunc.isParsableNumber(saAccountNumField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(saBalanceField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(saInterestRateField.getText());
			if(parsable){
				String mssg = HelperFunc.createSavingsAndAdd(savingsList,
				saCustIDField.getText(),
				Integer.parseInt(saAccountNumField.getText()),
				Double.parseDouble(saBalanceField.getText()),
				Double.parseDouble(saInterestRateField.getText()));
				HelperFunc.updateSavings(savingsList); // save changes
				JOptionPane.showMessageDialog(null, mssg, "New Savings Account", JOptionPane.INFORMATION_MESSAGE);
				cl.show(panelContainer, "14");

			} else {
				JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only for Account Number, Balance,and Interest Rate,", "Parse Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// create new cd from entered data
		if(e.getSource() == newCDSubmitButton){
			boolean parsable = true;
			parsable = HelperFunc.isParsableNumber(cdAccountNumField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(cdBalanceField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(cdInterestRateField.getText());
			if(!(cdDueDateField.getText().matches("([0-9]{1,2}/[0-9]{1,2}/[0-9]{4})"))) parsable = false;
			if(parsable) {
				String mssg = HelperFunc.createCDAndAdd(cdList,
				cdCustIDField.getText(), 
				cdDueDateField.getText(),
				Integer.parseInt(cdAccountNumField.getText()),
				Double.parseDouble(cdBalanceField.getText()),
				Double.parseDouble(cdInterestRateField.getText()));
				HelperFunc.updateCD(cdList); // save changes
				JOptionPane.showMessageDialog(null, mssg, "New CD Account", JOptionPane.INFORMATION_MESSAGE);
				cl.show(panelContainer, "14");
			} else {
				JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only for Account Number, Balance,and Interest Rate.\n Date should be formatted like '5/3/2021", "Parse Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// create new credit card from entered data
		if(e.getSource() == newCCSubmitButton){
			boolean parsable = true;
			parsable = HelperFunc.isParsableNumber(ccLoanIDField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(ccCustomerIDField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(ccInterestRateField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(ccLimitField.getText());
			if(parsable){
				String mssg = HelperFunc.createLoanAndAdd(loanList,
					Integer.parseInt(ccLoanIDField.getText()), // get loanID
					ccCustomerIDField.getText(), // get customerID
					0, // start with 0 balance
					Double.parseDouble(ccInterestRateField.getText()), // get interest rate
					"Credit Card", // this is a credit card type loan
					Integer.parseInt(ccLimitField.getText())); // get cc limit
				
				HelperFunc.updateLoans(loanList); // save changes
				JOptionPane.showMessageDialog(null, mssg, "New Credit Card", JOptionPane.INFORMATION_MESSAGE);
				cl.show(panelContainer, "14");
			} else {
				JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only for loanID, Interest Rate, and card limit", "Parse Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// send to customer creation page
		if(e.getSource() == createCustomerButton){
			ssnField.setText("");
			streetAddressField.setText("");
			cityField.setText("");
			stateField.setText("");
			zipField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			newCustomerPanel.add(lookupReturnButton);
			cl.show(panelContainer, "16");
		}
		
		// create new customer from entered data
		if(e.getSource() == createCustomerSubmitButton){
			String[] options = {"Yes", "No"};
			String msg = "You're about to create a new Customer and add to the database, are you sure?";
			int input = JOptionPane.showOptionDialog(null, msg, "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
			// option pane for confirm
			if(input == JOptionPane.OK_OPTION){
				// create customer
				HelperFunc.createCustomerAndAdd(customerList,
				ssnField.getText(),
				streetAddressField.getText(),
				cityField.getText(),
				stateField.getText(),
				zipField.getText(),
				firstNameField.getText(),
				lastNameField.getText());
				HelperFunc.updateCustomers(customerList); // save changes
				JOptionPane.showMessageDialog(null, "New Customer successfully created", "Customer Created", JOptionPane.INFORMATION_MESSAGE);

				cl.show(panelContainer, "14");
			}
		}

		// get data from selected account
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

		// back button is pressed
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
			cl.show(panelContainer, "14");
		}

		// panel 2 buttons
		if(e.getSource() == stopPaymentButton){
			panel3.add(backButton);
			cl.show(panelContainer, "3");
		}

		// send to balance inquiry page
		if(e.getSource() == balanceInquiryButton){
			panel4.add(backButton);
			if(workingAcctType.equals("Checking")){
				balanceAmountLabel.setText("Balance: $" + HelperFunc.getCheckingBalance(checkingList, workingAcctNum));
			} else if(workingAcctType.equals("Savings")){
				balanceAmountLabel.setText("Balance: $" + HelperFunc.getSavingsBalance(savingsList, workingAcctNum));
			} else if(workingAcctType.equals("CD")){
				balanceAmountLabel.setText("Balance: $" + HelperFunc.getCDBalance(cdList, workingAcctNum));
			}
			cl.show(panelContainer, "4");
		}

		// send to debits inquiry page
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

		// send to cash transfer page
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

		// deprecated, no longer used
		if(e.getSource() == loanPaymentButton){
			panel13.add(backButton);
			cl.show(panelContainer, "13");
		}

		// panel 3 buttons (stop payment)
		if(e.getSource() == submitStopButton) {
			if(HelperFunc.isParsableNumber(stopCheckNumField.getText())){
				HelperFunc.stopCheck(checkList, workingAcctNum, stopCheckNumField.getText(), workingAcctType);
				HelperFunc.updateChecks(checkList);
				// send teller to confirmation page
				panel10.add(backButton);
				cl.show(panelContainer, "10");
			}
		}

		// panel 4 buttons (balance inquiry) N/A

		// panel 5 buttons (debits inquiry) N/A

		// panel 6 buttons (credit account page)
		if(e.getSource() == checkDepositButton) {
			creditAmountField.setText("");
			panel7.add(backButton);
			cl.show(panelContainer, "7");
		}

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
				} else if (workingAcctType.equals("CD")){
					HelperFunc.creditCDAccount(cdList, workingAcctNum, creditAmt);
					HelperFunc.updateCD(cdList);
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
				} else if (workingAcctType.equals("CD")){
					HelperFunc.debitCDAccount(cdList, workingAcctNum, depositAmount);
					HelperFunc.updateCD(cdList);
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
		} // end transfer submit action

		// panel 11 button (short term loan page)
		// create new short term loan
		if(e.getSource() == shortLoanSubmitButton) {
			boolean parsable = true;
			parsable = HelperFunc.isParsableNumber(shortLoanCustIDField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(amountBorrowedField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(interestRateField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(paymentPlanField.getText());
			if(parsable){
				String mssg = HelperFunc.createLoanAndAdd(loanList,
					Integer.parseInt(shortLoanIDField.getText()),
					shortLoanCustIDField.getText(),
					Double.parseDouble(amountBorrowedField.getText()),
					Double.parseDouble(interestRateField.getText()), 
					"Short Term", 
					Integer.parseInt(paymentPlanField.getText()));
				
				HelperFunc.updateLoans(loanList); // save changes
				JOptionPane.showMessageDialog(null, mssg, "New Loan", JOptionPane.INFORMATION_MESSAGE);
				cl.show(panelContainer, "14");
			} else {
				JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only for loanID, Amount Borrowed, Interest Rate, and Years to Pay", "Parse Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// panel 12 button (long term loan page)
		// create new long term loan
		if(e.getSource() == longLoanSubmitButton && (fifteenButton.isSelected() || thirtyButton.isSelected())){
			boolean parsable = true;
			int years = 0;
			if(fifteenButton.isSelected()) years = 15;
			if(thirtyButton.isSelected()) years = 30;
			parsable = HelperFunc.isParsableNumber(shortLoanCustIDField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(amountBorrowedField.getText());
			if(parsable) parsable = HelperFunc.isParsableNumber(interestRateField.getText());
			if(parsable){
				String mssg = HelperFunc.createLoanAndAdd(loanList,
					Integer.parseInt(longLoanIDField.getText()),
					longLoanCustIDField.getText(),
					Double.parseDouble(longAmountBorrowedField.getText()),
					Double.parseDouble(longInterestRateField.getText()), 
					"Long Term", 
					years);
				
				HelperFunc.updateLoans(loanList); // save changes
				JOptionPane.showMessageDialog(null, mssg, "New Loan", JOptionPane.INFORMATION_MESSAGE);
				cl.show(panelContainer, "14");
			} else {
				JOptionPane.showMessageDialog(this, "Given input is incorrect! Enter numbers only for loanID, Amount Borrowed, and Interest Rate", "Parse Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		// panel 13 button (loan payment page) (Deprecated)
		if(e.getSource() == loanCompletePaymentButton) {
			panel10.add(backButton);
			cl.show(panelContainer, "10");
		}
	} // end button actions
    
} // end Managerpage