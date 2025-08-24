import java.util.Scanner;

/**
 * The BankAccount class represents a user's bank account.
 * It stores the account balance and provides methods to deposit,
 * withdraw, and check the balance.
 */
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    /**
     * Deposits a specified amount into the account.
     * @param amount The amount to deposit. Must be positive.
     * @return true if the deposit was successful, false otherwise.
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    /**
     * Withdraws a specified amount from the account.
     * @param amount The amount to withdraw. Must be positive and not exceed the balance.
     * @return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }
}

/**
 * The ATM class represents the ATM machine and its user interface.
 * It connects to a BankAccount to perform transactions.
 */
public class ATM {
    private BankAccount userAccount;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.userAccount = account;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu and handles user choices.
     */
    public void showMenu() {
        int choice = 0;
        while (choice != 4) {
            System.out.println("\n--- 🏧 ATM Menu ---");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        handleWithdraw();
                        break;
                    case 2:
                        handleDeposit();
                        break;
                    case 3:
                        handleCheckBalance();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye! 👋");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the faulty input from the scanner
            }
        }
    }

    /**
     * Handles the withdrawal process.
     */
    private void handleWithdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        
        if (userAccount.withdraw(amount)) {
            System.out.println("✅ Withdrawal successful. Please take your cash.");
        } else {
            System.out.println("❌ Transaction Failed. Insufficient balance or invalid amount.");
        }
        System.out.printf("Your current balance is: $%.2f%n", userAccount.getBalance());
    }

    /**
     * Handles the deposit process.
     */
    private void handleDeposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (userAccount.deposit(amount)) {
            System.out.println("✅ Deposit successful.");
        } else {
            System.out.println("❌ Transaction Failed. Invalid deposit amount.");
        }
        System.out.printf("Your new balance is: $%.2f%n", userAccount.getBalance());
    }

    /**
     * Handles checking the account balance.
     */
    private void handleCheckBalance() {
        System.out.printf("Your current account balance is: $%.2f%n", userAccount.getBalance());
    }

    // --- Main method to run the program ---
    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount myAccount = new BankAccount(1000.00);

        // Create an ATM instance connected to the bank account
        ATM atm = new ATM(myAccount);

        // Run the ATM interface
        atm.showMenu();
    }
}
