package com.challenge.myapplication.viewmodel;

import android.util.Log;

import com.challenge.myapplication.model.rootResponse;
import com.challenge.myapplication.network.APIService;
import com.challenge.myapplication.network.ResponseHandler;
import com.challenge.myapplication.network.RetroInstance;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class paymentMethodViewModel extends ViewModel {


    private MutableLiveData<rootResponse> jsonList;
    private MutableLiveData<String> messageMutableLiveData;

    public paymentMethodViewModel(){
        jsonList = new MutableLiveData<>();
        messageMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getMessageMutableLiveDataObserver(){
        return messageMutableLiveData;
    }

    public MutableLiveData<rootResponse> getDetailedJsonObserver() {
        return jsonList;

    }
    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<rootResponse> call = apiService.getDetailedJson();
        call.enqueue(new Callback<rootResponse>() {
            @Override
            public void onResponse(Call<rootResponse> call, Response<rootResponse> response) {


                jsonList.postValue(response.body());
                messageMutableLiveData.postValue(new ResponseHandler(response.raw()).returnMessage());

            }

            @Override
            public void onFailure(Call<rootResponse> call, Throwable t) {

                jsonList.postValue(null);
                messageMutableLiveData.postValue("Oops! Something Went Wrong, Are you connected to the internet?");


            }
        });
    }

}
