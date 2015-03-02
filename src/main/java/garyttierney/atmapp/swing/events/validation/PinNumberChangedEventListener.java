package garyttierney.atmapp.swing.events.validation;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This event listener is responsible for validating the inputted pin number when a change is made to the input.
 */
public class PinNumberChangedEventListener implements PropertyChangeListener {
    private final JLabel pinMessageLabel;

    /**
     * Construct a new PinNumberChangedEventListener.
     *
     * @param pinMessageLabel The JLabel to update with an error message if validation fails.
     */
    public PinNumberChangedEventListener(JLabel pinMessageLabel) {
        this.pinMessageLabel = pinMessageLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String value = (String) evt.getNewValue();

        // first check if it's all digits
        if (!value.matches("[0-9]+")) {
            pinMessageLabel.setText("The pin number must only contain numbers.");
            pinMessageLabel.setForeground(Color.RED);
            return;
        }

        // then check if it's the correct length
        if (value.length() != 4) {
            pinMessageLabel.setText("The pin number must be 4 digits. E.g., 1234");
            pinMessageLabel.setForeground(Color.RED);
            return;
        }

        pinMessageLabel.setForeground(Color.BLACK);
    }
}
