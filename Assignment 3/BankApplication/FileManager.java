import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    // Method to read accounts from a CSV file
    public static List<BankAccount> readAccounts(String filename) {
        List<BankAccount> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                BankAccount account = new BankAccount(values[0], values[1], values[2], Double.parseDouble(values[3]));
                accounts.add(account);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing a numeric value: " + e.getMessage());
        }
        return accounts;
    }

    // Method to write a new account to the CSV file
    public static void writeAccount(String filename, BankAccount account) {
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.printf("%s,%s,%s,%.2f\n", account.getAccountNumber(), account.getAccountHolderName(),
                    account.getAccountType(), account.getBalance());
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static void updateAccount(BankAccount accountToUpdate) {
        List<BankAccount> accounts = readAccounts("accounts.csv");
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(accountToUpdate.getAccountNumber())) {
                accounts.set(i, accountToUpdate);
                break;
            }
        }
        writeAccounts("accounts.csv", accounts);
    }

    public static void writeAccounts(String filename, List<BankAccount> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (BankAccount account : accounts) {
                writer.write(account.toCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle properly in a real application
        }
    }

    

    // Method to append a transaction to the transactions.csv file
    public static void logTransaction(String filename, String accountNumber, String action, double amount, String date) {
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.printf("%s,%s,%.2f,%s\n", accountNumber, action, amount, date);
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
