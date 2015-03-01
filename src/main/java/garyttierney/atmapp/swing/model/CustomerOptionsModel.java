package garyttierney.atmapp.swing.model;

import garyttierney.atmapp.model.Customer;

public class CustomerOptionsModel {
    private boolean print;
    private final Customer customer;

    public CustomerOptionsModel(Customer customer) {
        this.customer = customer;
    }

    public boolean shouldPrintReceipt() {
        return print;
    }

    public void setPrintReceipt(boolean print) {
        this.print = print;
    }

    public Customer getCustomer() {
        return customer;
    }
}
