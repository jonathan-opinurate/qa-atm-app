package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.model.CustomerOptionsModel;
import garyttierney.atmapp.swing.view.CustomerBalanceView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action wraps switching from the {@link garyttierney.atmapp.swing.view.CustomerOptionsView} view to the {@link garyttierney.atmapp.swing.view.CustomerBalanceView}.
 */
public class SwitchToCustomerBalanceAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final CustomerOptionsModel model;

    /**
     * Construct a new SwitchToCustomerBalanceAction.
     *
     * @param context The application context, used for switching views.
     * @param model The selected options, so we can pass on the {@link garyttierney.atmapp.model.Customer} and whether or not we should print a receipt.
     */
    public SwitchToCustomerBalanceAction(ATMApplicationContext context, CustomerOptionsModel model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.switchTo(new CustomerBalanceView(context, model.getCustomer(), model.shouldPrintReceipt()));
    }
}
