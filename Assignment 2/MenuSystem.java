import java.util.Scanner;

public class MenuSystem {
    private final AccountManager manager;
    private final Scanner scanner;

    public MenuSystem(AccountManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. View All Accounts");
            System.out.println("2. Search by Account Number");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    manager.viewAccounts();
                    break;
                case 2:
                    searchAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchAccount() {
        System.out.print("Enter account number to search: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = manager.searchAccount(accountNumber);
        if (account != null) {
            System.out.println("Account found: " + account);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void deposit() {
        System.out.print("Enter account number for deposit: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        manager.deposit(accountNumber, amount);
    }

    private void withdraw() {
        System.out.print("Enter account number for withdrawal: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline
        manager.withdraw(accountNumber, amount);
    }

    // Main method to run the Menu System
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        manager.loadAccountsFromFile("C:\\Users\\diksh\\Desktop\\COIS 2240H\\Assignment 2\\bank_accounts.csv");

        MenuSystem menu = new MenuSystem(manager);
        menu.displayMenu();
    }
}
