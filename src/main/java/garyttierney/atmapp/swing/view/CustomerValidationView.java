package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.service.CustomerAuthenticationService;
import garyttierney.atmapp.swing.action.CustomerAuthenticationAction;
import garyttierney.atmapp.swing.events.validation.AccountNumberChangedEventListener;
import garyttierney.atmapp.swing.events.validation.PinNumberChangedEventListener;
import garyttierney.atmapp.swing.model.CustomerValidationModel;
import garyttierney.atmapp.swing.util.DocumentListenerAdapter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

public class CustomerValidationView extends AbstractView {
    private final CustomerValidationModel model = new CustomerValidationModel();

    public CustomerValidationView(ATMApplicationContext context) {
        super(context);
    }

    @Override
    public JPanel createViewJPanel() {

        JLabel accountNumberLabel = new JLabel("Please enter your 8 digit account number here");
        JLabel pinNumberLabel = new JLabel("Please enter your 4 digit pin number here");

        model.addPropertyChangeListener("accountNumber", new AccountNumberChangedEventListener(accountNumberLabel));
        model.addPropertyChangeListener("pinNumber", new PinNumberChangedEventListener(pinNumberLabel));

        JPasswordField accountNumberField = new JPasswordField(8);
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

        JLabel submitOptionsLabel = new JLabel("Click the submit button to login.");
        Action submitOptionsAction = new CustomerAuthenticationAction(null, model, context.getService(CustomerAuthenticationService.class), submitOptionsLabel);

        JButton submitOptionsButton = new JButton(submitOptionsAction);
        submitOptionsButton.setText("Submit");
        submitOptionsLabel.setLabelFor(submitOptionsButton);

        JPanel panel = new JPanel();

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
