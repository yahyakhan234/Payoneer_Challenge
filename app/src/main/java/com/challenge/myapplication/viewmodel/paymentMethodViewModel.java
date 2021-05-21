package com.challenge.myapplication.viewmodel;

import com.challenge.myapplication.model.rootResponse;
import com.challenge.myapplication.network.APIService;
import com.challenge.myapplication.network.RetroInstance;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class paymentMethodViewModel extends ViewModel {


    private MutableLiveData<rootResponse> jsonList;

    public paymentMethodViewModel(){
        jsonList = new MutableLiveData<>();
    }

    public MutableLiveData<rootResponse> getMoviesListObserver() {
        return jsonList;

    }
    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<rootResponse> call = apiService.getMovieList();
        call.enqueue(new Callback<rootResponse>() {
            @Override
            public void onResponse(Call<rootResponse> call, Response<rootResponse> response) {
                jsonList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<rootResponse> call, Throwable t) {
                jsonList.postValue(null);
            }
        });
    }

}
