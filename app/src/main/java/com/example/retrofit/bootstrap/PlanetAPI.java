package com.example.retrofit.bootstrap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetAPI {
    public static final String ENDPOINT = "https://swapi.dev/api/";

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
