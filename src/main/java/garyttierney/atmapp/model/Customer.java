package garyttierney.atmapp.model;

/**
 * This class represents a customer in the database and all of their attributes.
 */
public class Customer {

    /**
     * The 4 digit pin number (effectively password) for this customer.
     */
    private String pinNumber;

    /**
     * The 8 digit pin number (effectively username) for this customer.
     */
    private String accountNumber;

    /**
     * The superuser flag for this customer (whether or not they can access the superuser view).
     */
    private boolean superuser;

    /**
     * The current balance of this customer.
     */
    private double balance;

    /**
     * The customers surname.
     */
    private String surname;

    /**
     * The customers forename.
     */
    private String forename;

    /**
     * The customers remaining daily withdrawal limit.
     */
    private int withdrawalLimit;

    /**
     * The customers address.
     */
    private String address;
    private boolean blacklisted;

    /**
     * Gets the account number.
     *
     * @return the 8 digit account number (effectively username) for this Customer.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the account number.
     *
     * @param accountNumber the 8 digit account number (effectively username) for this Customer.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the pin number.
     *
     * @return the 4 digit pin number (effectively password) for this Customer.
     */
    public String getPinNumber() {
        return pinNumber;
    }

    /**
     * Sets the pin number of this customer.
     *
     * @param pinNumber the 4 digit pin number (effectively password) for this customer.
     */
    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    /**
     * Get the superuser flag.
     *
     * @return whether this Customer has access to the management view or not.
     */
    public boolean isSuperuser() {
        return superuser;
    }

    /**
     * Set the superuser flag.
     *
     * @param superuser whether this Customer has access to the management view or not.
     */
    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    /**
     * Gets the customers surname.
     *
     * @return The surname of this customer.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the customers surname.
     *
     * @param surname The surname of this customer.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the customers forename.
     *
     * @return The forename of this customer.
     */
    public String getForename() {
        return forename;
    }

    /**
     * Sets the customers forename.
     *
     * @param forename The forename of this customer.
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Gets the daily withdrawal limit for this customer.
     *
     * @return The daily withdrawal limit.
     */
    public int getWithdrawalLimit() {
        return withdrawalLimit;
    }

    /**
     * Sets the daily withdrawal limit for this customer.
     *
     * @param withdrawalLimit The daily withdrawal limit.
     */
    public void setWithdrawalLimit(int withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    /**
     * Gets this customers balance.
     *
     * @return The balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets this customers balance.
     *
     * @param balance The balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the address of this customer.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of this customer.
     *
     * @param address The address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }
}
