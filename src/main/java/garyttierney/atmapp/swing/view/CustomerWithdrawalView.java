package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.service.CustomerWithdrawalService;
import garyttierney.atmapp.swing.action.CustomerWithdrawalAction;
import garyttierney.atmapp.swing.events.withdrawal.WithdrawalAmountChangedEventListener;
import garyttierney.atmapp.swing.model.CustomerWithdrawalModel;
import garyttierney.atmapp.swing.util.DefaultSwingMessageDialogHelper;
import garyttierney.atmapp.swing.util.DocumentListenerAdapter;
import garyttierney.atmapp.swing.util.MessageDialogHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class CustomerWithdrawalView extends AbstractView {
    private final CustomerWithdrawalModel model;

    public CustomerWithdrawalView(ATMApplicationContext context, Customer customer, boolean shouldPrintReceipt) {
        super(context);

        this.model = new CustomerWithdrawalModel(customer, shouldPrintReceipt);
    }

    @Override
    public JPanel createViewJPanel() {
        ButtonGroup radioButtonGroup = new ButtonGroup();

        JRadioButton tenRadioButton = createWithdrawalAmountRadioButton(radioButtonGroup, 10);
        JRadioButton twentyRadioButton = createWithdrawalAmountRadioButton(radioButtonGroup, 20);
        JRadioButton fiftyRadioButton = createWithdrawalAmountRadioButton(radioButtonGroup, 50);
        JRadioButton hundredRadioButton = createWithdrawalAmountRadioButton(radioButtonGroup, 100);
        JRadioButton twofiftyRadioButton = createWithdrawalAmountRadioButton(radioButtonGroup, 250);

        JLabel amountLabel = new JLabel("Amount: ...");
        model.addPropertyChangeListener("amount", new WithdrawalAmountChangedEventListener(model.getCustomer(), amountLabel));

        // user help
        JLabel arbitraryAmountLabel = new JLabel("Enter amount");
        JTextField arbitraryAmountInput = new JTextField("10");
        arbitraryAmountLabel.setLabelFor(arbitraryAmountInput);

        arbitraryAmountInput.getDocument().addDocumentListener(new DocumentListenerAdapter() {
            @Override
            public void documentChanged(DocumentEvent event) {
                try {
                    String text = event.getDocument().getText(0, event.getDocument().getLength());

                    if (!text.matches("[1-9][0-9]+") || text.isEmpty()) {
                        amountLabel.setText("Invalid value."); // user help
                        return;
                    }

                    Integer amount = Integer.parseInt(text);
                    if ((amount % 10) == 0) {
                        model.setAmount(amount);
                    } else {
                        amountLabel.setText("Expected a multiple of 10"); // user help
                    }
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        });

        MessageDialogHelper messageDialogHelper = new DefaultSwingMessageDialogHelper();

        JButton submitWithdrawalButton = new JButton(
            new CustomerWithdrawalAction(context, model, context.getService(CustomerWithdrawalService.class), messageDialogHelper)
        );
        submitWithdrawalButton.setText("Make withdrawal");

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(360, 130));

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // @formatter:off

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tenRadioButton)
                        .addComponent(twentyRadioButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fiftyRadioButton)
                        .addComponent(hundredRadioButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(twofiftyRadioButton)
                        .addComponent(arbitraryAmountInput))
                .addComponent(amountLabel)
                .addComponent(submitWithdrawalButton)
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tenRadioButton)
                        .addComponent(fiftyRadioButton)
                        .addComponent(twofiftyRadioButton)
                        .addComponent(amountLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(twentyRadioButton)
                        .addComponent(hundredRadioButton)
                        .addComponent(arbitraryAmountInput))
                .addComponent(submitWithdrawalButton)
        );

        //@formatter:on

        return panel;
    }

    private JRadioButton createWithdrawalAmountRadioButton(ButtonGroup buttonGroup, int amount) {
        JRadioButton button = new JRadioButton();
        button.setText(": " + amount);

        button.addItemListener(e -> model.setAmount(amount));
        buttonGroup.add(button);

        return button;
    }
}
