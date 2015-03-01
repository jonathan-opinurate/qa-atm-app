package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;

import javax.swing.*;

public class SuperuserView extends AbstractView {
    private final ATMApplicationContext context;

    public SuperuserView(ATMApplicationContext context) {
        this.context = context;
    }

    @Override
    public JPanel createViewJPanel() {
        return null;
    }
}
