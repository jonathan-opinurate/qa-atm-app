package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.service.CustomerWithdrawalException;
import garyttierney.atmapp.service.CustomerWithdrawalService;
import garyttierney.atmapp.swing.model.CustomerWithdrawalModel;
import garyttierney.atmapp.swing.view.CustomerWithdrawalResultView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This action represents a customers withdrawal request and encapsulates the logic of the {@link garyttierney.atmapp.service.CustomerWithdrawalService}
 * for the swing application.
 */
public class CustomerWithdrawalAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final CustomerWithdrawalModel model;
    private final CustomerWithdrawalService customerWithdrawalService;

    /**
     * Create this action and set it up with the specified model and withdrawal service.
     *
     * @param context The application context used to switch to the withdrawal result view.
     * @param model The withdrawal model we're operating on.
     * @param customerWithdrawalService The withdrawal service which handles the withdrawal request.
     */
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
