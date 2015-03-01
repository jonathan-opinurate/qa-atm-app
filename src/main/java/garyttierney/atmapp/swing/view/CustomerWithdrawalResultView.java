package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.swing.action.CloseTransactionAction;
import garyttierney.atmapp.swing.action.CloseTransactionAndPrintReceiptAction;

import javax.swing.*;

public class CustomerWithdrawalResultView extends AbstractView {
    private final Customer customer;
    private final int amount;
    private final boolean printReceipt;

    public CustomerWithdrawalResultView(ATMApplicationContext context, Customer customer, int amount, boolean printReceipt) {
        super(context);

        this.customer = customer;
        this.amount = amount;
        this.printReceipt = printReceipt;
    }

    @Override
    public JPanel createViewJPanel() {
        JTextArea receiptTextArea = new JTextArea(
                String.format("Withdrawal for %s %s", customer.getForename(), customer.getSurname()) + "\n" +
                String.format("    remaining balance: %1$,.2f", customer.getBalance()) + "\n" +
                String.format("    remaining withdrawal limit: %d", customer.getWithdrawalLimit()) + "\n" +
                String.format("    amount: %d", amount) + "\n"
        );

        Action closeWithdrawalAction;
        if (printReceipt) {
            closeWithdrawalAction = new CloseTransactionAndPrintReceiptAction(context, receiptTextArea);
        } else {
            closeWithdrawalAction = new CloseTransactionAction(context);
        }

        JButton closeButton = new JButton(closeWithdrawalAction);
        closeButton.setText(printReceipt ? "Close and print" : "Close");

        JPanel panel = new JPanel();
        panel.add(receiptTextArea);
        panel.add(closeButton);

        return panel;
    }
}
