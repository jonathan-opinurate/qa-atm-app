# USER GUIDE

# System requirements

* Java 8

# Purpose

This program provides an implementation of a cash machine simulator. It allows the user to login with their account number (pseudo-card) and pin number, and select
the option to make a withdrawal or get a balance. When the user makes an option, they are allowed to print the transaction using the default printing device.

It also allows a superuser to login with the special account number and pin 999999 and 1234 to lockdown or unlock the application from any customer interaction.

The default set of credentials is hardcoded into CustomerFixtures but when used with the SQLite database driver it is possible to define new customers and superusers
as long as a unique account number is chosen.

#Instructions

## Logging in

Upon starting the application, you will be presented with the following view:

![Customer login screen]
(http://i.imgur.com/UeLnXIv.png)

Which will allow you to login with a given set of credentials, which are provided in a table below. 
If there was an error with the credentials you have submitted, e.g., a stray letter or mismatch in account / pin number length, the appropriate input label will update with a message telling you what the issue is.

![Invalid account number]
(http://i.imgur.com/AfVDpS8.png)

### Credentials

| Account number | Pin number | Balance |
|-------------------------|------------------|-------------|
|  000101               | 5412            | 250.00  |
|  000202               | 0219            | 350.00  |
|  999999               | 1234            | 2500000.00 |

*Note: the account number 999999 and pin number 1234 will allow you to login with superuser access, more info at the bottom of this document.*

## Options

Upon a successful login, you'll be taken to the the options page where you are presented with the choice of getting a balance or making a withdrawal. There is also a print checkbox, which when selected will prompt you to print the receipt of the option you selected.

![Select an option]
(http://i.imgur.com/ZotMWbw.png)

## Making a withdrawal

![Make a withdrawal]
(http://i.imgur.com/bw3LTXL.png)

If you wish to make a withdrawal you will be prompted with the above dialog which allows you to select from a variety of pre-defined inputs or enter any arbitrary multiple of 10 (daily withdrawal limit of 250 still applies).

If the amount you wish to withdraw exceeds your balance then you will be prompted with a message telling you so.

![Insufficient funds]
(http://i.imgur.com/xbtTpg8.png)

If at any point during the balance should an unknown error occur your balance willed be rolled back to its initial amount and you will be prompted with a message notifying you that there was an error, and will give you a chance to retry the transaction. Otherwise, you will be forwarded onto the withdrawal result view with a printouf of the receipt.

![Withdrawal result]
(http://i.imgur.com/klThE4R.png)

Once you have finished you can click the Close button to return to the ATM main menu.

## Get a balance

If you selected the Balance option, you will be presented with a similar screen to the withdrawal system giving you a receipt and the chance to go back to the login page.

![Get a balance]
(http://i.imgur.com/24OYnCf.png)

## Superuser view

If you logged in as a superuser you will be given the chance to go to the superuser dashboard or continue to the customer options menu:

![Welcome administrator]
(http://i.imgur.com/y7Vfcu4.png)

If you opt to go to the superuser view then you will be presented with the option to lockdown or reset the lock on the application.

![Set or reset lock]
(http://i.imgur.com/vmuwFDd.png)
