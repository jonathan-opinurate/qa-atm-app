package garyttierney.atmapp.model;

public class Customer {
    private String pinNumber;
    private String accountNumber;

    private boolean superuser;
    private double balance;
    private String surname;
    private String forename;
    private int withdrawlLimit;
    private String address;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPinNumber() {
        return pinNumber;
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
     * @param superuser whether this Customer has access to the management view or not.
     */
    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getForename() {
        return forename;
    }

    public void setWithdrawlLimit(int withdrawlLimit) {
        this.withdrawlLimit = withdrawlLimit;
    }

    public int getWithdrawalLimit() {
        return withdrawlLimit;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }
}
