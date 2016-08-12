package com.cadnunsdev.myfilms.models.api;

import com.cadnunsdev.myfilms.models.FilmeProcurado;

/**
 * Created by Tiago Silva on 12/08/2016.
 */
public class FilmeDetalhado extends FilmeProcurado{
    private String Rated    ;
    private String Released ;
    private String Runtime  ;
    private String Genre    ;
    private String Director ;
    private String Writer   ;
    private String Actors   ;
    private String Plot     ;
    private String Language ;
    private String Country  ;
    private String Awards   ;
    private String Poster   ;
    private String Metascore   ;
    private String imdbRating;
    private String imdbVotes;
    private String Type     ;
    private boolean Response;
    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public boolean getResponse() {
        return Response;
    }


}
