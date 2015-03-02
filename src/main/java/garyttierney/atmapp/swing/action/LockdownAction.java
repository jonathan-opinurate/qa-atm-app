package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.util.MessageDialogHelper;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LockdownAction extends AbstractAction{
    private final ATMApplicationContext context;
    private final MessageDialogHelper messageDialogHelper;

    /**
     * Construct a new LockdownAction.
     *
     * @param context The context to call the lockdown method on.
     */
    public LockdownAction(ATMApplicationContext context, MessageDialogHelper messageDialogHelper) {
        this.context = context;
        this.messageDialogHelper = messageDialogHelper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.lockdown();

        messageDialogHelper.prompt("Success!", "Successfully locked down application!");
        context.switchTo(new CustomerValidationView(context));
    }
}
