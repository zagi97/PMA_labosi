package com.example.myapplication;

import java.net.URI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static  ApiManager instance;
    private RetrofitCourse service;

    private ApiManager()
    {
        Retrofit.Builder builder = new Retrofit.Builder();
        //postavljanje retroﬁt-a
        Retrofit retrofit = builder.baseUrl("https://catalog-api.udacity.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitCourse.class);
    }

    public static  ApiManager getInstance()
    {
        if (instance == null)
        {
            instance = new ApiManager();
        }
        return  instance;
    }

    public RetrofitCourse service()
    {
        return service;
    }
}
