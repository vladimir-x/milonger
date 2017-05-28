package ru.dude.milonger;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dude on 27.05.2017.
 */
public class SongTransferHandler extends TransferHandler {

    JTable table;
    JScrollPane scrollPane;


    public SongTransferHandler(JTable table, JScrollPane scrollPane) {
        this.table = table;
        this.scrollPane = scrollPane;
    }

    private final DataFlavor localObjectFlavor = new DataFlavor(Integer.class, "Object Row Index");
    private final DataFlavor fileObjectFlavor = new DataFlavor("application/x-java-file-list;class=java.util.List", "File list");

    @Override
    protected Transferable createTransferable(JComponent c) {
        System.out.println("createTransferable");
        // assert (c == table);
        return new DataHandler((Integer) table.getSelectedRow(), localObjectFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        boolean b = info.isDrop();
        b &= info.getComponent() == table || info.getComponent() == scrollPane;
        b &= info.isDataFlavorSupported(fileObjectFlavor) || info.isDataFlavorSupported(localObjectFlavor);
        table.setCursor(b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
        System.out.println("canImport " + b);
        return b;
    }


    @Override
    public int getSourceActions(JComponent c) {
        System.out.println("getSourceActions");
        return TransferHandler.COPY_OR_MOVE;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        System.out.println("importData");

        int index = table.getModel().getRowCount();
        if (info.getDropLocation() instanceof  JTable.DropLocation){
            JTable.DropLocation dl = (JTable.DropLocation) info.getDropLocation();
            index = dl.getRow();
            int max = table.getModel().getRowCount();
            if (index < 0 || index > max)
                index = max;
        }
        table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        try {

            SongTableModel model = (SongTableModel) table.getModel();

            List<DataFlavor> dataFlavors = Arrays.asList(info.getTransferable().getTransferDataFlavors());
            if (dataFlavors.contains(fileObjectFlavor)) {
                List<File> transferData = (List<File>) info.getTransferable().getTransferData(fileObjectFlavor);
                for (File file : transferData) {
                    model.putSong(Song.fromFile(file),index++);
                }
            }
            if (dataFlavors.contains(localObjectFlavor)) {
                model.reorder(table.getSelectedRows(), index);
            }

            model.fireTableDataChanged();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable t, int act) {
        System.out.println("exportDone");
        if ((act == TransferHandler.MOVE) || (act == TransferHandler.NONE)) {
            table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
