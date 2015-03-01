package garyttierney.atmapp.swing.action;

import garyttierney.atmapp.ATMApplicationContext;
import garyttierney.atmapp.swing.util.JTextAreaPrintable;
import garyttierney.atmapp.swing.view.CustomerValidationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class CloseTransactionAndPrintReceiptAction extends AbstractAction {
    private final JTextArea receiptTextArea;
    private final ATMApplicationContext context;

    public CloseTransactionAndPrintReceiptAction(ATMApplicationContext context, JTextArea receiptTextArea) {
        this.context = context;
        this.receiptTextArea = receiptTextArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new JTextAreaPrintable(receiptTextArea));
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex); //@todo - handle
            }
        }

        context.switchTo(new CustomerValidationView(context));
    }
}