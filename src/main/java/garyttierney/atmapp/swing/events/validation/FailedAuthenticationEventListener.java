package garyttierney.atmapp.swing.events.validation;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.view.CustomerLockedOutView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FailedAuthenticationEventListener implements PropertyChangeListener {
    private final ATMApplicationContext context;
    private final int maxAttempts;

    public FailedAuthenticationEventListener(ATMApplicationContext context, int maxAttempts) {
        this.context = context;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int numAttempts = (Integer) evt.getNewValue();

        // exceeded max number of attempts, lock out and "swallow" card.
        if (numAttempts == maxAttempts) {
            context.switchTo(new CustomerLockedOutView(context));
        }
    }
}
