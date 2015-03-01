package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.service.CustomerAuthenticationService;
import garyttierney.atmapp.swing.model.CustomerValidationModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CustomerWithdrawlAction extends AbstractAction {
    private final ATMApplicationContext context;
    private final CustomerValidationModel options;
    private final CustomerAuthenticationService customerAuthenticationService;
    private final JLabel submitResultLabel;

    public CustomerWithdrawlAction(ATMApplicationContext context, CustomerValidationModel options, CustomerAuthenticationService customerAuthenticationService,
                                        JLabel submitResultLabel) {
        this.context = context;
        this.options = options;
        this.customerAuthenticationService = customerAuthenticationService;
        this.submitResultLabel = submitResultLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
