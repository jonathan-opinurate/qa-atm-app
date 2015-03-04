package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.util.MessageDialogHelper;
import garyttierney.atmapp.swing.view.CustomerValidationView;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.event.ActionEvent;

public class ResetLockdownActionTest {

    @Test
    public void testActionPerformed() throws Exception {
        ATMApplicationContext context = Mockito.mock(ATMApplicationContext.class);
        MessageDialogHelper messageDialogHelper = Mockito.mock(MessageDialogHelper.class);


        ResetLockdownAction lockdownAction = new ResetLockdownAction(context, messageDialogHelper);
        lockdownAction.actionPerformed(new ActionEvent(lockdownAction, -1, "dummy"));

        Mockito.verify(context, Mockito.atLeast(1)).resetLockdown();
        Mockito.verify(context, Mockito.atLeast(1)).switchTo(Mockito.any(CustomerValidationView.class));
        Mockito.verify(messageDialogHelper, Mockito.atLeast(1)).prompt(Mockito.any(), Mockito.any());
    }
}