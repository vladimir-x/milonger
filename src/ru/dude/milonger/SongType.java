package ru.dude.milonger;

/**
 * Created by dude on 21.05.2017.
 */
public enum SongType {

    NONE(""),
    STANDART(""),
    MILONGUE(""),
    NUEVO(""),
    ALTERNATIVE(""),
    CORDINA(""),
    ;

    SongType(String label) {
        this.label = label;
    }

    String label;
}
