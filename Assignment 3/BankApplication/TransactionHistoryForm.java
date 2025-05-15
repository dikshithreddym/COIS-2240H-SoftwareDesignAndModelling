import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryForm extends JPanel {
    public TransactionHistoryForm(JFrame parentFrame) {
        setLayout(new BorderLayout());

        // Panel for input
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        inputPanel.add(accountNumberField);
        add(inputPanel, BorderLayout.NORTH);

        // Submit Button
        JButton submitButton = new JButton("View Transactions");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText().trim();
                if (accountNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "Please enter an account number.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                List<String> transactions = getTransactionHistory(accountNumber);
                if (transactions == null || transactions.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "No transactions found for account: " + accountNumber, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showTransactionHistoryDialog(parentFrame, transactions);
                }
            }
        });
        add(submitButton, BorderLayout.SOUTH);
    }

    private List<String> getTransactionHistory(String accountNumber) {
        List<String> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(accountNumber)) {
                    transactions.add(parts[1] + " - " + parts[2]); // Assuming the format is "Action - Date"
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading transaction history: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return transactions;
    }

    private void showTransactionHistoryDialog(JFrame parentFrame, List<String> transactions) {
        JDialog historyDialog = new JDialog(parentFrame, "Transaction History", true);
        historyDialog.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(20, 50);
        transactions.forEach(transaction -> textArea.append(transaction + "\n"));
        textArea.setEditable(false);
        historyDialog.add(new JScrollPane(textArea), BorderLayout.CENTER);
        historyDialog.pack();
        historyDialog.setLocationRelativeTo(parentFrame);
        historyDialog.setVisible(true);
    }
}
