package com.cadnunsdev.myfilms.services.api;
import com.cadnunsdev.myfilms.models.FilmeProcurado;
import com.cadnunsdev.myfilms.services.api.interfaces.IOmdbApiService;
import java.util.List;
import retrofit2.*;

/**
 * Created by Tiago Silva on 11/08/2016.
 */
public class OmdbService extends RetrofitServiceBase{
    private static final String BASE_URL = "http://www.omdbapi.com/";

    public static void Search(String keyWords, Callback<List<FilmeProcurado>> callback){
        Retrofit retrofit = buildRetrofit(BASE_URL);
        IOmdbApiService service = retrofit.create(IOmdbApiService.class);
        Call<List<FilmeProcurado>> request = service.Buscar(keyWords);
        request.enqueue(callback);
    }
}
