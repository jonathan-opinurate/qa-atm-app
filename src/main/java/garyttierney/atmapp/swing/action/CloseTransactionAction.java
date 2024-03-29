package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action wraps around switching from a transaction back to the login view.
 */
public class CloseTransactionAction extends AbstractAction {
    private final ATMApplicationContext context;

    public CloseTransactionAction(ATMApplicationContext context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.switchTo(new CustomerValidationView(context));
    }
}
