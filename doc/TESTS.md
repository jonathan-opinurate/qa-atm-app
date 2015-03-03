| Test  | What it is supposed to do | Working | Action taken |
|-------|---------------------------|---------|--------------|
| Validate account number length | Check that the account number is 6 characters long and update message if failed |  Yes | Unit tests around CustomerAuthenticationAction and AccountNumberChangedEventListener  |
| Validate account number correct format | Check that the account number consists only of numbers and update message if failed| Yes | Unit tests around CustomerAuthenticationAction and AccountNumberChangedEventListener |
| Validate pin number length | Check that the pin number is 4 characters long and update message if failed | Yes | Unit tests around CustomerAuthenticationAction and PinNumberChangedEventListener |
| Validate pin number correct format | Check that the pin number consists only of numbers and update message if failed | Yes | Unit tests around CustomerAuthenticationAction and PinNumberChangedEventListener |
| Validate that the user is locked out after 3 failed attempts | Check that numberOfAttempts variable is equal to maxAttempts and lock out if true | Yes | Unit tests around CustomerAuthenticationAction and FailedAuthenticationEventListener |
| Validate that the user is forwarded to the options screen on a successful login | Check that the view is switched to a new CustomerOptionsView on a successful login | Yes | Unit tests around CustomerAuthenticationAction |
