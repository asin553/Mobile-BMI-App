package com.example.bmicalculatormvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelREST implements ModelService{

    @Override
    public ModelRESTfulAPI createRESTAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ModelRESTfulAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ModelRESTfulAPI WebREST = retrofit.create(ModelRESTfulAPI.class);
        System.out.println("BMI REST API Service configured.");

        return WebREST;
    }

    @Override
    public ModelRESTfulAPI RESTAPIWebService() {
        return null;
    }
}