package com.challenge.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {


    public static String BASE_URL = "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/";//volley_array.json

    private static Retrofit retrofit;

    public static Retrofit getRetroClient() {

        if(retrofit == null ) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
