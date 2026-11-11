import java.util.ArrayList;

// Custom exception for insufficient balance
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Bank account class
class BankAccount {
    double balance;
    ArrayList<String> transactions = new ArrayList<>();

    // Constructor
    BankAccount(double balance) {
        this.balance = balance;
        transactions.add("Account opened with Rs. " + balance);
    }

    // Method to deposit money
    void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            transactions.add("Deposited Rs. " + amount);
            System.out.println("Money deposited successfully.");
        } else {
            System.out.println("Please enter a valid amount.");
        }
    }

    // Method to withdraw money
    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Please enter a valid amount.");
        } else if (amount > balance) {
            throw new InsufficientFundsException(
                "Sorry! You don't have enough balance."
            );
        } else {
            balance = balance - amount;
            transactions.add("Withdrawn Rs. " + amount);
            System.out.println("Money withdrawn successfully.");
        }
    }

    // Method to check balance
    void showBalance() {
        System.out.println("Your current balance is: Rs. " + balance);
    }

    // Method to show transaction history
    void showTransactions() {
        System.out.println("\nYour Transaction History:");

        for (String t : transactions) {
            System.out.println(t);
        }
    }
}

// Main class
public class BankAccountDemo {
    public static void main(String[] args) {

        // Creating a bank account with Rs. 1000
        BankAccount myAccount = new BankAccount(1000);

        System.out.println("Welcome to Simple Bank!");

        // Checking opening balance
        myAccount.showBalance();

        // Depositing money
        myAccount.deposit(500);

        // Withdrawing money successfully
        try {
            myAccount.withdraw(300);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        // Trying to withdraw more money than available
        try {
            myAccount.withdraw(2000);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Showing final balance
        myAccount.showBalance();

        // Displaying all transactions
        myAccount.showTransactions();
    }
              }
              
