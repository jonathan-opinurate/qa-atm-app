package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerAuthenticationException;
import garyttierney.atmapp.service.CustomerAuthenticationService;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import garyttierney.atmapp.swing.view.CustomerOptionsView;
import garyttierney.atmapp.swing.view.SuperuserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomerAuthenticationAction extends AbstractAction {

    private final ATMApplicationContext context;
    private final CustomerValidationModel options;
    private final CustomerAuthenticationService customerAuthenticationService;
    private final JLabel submitResultLabel;

    public CustomerAuthenticationAction(
        ATMApplicationContext context,
        CustomerValidationModel options,
        CustomerAuthenticationService customerAuthenticationService,
        JLabel submitResultLabel
    ) {
        this.context = context;
        this.options = options;
        this.customerAuthenticationService = customerAuthenticationService;
        this.submitResultLabel = submitResultLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Customer customer = customerAuthenticationService.authenticate(options.getAccountNumber(), options.getPinNumber());
            if (customer.isSuperuser()) { // forward on to management view
                Object[] options = {"Yes", "No - continue"};
                int option = JOptionPane.showOptionDialog(null,  "Would you like to view the superuser dashboard?", "Welcome administrator!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

                if (option == JOptionPane.YES_OPTION) {
                    context.switchTo(new SuperuserView(context));
                    return;
                }

                // else, continue as normal
            }

            context.switchTo(new CustomerOptionsView(context, customer));
        } catch (CustomerAuthenticationException ex) {
            options.incrementNumberOfAttempts();
            submitResultLabel.setForeground(Color.RED);
            submitResultLabel.setText(ex.getMessage());
        }
    }
}
