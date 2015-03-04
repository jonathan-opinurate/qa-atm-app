package garyttierney.atmapp.swing.view;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.model.Customer;
import garyttierney.atmapp.swing.action.SwitchToCustomerBalanceAction;
import garyttierney.atmapp.swing.action.SwitchToCustomerWithdrawalAction;
import garyttierney.atmapp.swing.model.CustomerOptionsModel;

import javax.swing.*;

public class CustomerOptionsView extends AbstractView {
    private final CustomerOptionsModel model;

    public CustomerOptionsView(ATMApplicationContext context, Customer customer) {
        super(context);

        this.model = new CustomerOptionsModel(customer);
    }

    @Override
    public JPanel createViewJPanel() {

        JPanel panel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JButton withdrawalButton = new JButton(new SwitchToCustomerWithdrawalAction(context, model));
        withdrawalButton.setText("Make withdrawal");

        JButton viewBalanceButton = new JButton(new SwitchToCustomerBalanceAction(context, model));
        viewBalanceButton.setText("View balance");

        // user help
        JLabel infoLabel = new JLabel("Select an option to continue");

        JCheckBox printCheckBox = new JCheckBox();
        printCheckBox.setText("Print receipt");
        printCheckBox.addItemListener(e -> model.setPrintReceipt(printCheckBox.isSelected()));

        // @formatter:off
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(infoLabel)
                        .addComponent(printCheckBox))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(withdrawalButton)
                        .addComponent(viewBalanceButton))

        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(infoLabel)
                        .addComponent(withdrawalButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(printCheckBox)
                        .addComponent(viewBalanceButton))
        );

        //@formatter:on

        return panel;
    }
}
