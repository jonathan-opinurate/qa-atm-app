package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.model.CustomerOptionsModel;
import garyttierney.atmapp.swing.view.CustomerBalanceView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SwitchToCustomerBalanceAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final CustomerOptionsModel model;

    public SwitchToCustomerBalanceAction(ATMApplicationContext context, CustomerOptionsModel model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.switchTo(new CustomerBalanceView(context, model.getCustomer(), model.shouldPrintReceipt()));
    }
}
