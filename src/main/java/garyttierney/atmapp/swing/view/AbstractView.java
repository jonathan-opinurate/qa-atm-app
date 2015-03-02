package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;

import javax.swing.*;

public abstract class AbstractView {
    protected final ATMApplicationContext context;

    public AbstractView(ATMApplicationContext context) {
        this.context = context;
    }

    // @todo - i could invert control here and change the method signature from JPanel createViewJPanel to void bindTo(JPanel), this would
    // allow for functional testing of views.
    public abstract JPanel createViewJPanel();
}
