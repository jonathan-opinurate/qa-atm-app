package garyttierney.atmapp;

import garyttierney.atmapp.swing.view.CustomerValidationView;

public class ATMApplication {
    public static void main(String[] argv) {
        ATMApplicationContext ctx = new ATMApplicationContext();

        ctx.switchTo(new CustomerValidationView(ctx));
    }
}
