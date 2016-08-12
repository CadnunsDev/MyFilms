package com.cadnunsdev.myfilms.models.api;

import com.cadnunsdev.myfilms.models.FilmeProcurado;

import java.util.List;

/**
 * Created by Tiago Silva on 11/08/2016.
 */
public class OmdbSearchResponse {
    public List<FilmeProcurado> Search;
    public int totalResults;
    public boolean Response;
}
