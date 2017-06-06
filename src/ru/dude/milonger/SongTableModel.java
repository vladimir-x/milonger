package ru.dude.milonger;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dude on 21.05.2017.
 */
public class SongTableModel extends AbstractTableModel {

    private static Integer identifier = 0;

    private List<Song> songs;

    private List<SongColumnConfig> columns;

    JTable playTable;

    SongTableModel(JTable playTable){
        this.playTable = playTable;
    }

    public void init(){
        songs = new ArrayList<>();
        columns = new ArrayList<>();


        while (playTable.getColumnCount()>0){
            TableColumn column = playTable.getColumnModel().getColumn(0);
            playTable.removeColumn(column);
        }

        int k = 0;
        for (SongColumnConfig scc : SongColumnConfig.values()) {
            columns.add(scc);
            TableColumn column = new TableColumn(k++);
            column.setWidth(scc.getWidth());
            column.setHeaderValue(scc.getTitile());
        }
    }

    @Override
    public int getRowCount() {
        return songs.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (columnIndex<0 || columnIndex>=columns.size()) return -1;
        if (rowIndex<0 || rowIndex>=songs.size()) return -1;

        SongColumnConfig scc = columns.get(columnIndex);
        return scc.getValue(songs.get(rowIndex));
    }

    public void addSong(Song song) {
        song.setId(identifier++);
        songs.add(song);
    }

    public void putSong(Song song,Integer index) {
        song.setId(identifier++);
        songs.add(index,song);
    }

    public void reorder(int[] rowFrom, int index) {

        //вырезаем
        List<Song> cutted = new ArrayList<>();
        for (int i : rowFrom) {
            cutted.add( songs.get(i));
            if (i<index) index-=1;
        }
        if (index<0)index=0;
        songs.removeAll(cutted);

        //вставляем
        for (Song song : cutted) {
            if (index>=songs.size()){
                songs.add(song);
            } else {
                songs.add(index,song);
            }
            index++;
        }
    }
}
