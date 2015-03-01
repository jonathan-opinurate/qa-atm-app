package garyttierney.atmapp.swing.events.validation;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FailedAuthenticationEventListener implements PropertyChangeListener {
    private final ATMApplicationContext context;
    private final CustomerValidationView view;
    private final int maxAttempts;

    public FailedAuthenticationEventListener(ATMApplicationContext context, CustomerValidationView view, int maxAttempts) {
        this.context = context;
        this.view = view;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int numAttempts = (Integer) evt.getNewValue();

        // exceeded max number of attempts, lock out and "swallow" card.
        if (numAttempts == maxAttempts) {

        }
    }
}
