package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.service.CustomerWithdrawalException;
import garyttierney.atmapp.service.CustomerWithdrawalService;
import garyttierney.atmapp.swing.model.CustomerWithdrawalModel;
import garyttierney.atmapp.swing.view.CustomerWithdrawalResultView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CustomerWithdrawalAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final CustomerWithdrawalModel model;
    private final CustomerWithdrawalService customerWithdrawalService;

    public CustomerWithdrawalAction(
        ATMApplicationContext context,
        CustomerWithdrawalModel model,
        CustomerWithdrawalService customerWithdrawalService
    ) {
        this.context = context;
        this.model = model;
        this.customerWithdrawalService = customerWithdrawalService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            customerWithdrawalService.handleWithdrawal(model.getCustomer(), model.getAmount());

            context.switchTo(new CustomerWithdrawalResultView(context, model.getCustomer(), model.getAmount(), model.shouldPrintReceipt()));
        } catch (CustomerWithdrawalException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error processing withdrawal!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
