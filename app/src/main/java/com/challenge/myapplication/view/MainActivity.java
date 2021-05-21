package com.challenge.myapplication.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import com.android.volley.RequestQueue;
import com.challenge.myapplication.R;
import com.challenge.myapplication.adapter.PaymentMethodListAdapter;
import com.challenge.myapplication.model.ApplicableItem;
import com.challenge.myapplication.model.rootResponse;
import com.challenge.myapplication.viewmodel.paymentMethodViewModel;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements PaymentMethodListAdapter.ItemClickListener {

    private RequestQueue mQueue;
    private paymentMethodViewModel viewModel;
    private rootResponse rootResponseList;
    private PaymentMethodListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider(this).get(paymentMethodViewModel.class);
        viewModel.getMoviesListObserver().observe(this, new Observer<rootResponse>() {
            @Override
            public void onChanged(rootResponse rootResponses) {
                if (rootResponses != null) {
                    rootResponseList = rootResponses;
                    adapter =  new PaymentMethodListAdapter(MainActivity.this, rootResponses.getNetworks().getApplicable(),  MainActivity.this);
                    recyclerView.setAdapter(adapter);

                    adapter.setMovieList(rootResponses.getNetworks().getApplicable());
                    //noresult.setVisibility(View.GONE);
                }
                else {
                    //noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();


    }

    @Override
    public void onMovieClick(ApplicableItem movie) {

    }
}