package com.example.noteapp.ModelView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class StartApi {
//    private static final Gson gson = new GsonBuilder().setLenient().create();
    private static final BackendApi retrofit=new Retrofit.Builder()
            .baseUrl("https://noteify-service.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackendApi.class);
    public static BackendApi getRetrofit() {
        return retrofit;
    }
}
