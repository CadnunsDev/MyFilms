package com.cadnunsdev.myfilms.services.api;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Tiago Silva on 11/08/2016.
 */
public class RetrofitServiceBase {
    public static Retrofit buildRetrofit(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
