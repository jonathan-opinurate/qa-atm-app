package garyttierney.atmapp.swing.events.validation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This event listener is responsible for validating the inputted account number when a change is made to the input.
 */
public class AccountNumberChangedEventListener implements PropertyChangeListener {
    private final JLabel accountNumberLabel;

    /**
     * Construct a new AccountNumberChangedEventListener.
     *
     * @param accountNumberLabel The JLabel to update with an error message if validation fails.
     */
    public AccountNumberChangedEventListener(JLabel accountNumberLabel) {
        this.accountNumberLabel = accountNumberLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String value = (String) evt.getNewValue();

        // first check if it's all digits
        if (!value.matches("[0-9]+")) {
            accountNumberLabel.setText("The account number must only contain numbers."); // user help
            accountNumberLabel.setForeground(Color.RED);
            return;
        }

        // then check if it's the correct length
        if (value.length() != 6) {
            accountNumberLabel.setText("The account number must be 6 digits. E.g., 000100"); // user help
            accountNumberLabel.setForeground(Color.RED);
            return;
        }

        accountNumberLabel.setForeground(Color.BLACK);
    }
}
