package garyttierney.atmapp.swing.model;

import garyttierney.atmapp.model.Customer;

public class CustomerWithdrawalModel extends AbstractModel {

    private final Customer customer;
    private final boolean printReceipt;

    public CustomerWithdrawalModel(Customer customer, boolean shouldPrintReceipt) {
        this.customer = customer;
        this.printReceipt = shouldPrintReceipt;
    }

    private int amount = 0;

    public void setAmount(int amount) {
        propertyChangeDispatcher.firePropertyChange("amount", this.amount, amount);

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
    
    public boolean shouldPrintReceipt() {
        return printReceipt;
    }

    public Customer getCustomer() {
        return customer;
    }
}
