package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LockdownAction extends AbstractAction{
    private final ATMApplicationContext context;

    /**
     * Construct a new LockdownAction.
     *
     * @param context The context to call the lockdown method on.
     */
    public LockdownAction(ATMApplicationContext context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.lockdown();

        JOptionPane.showMessageDialog(null, "Successfully locked down application!");
        context.switchTo(new CustomerValidationView(context));
    }
}
