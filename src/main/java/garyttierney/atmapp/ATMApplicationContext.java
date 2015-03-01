package garyttierney.atmapp;

import garyttierney.atmapp.swing.view.AbstractView;

import javax.swing.*;

public class ATMApplicationContext {
    public <T> T getService(Class<? extends T> clazz) {
        return null;
    }

    private JFrame frame = new JFrame();

    public void switchTo(AbstractView view) {
        frame.setVisible(false);
        frame = new JFrame();
        frame.add(view.createViewJPanel());
        frame.setVisible(true);
    }
}
