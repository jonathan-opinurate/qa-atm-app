# USER GUIDE

# System requirements

* Java 8

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
