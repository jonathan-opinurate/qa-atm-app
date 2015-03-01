package garyttierney.atmapp.swing.events.validation;

import garyttierney.atmapp.swing.model.CustomerValidationModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class PinNumberChangedEventListenerTest {

    @Test
    public void testPropertyChange_InvalidPinNumber() throws Exception {
        JLabel labelMock = Mockito.mock(JLabel.class);

        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(labelMock).setText(textCaptor.capture());

        CustomerValidationModel validationModel = new CustomerValidationModel();
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "pinNumber", null, "whatever");
        PinNumberChangedEventListener eventListener = new PinNumberChangedEventListener(labelMock);

        eventListener.propertyChange(event);

        Assert.assertEquals("The pin number must only contain numbers.", textCaptor.getValue());
    }

    @Test
    public void testPropertyChange_LengthMismatch() throws Exception {
        JLabel labelMock = Mockito.mock(JLabel.class);

        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(labelMock).setText(textCaptor.capture());

        CustomerValidationModel validationModel = new CustomerValidationModel();
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "pinNumber", null, "12");
        PinNumberChangedEventListener eventListener = new PinNumberChangedEventListener(labelMock);

        eventListener.propertyChange(event);

        Assert.assertEquals("The pin number must be 4 digits. E.g., 1234", textCaptor.getValue());
    }

}