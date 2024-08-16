package com.example.postandgetdataretrofit;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandeler {
    private static RetrofitService retrofitService;
    public static String BASE_URL = "http://192.168.0.110:3000";
    public static RetrofitService getRetrofit() {
        if (retrofitService != null) return retrofitService;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         retrofitService = retrofit.create(RetrofitService.class);
        return retrofitService;
    }
}
