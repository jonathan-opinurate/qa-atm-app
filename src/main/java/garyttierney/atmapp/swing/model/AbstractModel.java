package garyttierney.atmapp.swing.model;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;

public abstract class AbstractModel {
    protected SwingPropertyChangeSupport propertyChangeDispatcher;

    public AbstractModel() {
        propertyChangeDispatcher = new SwingPropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        propertyChangeDispatcher.addPropertyChangeListener(property, listener);
    }
}
