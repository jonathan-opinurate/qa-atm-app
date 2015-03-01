package garyttierney.atmapp.swing.events.withdrawal;

import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class WithdrawalAmountChangedEventListenerTest {
    @Test
    public void testPropertyChange_InsufficientFunds() {
        Customer customer = new Customer();
        customer.setBalance(10.00);

        JLabel labelMock = Mockito.mock(JLabel.class);
        WithdrawalAmountChangedEventListener eventListener = new WithdrawalAmountChangedEventListener(customer, labelMock);

        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(labelMock).setText(textCaptor.capture());

        CustomerValidationModel validationModel = new CustomerValidationModel();
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "amount", null, 50);

        eventListener.propertyChange(event);

        Assert.assertEquals("Insufficient funds", textCaptor.getValue());
    }

    @Test
    public void testPropertyChange_SufficientFunds() {
        Customer customer = new Customer();
        customer.setBalance(50.00);

        JLabel labelMock = Mockito.mock(JLabel.class);
        WithdrawalAmountChangedEventListener eventListener = new WithdrawalAmountChangedEventListener(customer, labelMock);

        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(labelMock).setText(textCaptor.capture());

        CustomerValidationModel validationModel = new CustomerValidationModel();
        PropertyChangeEvent event = new PropertyChangeEvent(validationModel, "amount", null, 50);

        eventListener.propertyChange(event);

        Assert.assertEquals("Amount: 50", textCaptor.getValue());
    }
}