package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;

import javax.swing.*;

public class SuperuserView extends AbstractView {

    public SuperuserView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {
        return new JPanel();
    }
}
