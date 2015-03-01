package garyttierney.atmapp.swing.events.validation;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PinNumberChangedEventListener implements PropertyChangeListener {
    private final JLabel pinMessageLabel;

    public PinNumberChangedEventListener(JLabel pinMessageLabel) {
        this.pinMessageLabel = pinMessageLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String value = (String) evt.getNewValue();

        // first check if it's all digits
        if (!value.matches("[0-9]+")) {
            pinMessageLabel.setText("The account number must only contain numbers.");
            return;
        }

        // then check if it's the correct length
        if (value.length() != 8) {
            pinMessageLabel.setText("The account number must be 4 digits. E.g., 1234");
            return;
        }
    }
}
