import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BalanceInquiryForm extends JPanel {
    public BalanceInquiryForm(JFrame parentFrame) {
        setLayout(new GridLayout(0, 2));

        // Account Number Field
        add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        add(accountNumberField);

        // Inquiry Button
        JButton inquiryButton = new JButton("Inquire");
        inquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                Double balance = getAccountBalance(accountNumber);
                if (balance != null) {
                    JOptionPane.showMessageDialog(parentFrame, "Current balance: $" + String.format("%.2f", balance), "Balance Inquiry", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(inquiryButton);
    }

    private Double getAccountBalance(String accountNumber) {
        List<BankAccount> accounts = readAccounts("accounts.csv");
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account.getBalance();
            }
        }
        return null; // Account not found
    }

    private List<BankAccount> readAccounts(String filename) {
        List<BankAccount> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    BankAccount account = new BankAccount(values[0], values[1], values[2], Double.parseDouble(values[3]));
                    accounts.add(account);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading the file: " + e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error parsing the balance from the file: " + e.getMessage(), "Data Error", JOptionPane.ERROR_MESSAGE);
        }
        return accounts;
    }
}
