package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.service.CustomerAuthenticationService;
import garyttierney.atmapp.swing.action.CustomerAuthenticationAction;
import garyttierney.atmapp.swing.events.validation.AccountNumberChangedEventListener;
import garyttierney.atmapp.swing.events.validation.FailedAuthenticationEventListener;
import garyttierney.atmapp.swing.events.validation.PinNumberChangedEventListener;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import garyttierney.atmapp.swing.util.DefaultSwingMessageDialogHelper;
import garyttierney.atmapp.swing.util.DocumentListenerAdapter;
import garyttierney.atmapp.swing.util.MessageDialogHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class CustomerValidationView extends AbstractView {
    private final CustomerValidationModel model = new CustomerValidationModel();

    public CustomerValidationView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {
        JLabel accountNumberLabel = new JLabel("Please enter your 6 digit account number here");
        JLabel pinNumberLabel = new JLabel("Please enter your 4 digit pin number here");

        model.addPropertyChangeListener("accountNumber", new AccountNumberChangedEventListener(accountNumberLabel));
        model.addPropertyChangeListener("pinNumber", new PinNumberChangedEventListener(pinNumberLabel));
        model.addPropertyChangeListener("numberOfAttempts", new FailedAuthenticationEventListener(context, 3));
        JPasswordField accountNumberField = new JPasswordField(6);
        JPasswordField pinNumberField = new JPasswordField(4);

        accountNumberField.getDocument().addDocumentListener(new DocumentListenerAdapter() {
            @Override
            public void documentChanged(DocumentEvent event) {
                try {
                    model.setAccountNumber(event.getDocument().getText(0, event.getDocument().getLength()));
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });

        pinNumberField.getDocument().addDocumentListener(new DocumentListenerAdapter() {
            @Override
            public void documentChanged(DocumentEvent event) {
                try {
                    model.setPinNumber(event.getDocument().getText(0, event.getDocument().getLength()));
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });

        MessageDialogHelper messageDialogHelper = new DefaultSwingMessageDialogHelper();
        JLabel submitOptionsLabel = new JLabel("Click the submit button to login.");
        Action submitOptionsAction = new CustomerAuthenticationAction(
            context, model, context.getService(CustomerAuthenticationService.class), submitOptionsLabel, messageDialogHelper
        );

        JButton submitOptionsButton = new JButton(submitOptionsAction);
        submitOptionsButton.setText("Submit");
        submitOptionsLabel.setLabelFor(submitOptionsButton);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 100));

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // @formatter:off
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(accountNumberLabel)
                        .addComponent(accountNumberField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(pinNumberLabel)
                        .addComponent(pinNumberField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(submitOptionsLabel)
                        .addComponent(submitOptionsButton))
        );

        layout.linkSize(SwingConstants.VERTICAL, accountNumberLabel, accountNumberField);
        layout.linkSize(SwingConstants.VERTICAL, pinNumberLabel, pinNumberField);
        layout.linkSize(SwingConstants.VERTICAL, submitOptionsLabel, submitOptionsButton);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(accountNumberLabel)
                        .addComponent(pinNumberLabel)
                        .addComponent(submitOptionsLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(accountNumberField)
                        .addComponent(pinNumberField)
                        .addComponent(submitOptionsButton))
        );

        layout.linkSize(SwingConstants.HORIZONTAL, accountNumberLabel, pinNumberLabel, submitOptionsLabel);
        layout.linkSize(SwingConstants.HORIZONTAL, accountNumberField, pinNumberField, submitOptionsButton);

        //@formatter:on

        return panel;
    }
}
