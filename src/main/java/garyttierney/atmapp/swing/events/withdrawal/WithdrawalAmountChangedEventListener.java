package garyttierney.atmapp.swing.events.withdrawal;

import garyttierney.atmapp.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This event listener is reponsible for handling updates to the requested withdrawal amount, and updating an on screen label with a message
 * telling the user the currently selected amount, or if they have insufficient funds to continue with this transaction.
 */
public class WithdrawalAmountChangedEventListener implements PropertyChangeListener {

    private final Customer customer;
    private final JLabel withdrawalMessageLabel;

    /**
     * Construct a new WithdrawalAmountChangedEventListener.
     *
     * @param customer A reference to the {@link garyttierney.atmapp.model.Customer} who wishes to make the withdrawal.
     * @param withdrawalMessageLabel A JLabel to update with a message detailing the requested withdrawal amount.
     */
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
