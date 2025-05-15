import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    public Main() {
        super("Basic Banking Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window
        
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // File menu with an exit item
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        
        // Banking operations menu
        JMenu operationsMenu = new JMenu("Operations");
        JMenuItem createAccountItem = new JMenuItem("Create Account");
        JMenuItem depositItem = new JMenuItem("Deposit");
        JMenuItem withdrawalItem = new JMenuItem("Withdrawal");
        JMenuItem balanceInquiryItem = new JMenuItem("Balance Inquiry");
        JMenuItem transactionHistoryItem = new JMenuItem("Transaction History");
        
        //action listeners to menu items
        createAccountItem.addActionListener(e -> {
            JFrame frame = new JFrame("Create Account");
            frame.setContentPane(new CreateAccountForm(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        depositItem.addActionListener(e -> {
            JFrame frame = new JFrame("Deposit Funds");
            frame.setContentPane(new DepositForm(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        withdrawalItem.addActionListener(e -> {
            JFrame frame = new JFrame("Withdraw Funds");
            frame.setContentPane(new WithdrawalForm(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        balanceInquiryItem.addActionListener(e -> {
            JFrame frame = new JFrame("Balance Inquiry");
            frame.setContentPane(new BalanceInquiryForm(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        transactionHistoryItem.addActionListener(e -> {
            JFrame frame = new JFrame("Transaction History");
            frame.setContentPane(new TransactionHistoryForm(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        operationsMenu.add(createAccountItem);
        operationsMenu.add(depositItem);
        operationsMenu.add(withdrawalItem);
        operationsMenu.add(balanceInquiryItem);
        operationsMenu.add(transactionHistoryItem);
        
        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(operationsMenu);
        
        // Set the menu bar
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        // Make the GUI visible on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
