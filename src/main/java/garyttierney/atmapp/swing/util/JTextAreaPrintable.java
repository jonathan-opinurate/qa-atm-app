package garyttierney.atmapp.swing.util;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

public class JTextAreaPrintable implements Printable {
    private final JTextArea jTextArea;

    public JTextAreaPrintable(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        if (pageIndex > 0) {
            return (NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            jTextArea.paint(g2d);

            return (PAGE_EXISTS);
        }
    }
}
