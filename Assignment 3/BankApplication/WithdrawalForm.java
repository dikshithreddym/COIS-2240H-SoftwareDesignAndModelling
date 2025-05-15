import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat; // Add this import statement
import java.util.Date;
import java.util.List;

public class WithdrawalForm extends JPanel {
    public WithdrawalForm(JFrame parentFrame) {
        setLayout(new GridLayout(0, 2));

        // Account Number Field
        add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        add(accountNumberField);

        // Withdrawal Amount Field
        add(new JLabel("Withdrawal Amount:"));
        JTextField withdrawalAmountField = new JTextField();
        add(withdrawalAmountField);

        // Withdraw Button
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText().trim();
                String withdrawalAmountText = withdrawalAmountField.getText().trim();

                // Validate input fields
                if (accountNumber.isEmpty() || withdrawalAmountText.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Please enter account number and withdrawal amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double withdrawalAmount = Double.parseDouble(withdrawalAmountText);

                    if (withdrawalAmount <= 0) {
                        JOptionPane.showMessageDialog(parentFrame, "Withdrawal amount must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Check if account exists and has sufficient funds
                    BankAccount account = getAccount(accountNumber);
                    if (account == null) {
                        JOptionPane.showMessageDialog(parentFrame, "Account not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (account.getBalance() < withdrawalAmount) {
                            JOptionPane.showMessageDialog(parentFrame, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Perform withdrawal
                            account.withdraw(withdrawalAmount);
                            FileManager.updateAccount(account); // Update account in the file
                            FileManager.logTransaction("transactions.csv", accountNumber, "Withdrawal", withdrawalAmount, getCurrentDate()); // Log the transaction
                            JOptionPane.showMessageDialog(parentFrame, "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            parentFrame.dispose(); // Close the form on successful withdrawal
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Please enter a valid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(withdrawButton);
    }

    private BankAccount getAccount(String accountNumber) {
        List<BankAccount> accounts = FileManager.readAccounts("accounts.csv");
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
