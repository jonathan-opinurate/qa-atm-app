package garyttierney.atmapp;

import com.google.inject.Injector;
import garyttierney.atmapp.swing.view.AbstractView;

import javax.swing.*;

public class ATMApplicationContext {

    private final Injector injector;

    public ATMApplicationContext(Injector injector) {
        this.injector = injector;

    }

    public <T> T getService(Class<? extends T> clazz) {
        return injector.getInstance(clazz);
    }

    private JFrame frame = new JFrame();

    public void switchTo(AbstractView view) {
        frame.setVisible(false);
        frame = new JFrame("ATM");
        frame.add(view.createViewJPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
