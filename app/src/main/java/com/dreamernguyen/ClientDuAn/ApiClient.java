package com.dreamernguyen.ClientDuAn;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class ApiClient {
    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient.Builder().build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.32:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;

    }
}
