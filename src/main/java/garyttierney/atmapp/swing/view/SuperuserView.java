package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;

import javax.swing.*;

public class SuperuserView extends AbstractView {

    public SuperuserView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {
        JButton resetLockdownButton = new JButton();
        resetLockdownButton.setText("Reset lockdown");

        JButton lockdownButton = new JButton();
        lockdownButton.setText("Lockdown");

        JPanel panel = new JPanel();
        panel.add(lockdownButton);
        panel.add(resetLockdownButton);

        return new JPanel();
    }
}
