package garyttierney.atmapp.swing.util;

public interface MessageDialogHelper {
    String promptWithInput(String title, String question);

    MessageDialogChoice promptWithChoice(String title, String message, String yesText, String noText, boolean allowCancel);

    default MessageDialogChoice promptWithChoice(String title, String message, boolean allowCancel) {
        return promptWithChoice(title, message, "Yes", "No", allowCancel);
    }

    void prompt(String title, String message);

    public static enum MessageDialogChoice {
        YES, NO, CANCEL
    }
}
