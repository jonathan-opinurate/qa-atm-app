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
        frame.dispose();

        frame = new JFrame("ATM");
        JPanel panel = view.createViewJPanel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
