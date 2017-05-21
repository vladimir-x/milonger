package ru.dude.milonger;

import ru.dude.milonger.utils.FileDrop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
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
    private JScrollPane ScrollPane;

    private void initUIComponents() {

/*
        TableColumn idCol = new TableColumn();
        idCol.setHeaderValue("id");
        idCol.setModelIndex(0);
        TableColumn nameCol = new TableColumn();
        idCol.setHeaderValue("name");
        idCol.setModelIndex(1);
        playTable.addColumn(nameCol);
        playTable.setTableHeader(new JTableHeader());
*/

        playTable.setModel(new SongTableModel());
        playTable.getTableHeader();
/*
        playTable.setTransferHandler(new TransferHandler(){


        });
        */


        new FileDrop(ScrollPane, new FileDrop.Listener() {
            @Override
            public void filesDropped(File[] files) {

                SongTableModel model = (SongTableModel) playTable.getModel();

                for (File f:files){
                    model.addSong(Song.fromFile(f));
                }
            }
        });

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
