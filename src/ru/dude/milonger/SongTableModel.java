package ru.dude.milonger;

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


    SongTableModel(){
        songs = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return songs.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return songs.get(rowIndex).getId();
            case 1: return songs.get(rowIndex).getName();
            default:return -1;
        }
    }

    public void addSong(Song song) {
        song.setId(identifier++);
        songs.add(song);
    }
}
