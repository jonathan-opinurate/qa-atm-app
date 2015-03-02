package garyttierney.atmapp.swing.util;

import javax.swing.*;

public class DefaultSwingMessageDialogHelper implements MessageDialogHelper {
    @Override
    public String promptWithInput(String title, String question) {
        return JOptionPane.showInputDialog(null, question, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public MessageDialogChoice promptWithChoice(String title, String message, String yesText, String noText, boolean allowCancel) {
        String[] options = new String[] {yesText, noText};
        int optionType = JOptionPane.YES_NO_OPTION;
        if (allowCancel) {
            optionType = JOptionPane.YES_NO_CANCEL_OPTION;
            options = new String[] {yesText, noText, "Cancel"};
        }

        int choice = JOptionPane.showOptionDialog(null, message, title, optionType, JOptionPane.INFORMATION_MESSAGE, null,
                        options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            return MessageDialogChoice.YES;
        } else if (choice == JOptionPane.NO_OPTION) {
            return MessageDialogChoice.NO;
        } else if (choice == JOptionPane.CANCEL_OPTION) {
            return MessageDialogChoice.CANCEL;
        }

        return null;
    }

    @Override
    public void prompt(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
