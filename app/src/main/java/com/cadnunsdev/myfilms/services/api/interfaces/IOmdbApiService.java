package com.cadnunsdev.myfilms.services.api.interfaces;

import com.cadnunsdev.myfilms.models.FilmeProcurado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Tiago Silva on 10/08/2016.
 */
public interface IOmdbApiService {
    @GET("?s={keywords}&y=&plot=short&r=json")
    Call<List<FilmeProcurado>> Buscar(@Path("keywords")String palavraChaves);
}
