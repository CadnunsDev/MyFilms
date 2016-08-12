package com.cadnunsdev.myfilms.models;

/**
 * Created by Tiago Silva on 10/08/2016.
 */
public class FilmeProcurado {
    protected String Title;
    protected String Year;
    protected String imdbID;

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

    public String getYear() {
        return Year;
    }
}
