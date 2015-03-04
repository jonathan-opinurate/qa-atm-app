package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerAuthenticationException;
import garyttierney.atmapp.service.CustomerAuthenticationService;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import garyttierney.atmapp.swing.util.MessageDialogHelper;
import garyttierney.atmapp.swing.view.CustomerLockedOutView;
import garyttierney.atmapp.swing.view.CustomerOptionsView;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerAuthenticationActionTest {

    @Test
    public void testActionPerformed_Superuser() throws Exception {
        CustomerValidationModel validationModel = new CustomerValidationModel();
        validationModel.setAccountNumber("000001");
        validationModel.setPinNumber("0012");

        ATMApplicationContext context = Mockito.mock(ATMApplicationContext.class);
        MessageDialogHelper messageDialogHelper = Mockito.mock(MessageDialogHelper.class);
        CustomerAuthenticationService customerAuthenticationService = Mockito.mock(CustomerAuthenticationService.class);
        Customer customer = new Customer();
        customer.setSuperuser(true);

        Mockito.doReturn(customer).when(customerAuthenticationService).authenticate(Mockito.any(), Mockito.any());
        Mockito.doReturn(MessageDialogHelper.MessageDialogChoice.YES).when(messageDialogHelper).promptWithChoice(Mockito.any(), Mockito.any(),
                Mockito.any(), Mockito.any(), Mockito.eq(false));

        CustomerAuthenticationAction customerAuthenticationAction = new CustomerAuthenticationAction(
            context,
            validationModel,
            customerAuthenticationService,
            null,
            messageDialogHelper
        );

        customerAuthenticationAction.actionPerformed(new ActionEvent(validationModel, -1, "dummy"));

        Mockito.verify(context, Mockito.only()).switchTo(Mockito.any(CustomerOptionsView.class));
    }

    @Test
    public void testActionPerformed_Lockdown() throws Exception {
        CustomerValidationModel validationModel = new CustomerValidationModel();
        validationModel.setAccountNumber("000001");
        validationModel.setPinNumber("0012");

        ATMApplicationContext context = Mockito.mock(ATMApplicationContext.class);
        MessageDialogHelper messageDialogHelper = Mockito.mock(MessageDialogHelper.class);
        CustomerAuthenticationService customerAuthenticationService = Mockito.mock(CustomerAuthenticationService.class);
        Customer customer = new Customer();
        customer.setSuperuser(false);

        Mockito.doReturn(customer).when(customerAuthenticationService).authenticate(Mockito.any(), Mockito.any());
        Mockito.doReturn(true).when(context).isLockedDown();
        Mockito.doNothing().when(messageDialogHelper).prompt(Mockito.any(), Mockito.any());

        CustomerAuthenticationAction customerAuthenticationAction = new CustomerAuthenticationAction(
            context,
            validationModel,
            customerAuthenticationService,
            null,
            messageDialogHelper
        );

        customerAuthenticationAction.actionPerformed(new ActionEvent(validationModel, -1, "dummy"));

        Mockito.verify(context, Mockito.atLeastOnce()).switchTo(Mockito.any(CustomerLockedOutView.class));
    }

    @Test
    public void testActionPerformed_FailedAuthentication() throws Exception {
        JLabel messageLabel = Mockito.mock(JLabel.class);
        CustomerValidationModel validationModel = Mockito.mock(CustomerValidationModel.class);
        ATMApplicationContext context = Mockito.mock(ATMApplicationContext.class);
        MessageDialogHelper messageDialogHelper = Mockito.mock(MessageDialogHelper.class);
        CustomerAuthenticationService customerAuthenticationService = Mockito.mock(CustomerAuthenticationService.class);

        Customer customer = new Customer();
        customer.setSuperuser(false);

        Mockito.doReturn("1212").when(validationModel).getAccountNumber();
        Mockito.doReturn("1212").when(validationModel).getPinNumber();

        Mockito.doThrow(new CustomerAuthenticationException("dummy")).when(customerAuthenticationService).authenticate(Mockito.any(), Mockito.any());
        Mockito.doReturn(false).when(context).isLockedDown();

        CustomerAuthenticationAction customerAuthenticationAction = new CustomerAuthenticationAction(
            context,
            validationModel,
            customerAuthenticationService,
            messageLabel,
            messageDialogHelper
        );

        customerAuthenticationAction.actionPerformed(new ActionEvent(validationModel, -1, "dummy"));

        Mockito.verify(validationModel, Mockito.atLeastOnce()).incrementNumberOfAttempts();
        Mockito.verify(messageLabel, Mockito.times(1)).setForeground(Mockito.eq(Color.RED));
        Mockito.verify(messageLabel, Mockito.times(1)).setText(Mockito.any());
    }
}