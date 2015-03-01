package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.swing.action.CloseTransactionAction;
import garyttierney.atmapp.swing.action.CloseTransactionAndPrintReceiptAction;

import javax.swing.*;

public class CustomerBalanceView extends AbstractView {
    private final Customer customer;
    private final boolean print;

    public CustomerBalanceView(ATMApplicationContext context, Customer customer, boolean print) {
        super(context);

        this.customer = customer;
        this.print = print;
    }

    @Override
    public JPanel createViewJPanel() {
        JTextArea textArea = new JTextArea(
                String.format("Balance for %s %s", customer.getForename(), customer.getSurname()) + "\n" +
                String.format("    balance: " + customer.getBalance()) + "\n" +
                String.format("    remaining withdrawal limit: " + customer.getWithdrawalLimit()) + "\n"
        );

        textArea.setEditable(false);
        textArea.setEnabled(false);

        Action closeAction;
        if (print) {
            closeAction = new CloseTransactionAndPrintReceiptAction(context, textArea);
        } else {
            closeAction = new CloseTransactionAction(context);
        }

        JButton closeButton = new JButton(closeAction);
        closeButton.setText(print? "Close and print" : "Close");

        JPanel panel = new JPanel();
        panel.add(textArea);
        panel.add(closeButton);

        return panel;
    }
}
