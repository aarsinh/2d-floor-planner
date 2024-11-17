package src.main.java.com.floorplanner;

import javax.swing.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ElementTransferHandler extends TransferHandler {
    private final String value;

    public ElementTransferHandler(String value) {
        this.value = value;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new StringSelection(value);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }
}
