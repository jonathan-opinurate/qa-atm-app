package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;

import javax.swing.*;

public class CustomerLockedOutView extends AbstractView {
    public CustomerLockedOutView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {
        JLabel label = new JLabel("Max tries exceeded!");

        JPanel panel = new JPanel();
        panel.add(label);

        return panel;
    }
}
