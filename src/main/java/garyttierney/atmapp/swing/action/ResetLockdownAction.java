package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResetLockdownAction extends AbstractAction {
    private final ATMApplicationContext context;

    public ResetLockdownAction(ATMApplicationContext context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.resetLockdown();

        JOptionPane.showMessageDialog(null, "Successfully reset application lock!");
        context.switchTo(new CustomerValidationView(context));
    }
}
