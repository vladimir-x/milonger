package ru.dude.milonger;

import java.io.File;

/**
 * Created by dude on 21.05.2017.
 */
public class Song {

    Integer id;
    String name;
    String path;

    SongType type;
    Integer tandaId;

    public static Song fromFile(File f){
        Song s = new Song();
        s.name = f.getName();
        s.path = f.getAbsolutePath();
        s.type = SongType.NONE;
        return s;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public SongType getType() {
        return type;
    }

    public void setType(SongType type) {
        this.type = type;
    }

    public Integer getTandaId() {
        return tandaId;
    }

    public void setTandaId(Integer tandaId) {
        this.tandaId = tandaId;
    }
}
