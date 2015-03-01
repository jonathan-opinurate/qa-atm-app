package garyttierney.atmapp.module;

import com.google.inject.Binder;
import com.google.inject.Module;

public class InMemoryDatabaseModule implements Module {
    /**
     * Contributes bindings and other configurations for this module to {@code binder}.
     * <p>
     * <p><strong>Do not invoke this method directly</strong> to install submodules. Instead use
     * {@link com.google.inject.Binder#install(com.google.inject.Module)}, which ensures that {@link Provides provider methods} are
     * discovered.
     *
     * @param binder
     */
    @Override
    public void configure(Binder binder) {

    }
}
