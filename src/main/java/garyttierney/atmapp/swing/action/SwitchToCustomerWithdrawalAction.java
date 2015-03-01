package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.model.CustomerOptionsModel;
import garyttierney.atmapp.swing.view.CustomerWithdrawalView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SwitchToCustomerWithdrawalAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final CustomerOptionsModel model;

    public SwitchToCustomerWithdrawalAction(ATMApplicationContext context, CustomerOptionsModel model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        context.switchTo(new CustomerWithdrawalView(context, model.getCustomer(), model.shouldPrintReceipt()));
    }
}
