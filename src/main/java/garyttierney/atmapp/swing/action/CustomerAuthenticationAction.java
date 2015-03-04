package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerAuthenticationException;
import garyttierney.atmapp.service.CustomerAuthenticationService;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import garyttierney.atmapp.swing.util.MessageDialogHelper;
import garyttierney.atmapp.swing.util.MessageDialogHelper.MessageDialogChoice;
import garyttierney.atmapp.swing.view.CustomerLockedOutView;
import garyttierney.atmapp.swing.view.CustomerOptionsView;
import garyttierney.atmapp.swing.view.SuperuserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This action represents a customer login request and invokes the {@link garyttierney.atmapp.service.CustomerAuthenticationService} to try
 * and load a customer using their credentials.
 */
public class CustomerAuthenticationAction extends AbstractAction {

    private final ATMApplicationContext context;
    private final CustomerValidationModel options;
    private final CustomerAuthenticationService customerAuthenticationService;
    private final JLabel submitResultLabel;
    private final MessageDialogHelper messageDialogHelper;

    /**
     * Create a new action which handles a user submitting an account and pin number.
     *
     * @param context The application context used for switching to the {@link garyttierney.atmapp.swing.view.CustomerOptionsView}.
     * @param options The customer validation model we're operating on.
     * @param customerAuthenticationService The authentication server which is responsible for actually authenticating the user input.
     * @param submitResultLabel The label to update with customer authentication attempts.
     */
    public CustomerAuthenticationAction(
        ATMApplicationContext context,
        CustomerValidationModel options,
        CustomerAuthenticationService customerAuthenticationService,
        JLabel submitResultLabel,
        MessageDialogHelper messageDialogHelper
    ) {
        this.context = context;
        this.options = options;
        this.customerAuthenticationService = customerAuthenticationService;
        this.submitResultLabel = submitResultLabel;
        this.messageDialogHelper = messageDialogHelper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Customer customer = customerAuthenticationService.authenticate(options.getAccountNumber(), options.getPinNumber());
            if (customer.isSuperuser()) { // forward on to management view

                // user help
                MessageDialogChoice choice = messageDialogHelper.promptWithChoice(
                    "Welcome administrator!",
                    "Would you like to view the superuser dashboard?",
                    "Yes", "No - continue",
                    false
                );

                if (choice == MessageDialogChoice.YES) {
                    context.switchTo(new SuperuserView(context));
                    return;
                }

                // else, continue as normal
            }

            if (!context.isLockedDown()) {
                context.switchTo(new CustomerOptionsView(context, customer));
            } else {
                // user help
                messageDialogHelper.prompt("Error!", "Sorry, the application is currently locked down!");
                context.switchTo(new CustomerLockedOutView(context));
            }
        } catch (CustomerAuthenticationException ex) {
            options.incrementNumberOfAttempts();
            submitResultLabel.setForeground(Color.RED);
            submitResultLabel.setText(ex.getMessage());
        }
    }
}
