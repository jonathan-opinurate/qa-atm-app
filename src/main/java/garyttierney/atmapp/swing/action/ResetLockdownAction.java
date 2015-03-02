package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.util.MessageDialogHelper;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResetLockdownAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final MessageDialogHelper messageDialogHelper;

    public ResetLockdownAction(ATMApplicationContext context, MessageDialogHelper messageDialogHelper) {
        this.context = context;
        this.messageDialogHelper = messageDialogHelper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.resetLockdown();

        messageDialogHelper.prompt("Success", "Successfully reset application lock!");
        context.switchTo(new CustomerValidationView(context));
    }
}
