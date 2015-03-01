package garyttierney.atmapp.swing.events.validation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccountNumberChangedEventListener implements PropertyChangeListener {
    private final JLabel accountNumberLabel;

    public AccountNumberChangedEventListener(JLabel accountNumberLabel) {
        this.accountNumberLabel = accountNumberLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String value = (String) evt.getNewValue();

        // first check if it's all digits
        if (!value.matches("[0-9]+")) {
            accountNumberLabel.setText("The account number must only contain numbers.");
            accountNumberLabel.setForeground(Color.RED);
            return;
        }

        // then check if it's the correct length
        if (value.length() != 8) {
            accountNumberLabel.setText("The account number must be 8 digits. E.g., 00010012");
            accountNumberLabel.setForeground(Color.RED);
            return;
        }

        accountNumberLabel.setForeground(Color.BLACK);
    }
}
