package com.example.cryptocurrency.RestApi;

import com.example.cryptocurrency.model.ResponseCoins;

import retrofit2.Call;
import retrofit2.http.GET;


public interface RestApi {

   @GET("coins")
   Call<ResponseCoins> coins();



}
