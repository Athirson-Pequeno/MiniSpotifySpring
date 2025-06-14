package org.tizo.minispotifyspring.model;

public enum Genero {
    ROCK(1,"Rock"),
    POP(2,"POP"),
    MPB(3, "MPB"),
    JAZZ (4, "Jazz"),
    CLASSICA(5,"Classica");


    private final int indice;
    private final String name;

    Genero(int indice, String name) {
        this.indice = indice;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getIndice() {
        return indice;
    }


    public static Genero porIndice(int indice) {
        for (Genero c: Genero.values()) {
            if(c.getIndice()== indice) {
                return c;
            }
        }
        return null;
    }
    }
