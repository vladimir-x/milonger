package ru.dude.milonger;

import javax.activation.DataHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import java.io.File;

/**
 * Created by dude on 14.05.2017.
 */
public class MainForm {
    private JPanel workPanel;
    private JList playList;
    private JButton refreshBtn;
    private JButton browseBtn;
    private JPanel browsePanel;
    private JTextField textField1;
    private JTable playTable;
    private JTree tree1;
    private JScrollPane scrollPane;

    private void initUIComponents() {

        playTable.setModel(new SongTableModel());

        playTable.setDragEnabled(true);
        playTable.setDropMode(DropMode.INSERT_ROWS);

        SongTransferHandler songTransferHandler = new SongTransferHandler(playTable,scrollPane);
        playTable.setTransferHandler(songTransferHandler);
        scrollPane.setTransferHandler(songTransferHandler);

        File f = new File("D:\\datastudio\\");
        for (File file : f.listFiles()) {
            ((SongTableModel) playTable.getModel()).addSong(Song.fromFile(file));
        }

    }

    public static void main(String args[]){

        new MainForm();
    }

    public MainForm(){

        initUIComponents();

        JFrame frame = new JFrame("MilongerPlayer");
        //frame.add(new MainForm().workPanel);
        frame.add(workPanel);
        frame.setMenuBar(makeMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private MenuBar makeMenuBar() {
        MenuBar mbar = new MenuBar();

        Menu m = new Menu("LAL");
        mbar.add(m);
        return mbar;

    }
}
