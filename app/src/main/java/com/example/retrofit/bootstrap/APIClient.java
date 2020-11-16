package com.example.retrofit.bootstrap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//PASSO 3 Cliar classe para mapear ENDPOINT e
// configurar classe para fazer o PARSE. Converter JSON para objeto
public class APIClient {
    //No retrofit você precisa colocar o / (slash)
    //Prezado, por gentileza, informar fim de instrução (/) no endereço informado.
    public static final String ENDPOINT = "https://jsonplaceholder.typicode.com/";

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
