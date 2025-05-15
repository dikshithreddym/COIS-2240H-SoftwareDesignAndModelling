import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat; // Import for SimpleDateFormat
import java.util.Date; // Import for Date
import java.util.List;

public class DepositForm extends JPanel {
    public DepositForm(JFrame parentFrame) {
        setLayout(new GridLayout(0, 2));

        // Add form fields
        add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        add(accountNumberField);

        add(new JLabel("Deposit Amount:"));
        JTextField depositAmountField = new JTextField();
        add(depositAmountField);

        // Submit button
        JButton submitButton = new JButton("Deposit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText().trim();
                try {
                    double depositAmount = Double.parseDouble(depositAmountField.getText());
                    
                    // Validate the deposit amount
                    if (depositAmount <= 0) {
                        JOptionPane.showMessageDialog(parentFrame, "Deposit amount must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Process the deposit
                    boolean depositSuccessful = processDeposit(accountNumber, depositAmount);
                    if (depositSuccessful) {
                        JOptionPane.showMessageDialog(parentFrame, "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(parentFrame, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Please enter a valid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }

                // Refresh or close the deposit form as needed
                // parentFrame.dispose();
            }
        });
        add(submitButton);
    }

    private boolean processDeposit(String accountNumber, double amount) {
        List<BankAccount> accounts = FileManager.readAccounts("accounts.csv");
        boolean found = false;
        
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.deposit(amount);
                found = true;
                break;
            }
        }

        if (found) {
            FileManager.writeAccounts("accounts.csv", accounts); // Overwrites the accounts file with updated balances
            FileManager.logTransaction("transactions.csv", accountNumber, "Deposit", amount, getCurrentDate()); // Log the transaction
        }
        
        return found;
    }
    
    // FileManager methods for writing accounts and logging transactions should be added here

    private String getCurrentDate() {
        // This method returns the current date in the format you use in your transactions.csv
        // For example, you could use SimpleDateFormat to format a new Date object
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
