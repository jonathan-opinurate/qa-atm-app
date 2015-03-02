package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerWithdrawalException;
import garyttierney.atmapp.service.CustomerWithdrawalService;
import garyttierney.atmapp.swing.model.CustomerWithdrawalModel;
import garyttierney.atmapp.swing.util.MessageDialogHelper;
import garyttierney.atmapp.swing.view.CustomerWithdrawalResultView;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.event.ActionEvent;

public class CustomerWithdrawalActionTest {

    @Test
    public void testActionPerformed_MessageDialogOnBadTransaction() throws Exception {
        MessageDialogHelper messageDialogHelper = Mockito.mock(MessageDialogHelper.class);
        CustomerWithdrawalService withdrawalService = Mockito.mock(CustomerWithdrawalService.class);

        Customer customer = new Customer();
        CustomerWithdrawalModel withdrawalModel = new CustomerWithdrawalModel(customer, false);

        Mockito.doThrow(new CustomerWithdrawalException("empty")).when(withdrawalService).handleWithdrawal(Mockito.any(Customer.class), Mockito.anyInt());

        CustomerWithdrawalAction action = new CustomerWithdrawalAction(null, withdrawalModel, withdrawalService, messageDialogHelper);
        action.actionPerformed(new ActionEvent("dummy", 0, "dummyCommand"));
        Mockito.verify(messageDialogHelper, Mockito.only()).prompt(Mockito.anyString(), Mockito.anyString());
    }


    @Test
    public void testActionPerformed_Successful() throws Exception {
        ATMApplicationContext context = Mockito.mock(ATMApplicationContext.class);
        CustomerWithdrawalService withdrawalService = Mockito.mock(CustomerWithdrawalService.class);

        Customer customer = new Customer();
        CustomerWithdrawalModel withdrawalModel = new CustomerWithdrawalModel(customer, false);

        Mockito.doNothing().when(withdrawalService).handleWithdrawal(Mockito.any(Customer.class), Mockito.anyInt());

        CustomerWithdrawalAction action = new CustomerWithdrawalAction(context, withdrawalModel, withdrawalService, null);
        action.actionPerformed(new ActionEvent("dummy", 0, "dummyCommand"));
        Mockito.verify(context, Mockito.only()).switchTo(Mockito.any(CustomerWithdrawalResultView.class));
    }
}