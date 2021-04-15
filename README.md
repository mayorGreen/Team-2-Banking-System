# Team-2-Banking-System
CSC 406 UML System Design Problem.

# Accounts available:
1. Savings accounts: Customers can add or withdraw
money from them at any time. The accounts earn a fixed interest rate that is compounded
daily. The bank also has CDs. These have a fixed rate of return for a specified period of
time. The bank has a penalty for withdrawal before the time is complete and after customer
notification, the CDs automatically roll over.
2. Checking accounts. There are two types of checking accounts. 
The first checking account is the “That’s My Bank”
account. In this account, the customer’s are charged 0.50 per transaction. A transaction is
considered both a deposit and a withdrawal. Monthly transfers (for home mortgages, payment
of bills, or movement of money to other accounts) can be made from the TMB account.
These cost 0.75 per transaction. There is no minimum balance to be maintained for this
account.
The second type of account is the “Gold/Diamond” account. This type account maintains
a minimum balance of $1000.0. There is no charge for transactions against this account
if the minimum balance is maintained. The account also earns interest on the average
balance in the account. The rate is flexible in that it changes on a daily basis and is
0.5*(the rate on the savings account).

# Services and Penalties
a). Stop Payment. 
In the context of a stop payment, the account owner calls the bank and
gives them a check number. The bank will not pay the check with the stop payment number. There is a
$15.00 charge for this service.
b). Overdrafts. 
The bank returns the check unpaid and charges the account with a $20 overdraft fee.
Customers having Savings accounts can use them as backup to the checking accounts. In this case, an
overdraft will not occur if there is enough money in the savings account to cover the check. The banking
system will remove the correct amount of money from the savings account deposit it in the checking
account and then honor the check. This is done at no cost to the customer. The customer must however
specify the “overdraft” backup account.
# Loans
The bank maintains three different kinds of loan accounts;
1. The long-term Mortgage loan. 
This loan is of either the 15/30-year type. These loans are
fixed rate, fixed payment plan. The bank receives these loans on a monthly basis. If loan
payment is late (beyond a given due date) the bank adds a $75 dollar late fee to that
month’s payment. Extra payments maybe made on loans at anytime, however no amount
maybe added to the loan. If a payment is missed, the account is flagged as a problem
account.
2. The short term loan. 
This is a loan of approx. 5 years for things like cars. The short-
term loan has the same characteristics as the long-term loan.
3. Credit Cards. 
Debits against the loan are made with each credit card purchase. For each CC account there is a
limit on the credit available for that account. So each purchase is checked to determine if
it will bring the account balance over the limit. If not the purchase (and the debit on the
account) are authorized. A record of each purchase is maintained. CC bills are sent on
the first of each month, payable by the 10’Th. Each bill consists of a finance charge and
a total of the charges incurred that month. The account holder can pay the entire account
off each month in which case there will be no finance charge. Payments
