package com.cadnunsdev.myfilms.models;

/**
 * Created by Tiago Silva on 10/08/2016.
 */
public class FilmeProcurado {
    private String Title;
    private String Year;
    private String imdbID;

    @Override
    public String toString() {
        return Title;
    }

    public String getTitle() {
        return Title;
    }

    public String getImdbId() {
        return imdbID;
    }
}
