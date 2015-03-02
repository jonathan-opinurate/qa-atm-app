package garyttierney.atmapp.swing.events.validation;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.view.CustomerLockedOutView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This event listener is responsible for handling failed authentication events, and eventually locking the user out when they have exceeded
 * the maximum number of attempts.
 */
public class FailedAuthenticationEventListener implements PropertyChangeListener {
    private final ATMApplicationContext context;
    private final int maxAttempts;

    /**
     * Construct a new FailedAuthenticationEventListener.
     *
     * @param context The application context, used for switching to the {@link garyttierney.atmapp.swing.view.CustomerLockedOutView} if the user exceeds <code>maxAttempts</code>.
     * @param maxAttempts The maximum number of attempts permitted by this event listener.
     */
    public FailedAuthenticationEventListener(ATMApplicationContext context, int maxAttempts) {
        this.context = context;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Handle property change.");
        int numAttempts = (Integer) evt.getNewValue();

        // exceeded max number of attempts, lock out and "swallow" card.
        if (numAttempts == maxAttempts) {
            context.switchTo(new CustomerLockedOutView(context));
        }
    }
}
