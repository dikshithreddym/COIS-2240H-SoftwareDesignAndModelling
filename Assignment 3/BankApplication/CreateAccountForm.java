// CreateAccountForm.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountForm extends JPanel {
    public CreateAccountForm(JFrame parentFrame) {
        setLayout(new GridLayout(0, 2));

        // Add form fields
        add(new JLabel("Account Holder Name:"));
        JTextField holderNameField = new JTextField();
        add(holderNameField);

        add(new JLabel("Account Type:"));
        JComboBox<String> accountTypeBox = new JComboBox<>(new String[]{"Savings", "Checking"});
        add(accountTypeBox);

        add(new JLabel("Initial Deposit:"));
        JTextField initialDepositField = new JTextField();
        add(initialDepositField);

        // Submit button
        JButton submitButton = new JButton("Create Account");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validate input and create account
                    String holderName = holderNameField.getText().trim();
                    String accountType = (String) accountTypeBox.getSelectedItem();
                    double initialDeposit = Double.parseDouble(initialDepositField.getText());

                    // Input validation
                    if (holderName.isEmpty()) {
                        JOptionPane.showMessageDialog(parentFrame, "Please enter the account holder's name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (initialDeposit < 0) {
                        JOptionPane.showMessageDialog(parentFrame, "Initial deposit must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Generate a unique account number (this method needs to be implemented)
                    String accountNumber = generateUniqueAccountNumber();
                    
                    // Create a new BankAccount object
                    BankAccount newAccount = new BankAccount(accountNumber, holderName, accountType, initialDeposit);

                    // Use FileManager to write this new account information to the accounts.csv file
                    FileManager.writeAccount("accounts.csv", newAccount);

                    // Inform the user of a successful account creation
                    JOptionPane.showMessageDialog(parentFrame, "Account created successfully! Account Number: " + accountNumber, "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close the form window after account creation
                    parentFrame.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(parentFrame, "Please enter a valid amount for initial deposit.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(parentFrame, "An error occurred while creating the account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(submitButton);
    }

    private String generateUniqueAccountNumber() {
        // Implement this method to generate a unique account number.
        // For now, here's a placeholder using the current time in milliseconds
        return String.valueOf(System.currentTimeMillis());
    }
}
