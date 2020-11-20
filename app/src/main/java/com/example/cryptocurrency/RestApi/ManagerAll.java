package com.example.cryptocurrency.RestApi;

import com.example.cryptocurrency.model.ResponseCoins;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }


    public Call<ResponseCoins> coinList() {
        Call<ResponseCoins> x=getRestApi().coins();
        return x;
    }




}
