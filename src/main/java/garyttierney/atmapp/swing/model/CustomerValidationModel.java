package garyttierney.atmapp.swing.model;

public class CustomerValidationModel extends AbstractModel {
    public CustomerValidationModel() {
        super();
    }

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public int getNumberOfAttemtps() {
        return attempts;
    }

    public void incrementNumberOfAttempts() {
        attempts++;
        propertyChangeDispatcher.firePropertyChange("disabled", attempts - 1, attempts);
    }

    private int attempts = 0;
    private String pinNumber;
}
