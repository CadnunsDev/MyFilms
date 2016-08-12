package com.cadnunsdev.myfilms.services.api.interfaces;

import com.cadnunsdev.myfilms.models.FilmeProcurado;
import com.cadnunsdev.myfilms.models.api.FilmeDetalhado;
import com.cadnunsdev.myfilms.models.api.OmdbSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tiago Silva on 10/08/2016.
 */
public interface IOmdbApiService {
    @GET("?y=&plot=short&r=json")
    Call<OmdbSearchResponse> Buscar(@Query("s")String palavraChaves);

    @GET("?y=&plot=short&r=json")
    Call<FilmeDetalhado> GetDetalhes(@Query("i")String imdbID);
}
