package com.challenge.myapplication.network;

import com.challenge.myapplication.model.rootResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("listresult.json")
    Call<rootResponse> getDetailedJson();
}
