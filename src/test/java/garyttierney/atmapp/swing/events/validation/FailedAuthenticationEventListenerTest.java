package garyttierney.atmapp.swing.events.validation;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import org.junit.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;

public class FailedAuthenticationEventListenerTest {

    @Test
    public void testPropertyChange_ExceededTries() {
        ATMApplicationContext context = Mockito.mock(ATMApplicationContext.class);

        CustomerValidationModel validationModel = new CustomerValidationModel();
        FailedAuthenticationEventListener listener = new FailedAuthenticationEventListener(context, 3);
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "numberOfAttempts", 2, 3);
        listener.propertyChange(event);

        Mockito.verify(context, Mockito.atLeastOnce()).switchTo(Mockito.any());
    }
}