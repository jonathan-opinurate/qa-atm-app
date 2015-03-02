package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.action.LockdownAction;
import garyttierney.atmapp.swing.action.ResetLockdownAction;

import javax.swing.*;

public class SuperuserView extends AbstractView {

    public SuperuserView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {
        JButton resetLockdownButton = new JButton(new ResetLockdownAction(context));
        resetLockdownButton.setText("Reset lockdown");

        JButton lockdownButton = new JButton(new LockdownAction(context));
        lockdownButton.setText("Lockdown");

        JPanel panel = new JPanel();
        panel.add(lockdownButton);
        panel.add(resetLockdownButton);

        return panel;
    }
}
