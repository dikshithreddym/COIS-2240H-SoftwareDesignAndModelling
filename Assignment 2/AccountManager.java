import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountManager {
    private List<BankAccount> accounts = new ArrayList<>();

    // Method to load accounts from the CSV file
    public void loadAccountsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                BankAccount account = new BankAccount(values[0], values[1], values[2], Double.parseDouble(values[3]));
                accounts.add(account);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while loading the accounts: " + e.getMessage());
        }
    }

    // Method to display all accounts
    public void viewAccounts() {
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    // Method to search for an account by account number
    public BankAccount searchAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // Method to deposit money into an account
    public void deposit(String accountNumber, double amount) {
        BankAccount account = searchAccount(accountNumber);
        if (account != null && amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        } else {
            System.err.println("Deposit failed. Account not found or invalid amount.");
        }
    }

    // Method to withdraw money from an account
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = searchAccount(accountNumber);
        if (account != null && amount > 0 && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        } else {
            System.err.println("Withdrawal failed. Account not found, insufficient balance, or invalid amount.");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        // Adjust the file path according to your system
        manager.loadAccountsFromFile("C:\\Users\\diksh\\Desktop\\COIS 2240H\\Assignment 2\\bank_accounts.csv");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:\n1. View All Accounts\n2. Search by Account Number\n3. Deposit\n4. Withdraw\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (choice) {
                case 1:
                    manager.viewAccounts();
                    break;
                case 2:
                    System.out.print("Enter account number to search: ");
                    String accountNumber = scanner.nextLine();
                    BankAccount account = manager.searchAccount(accountNumber);
                    if (account != null) {
                        System.out.println("Account found: " + account);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number for deposit: ");
                    String depositAccountNumber = scanner.nextLine();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    manager.deposit(depositAccountNumber, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter account number for withdrawal: ");
                    String withdrawAccountNumber = scanner.nextLine();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    manager.withdraw(withdrawAccountNumber, withdrawAmount);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
