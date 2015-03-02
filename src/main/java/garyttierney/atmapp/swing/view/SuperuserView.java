package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.action.LockdownAction;
import garyttierney.atmapp.swing.action.ResetLockdownAction;
import garyttierney.atmapp.swing.util.DefaultSwingMessageDialogHelper;
import garyttierney.atmapp.swing.util.MessageDialogHelper;

import javax.swing.*;

public class SuperuserView extends AbstractView {

    public SuperuserView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {
        MessageDialogHelper messageDialogHelper = new DefaultSwingMessageDialogHelper();
        JButton resetLockdownButton = new JButton(new ResetLockdownAction(context, messageDialogHelper));
        resetLockdownButton.setText("Reset lockdown");

        JButton lockdownButton = new JButton(new LockdownAction(context, messageDialogHelper));
        lockdownButton.setText("Lockdown");

        JPanel panel = new JPanel();
        panel.add(lockdownButton);
        panel.add(resetLockdownButton);

        return panel;
    }
}
