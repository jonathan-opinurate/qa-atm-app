# DEVELOPMENT GUIDE

## Requirements

JDK 8
Maven >= 2

## General notes

This application is setup in a way that allows you to easily override the default withdrawal and authentication mechanisms. A `Module` is able
to bind a `CustomerRepository` instance which is then injected into the `CustomerAuthentication` and `CustomerWithdrawalService`s. Google Guice
is used to provide this modularity feature.

It is built in using the MVC design pattern and couples models to views and handles actions through controllers (event listeners). The
majority of events are handled via Swing `Action`s and the java beans `PropertyChangeEventSupport`, which models call when they are updated.

When a `AbstractView` is created it has the chance to bind `PropertyChangeEventListener`s to these models, and update themselves based on any events that are fired. It
can also bind an `Action` to some component on the form which takes the model and performs an operation with it.

### Customer Validation

The initial validation form consists of 1 submit button and 2 inputs, one for account number, and one for a pin number. Each have bound DocumentListeners which update
the CustomerValidationModel which then fires off PropertyChangeEvents to fires off the respective validation event listener for each input. If validation failed,
the respective label for the input will be colored red and will be updated with an appropriate validation message. If validation succeeded, the message will reset to black
text but the message itself will not change.

The submit button is created with an instance of `CustomerAuthenticationAction`, which calls the `CustomerAuthenticationService` to check for a customer matching these credentials.
If there is a match, we proceed to the `CustomerOptionsView`, which will allow us to select an option. If not, an exception is thrown and the cause is shown to the user on screen.

Each time the user inputs invalid credentials we increment the number of login attempts they have made, and call a `FailedAuthenticationEventListener`, if this listener
decides that the user has exceeded the maximum number of events then we show them a window with a message informing them that they have been locked out.

*Note: in the special case of a super user logging in, they are prompted with a yes/no dialog allowing them to proceed to the `SuperuserView`*

### Customer Option Selection

Upon passing validation, the user will be presented with a new view, `CustomerOptionsView`. This again is coupled to a Model, `CustomerOptionsModel`, which encapsulates
all the data related to this view. The view consists of 2 buttons, 1 for "View Balance" and one for "Make withdrawal". It also has a checkbox, which dictates
whether or not the chosen action will print a receipt or not.

Each button has an Action registered to it like before. The view balance button has a `SwitchToCustomerBalanceAction` and the make withdrawal button has a
`SwitchToCustomerWithdrawalAction`. Both are simply Actions which forward the customer on to the next step.

### Customer Balance

The customer balance view consists of a single JTextArea with the balance receipt and a button for closing the window. If the user ticked the "printing" checkbox
during the previous step, that flag will be passed into this view and the button will also attempt to print the receipt before it returns to the login view.

### Customer Withdrawal

If the user wishes to make a withdrawal they will be forwarded on to the `CustomerWithdrawalView`, it consists of 5 radio buttons each with a pre-set amount and
an input box for making a withdrawal of any arbitrary amounts. When the user selects an amount, the `WithdrawalAmountChangedEventListener` is called
we check if the user has sufficient funds to make the withdrawal. If not, we update the label to tell thim this.

When the user inputs something into the input box, we check result of `amount % 10` to check if it's a multiple of 10, and if not, we also update the
on-screen label to notify the user of this.

When the user has selected a valid withdrawal amount, they can click the Submit button, which has a registered action `CustomerWithdrawalAction`. This action
calls the `CustomerWithdrawalService` which checks if the user has enough funds to make the withdrawal, and has not exceeded their daily limit, and then
deducts the amount from their balance and limit. If any unknown error (`CustomerRepositoryException`) occurred while processing the withdrawal, then the customers
balance is rolled back and they are notified.

If all goes to plan, they are forwarded to the withdrawal result screen.

### Customer Withdrawal Results

After making a successful withdrawal the user is forwarded on to the results page, where a statement containing details of the withdrawal
are printed onto screen with a button which allows the user to return to the main login page. If the user checked the box to print a receipt in the previous
option then we will attempt to print the receipt when the user presses the close button.

## Test suite

There are also some unit tests located in the src/test/java folder, which test that the withdrawal and authentication functionality is working as expected.

They can be invoked by running

```
mvn test
```

# Running the application

Firstly, to build the application and package it with its dependencies, you should run the following:
```
mvn assembly:single
```

You will need to seed the SQLite database if it with the SQLite driver by executing the following command:
```
java -jar target/atm-app-*.jar garyttierney.atmapp.tools.CreateSQLiteDatabaseTool <path-to-config>
```

Where path-to-config is a java properties file containing a dsn for the sqlite database, e.g.,
```
dsn=jdbc:sqlite:database.sqlite
```

*Note: if the database file doesn't exist it will be automatically created*

To run the application with the SQLite driver, you should execute it with the following arguments:
```
java --jar target/atm-app-*.jar --sqlite <path-to-config>
``
