package ru.dude.milonger;

/**
 * Created by dude on 29.05.2017.
 */
public enum SongColumnConfig{

    ID("ID",20, new Getable(){

        @Override
        public Object getValue(Song s) {
            return s.getId();
        }
    }),
    NAME("Имя",120, new Getable(){

        @Override
        public Object getValue(Song s) {
            return s.getName();
        }
    }),
    TYPE("Тип",80, new Getable(){

        @Override
        public Object getValue(Song s) {
            return s.getType();
        }
    }),
    TANDA("Танда",20, new Getable(){

        @Override
        public Object getValue(Song s) {
            return s.getTandaId();
        }
    }),
    PATH("Путь",250, new Getable(){

        @Override
        public Object getValue(Song s) {
            return s.getPath();
        }
    }),
    ;

    SongColumnConfig(String titile, Integer width, Getable getter) {
        this.titile = titile;
        this.width = width;
        this.getter = getter;
    }

    private static interface Getable{
        Object getValue(Song s);
    };

    String titile;
    Integer width;
    Getable getter;

    public Object getValue(Song s){
        return getter.getValue(s);
    }

    public String getTitile() {
        return titile;
    }

    public Integer getWidth() {
        return width;
    }
}
