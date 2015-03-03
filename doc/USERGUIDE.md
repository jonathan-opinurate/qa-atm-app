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

