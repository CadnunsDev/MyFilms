package com.cadnunsdev.myfilms.api;

import com.cadnunsdev.myfilms.models.FilmeProcurado;
import com.cadnunsdev.myfilms.services.api.OmdbService;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tiago Silva on 11/08/2016.
 */
public class SearchTest {
    @Test
    public void GetListResultsFromApi(){
        String keyWords = "Justice";
        OmdbService.Search(keyWords, new Callback<List<FilmeProcurado>>() {
            @Override
            public void onResponse(Call<List<FilmeProcurado>> call, Response<List<FilmeProcurado>> response) {
                List<FilmeProcurado> result = response.body();
                Assert.assertTrue("OK", result.size() > 0 );
            }

            @Override
            public void onFailure(Call<List<FilmeProcurado>> call, Throwable t) {
                Assert.assertTrue(t.getMessage(), false);
            }
        });
    }
}
