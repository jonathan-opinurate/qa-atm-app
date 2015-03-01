package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;

import javax.swing.*;

public abstract class AbstractView {
    protected final ATMApplicationContext context;

    public AbstractView(ATMApplicationContext context) {
        this.context = context;
    }

    public abstract JPanel createViewJPanel();
}
