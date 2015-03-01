package garyttierney.atmapp.swing.events.withdrawal;

import garyttierney.atmapp.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WithdrawalAmountChangedEventListener implements PropertyChangeListener {

    private final Customer customer;
    private final JLabel withdrawalMessageLabel;

    public WithdrawalAmountChangedEventListener(Customer customer, JLabel withdrawalMessageLabel) {
        this.customer = customer;
        this.withdrawalMessageLabel = withdrawalMessageLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int amount = (Integer) evt.getNewValue();

        if (amount > customer.getBalance()) {
            withdrawalMessageLabel.setForeground(Color.RED);
            withdrawalMessageLabel.setText("Insufficient funds");
            return;
        }

        withdrawalMessageLabel.setForeground(Color.GREEN);
        withdrawalMessageLabel.setText("Amount: " + amount);
    }
}
