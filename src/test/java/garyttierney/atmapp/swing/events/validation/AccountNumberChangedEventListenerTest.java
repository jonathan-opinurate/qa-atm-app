package garyttierney.atmapp.swing.events.validation;

import garyttierney.atmapp.swing.model.CustomerValidationModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class AccountNumberChangedEventListenerTest {

    @Test
    public void testPropertyChange_InvalidAccountNumber() throws Exception {
        JLabel labelMock = Mockito.mock(JLabel.class);

        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(labelMock).setText(textCaptor.capture());

        CustomerValidationModel validationModel = new CustomerValidationModel();
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "accountNumber", null, "whatever");
        AccountNumberChangedEventListener eventListener = new AccountNumberChangedEventListener(labelMock);

        eventListener.propertyChange(event);

        Assert.assertEquals("The account number must only contain numbers.", textCaptor.getValue());
    }

    @Test
    public void testPropertyChange_LengthMismatch() throws Exception {
        JLabel labelMock = Mockito.mock(JLabel.class);

        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(labelMock).setText(textCaptor.capture());

        CustomerValidationModel validationModel = new CustomerValidationModel();
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "accountNumber", null, "1234");
        AccountNumberChangedEventListener eventListener = new AccountNumberChangedEventListener(labelMock);

        eventListener.propertyChange(event);

        Assert.assertEquals("The account number must be 6 digits. E.g., 000100", textCaptor.getValue());
    }
}