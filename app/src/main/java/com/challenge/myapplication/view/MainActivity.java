package com.challenge.myapplication.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.challenge.myapplication.R;
import com.challenge.myapplication.adapter.PaymentMethodListAdapter;
import com.challenge.myapplication.model.ApplicableItem;
import com.challenge.myapplication.model.rootResponse;
import com.challenge.myapplication.network.ResponseHandler;
import com.challenge.myapplication.viewmodel.paymentMethodViewModel;
import com.google.android.material.button.MaterialButton;




public class MainActivity extends AppCompatActivity implements PaymentMethodListAdapter.ItemClickListener {

    private RequestQueue mQueue;
    private paymentMethodViewModel viewModel;
    private rootResponse rootResponseList;
    private PaymentMethodListAdapter adapter;
    private TextView loadingTV,oopsTV;
    private ProgressBar loadingPB;
    private MaterialButton tryAgainBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingPB=findViewById(R.id.PBloading);
        oopsTV=findViewById(R.id.TVoops);
        loadingTV=findViewById(R.id.TVloading);
        tryAgainBT=findViewById(R.id.BTtryagain);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider(this).get(paymentMethodViewModel.class);
        viewModel.getMessageMutableLiveDataObserver().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.equals("OK")){

                    oopsTV.setText(s);
                    oopsTV.setVisibility(View.VISIBLE);
                }
                else {
                    oopsTV.setVisibility(View.GONE);
                }

            }
        });
        viewModel.getDetailedJsonObserver().observe(this, new Observer<rootResponse>() {
            @Override
            public void onChanged(rootResponse rootResponses) {
                if (rootResponses != null) {
                    rootResponseList = rootResponses;
                    adapter =  new PaymentMethodListAdapter(MainActivity.this, rootResponses.getNetworks().getApplicable(),  MainActivity.this);
                    recyclerView.setAdapter(adapter);

                    adapter.setMovieList(rootResponses.getNetworks().getApplicable());


                    hideProgress();
                }
                else {
                        hideProgress();
                        showOops();
                }
            }
        });
        viewModel.makeApiCall();


        tryAgainBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.makeApiCall();
                showProgress();
                hideOops();
            }
        });

    }

    void showProgress(){

     loadingPB.setVisibility(View.VISIBLE);
     loadingTV.setVisibility(View.VISIBLE);
    }
    void showOops(){
        oopsTV.setVisibility(View.VISIBLE);
        tryAgainBT.setVisibility(View.VISIBLE);
    }
    void hideOops(){
        oopsTV.setVisibility(View.GONE);
        tryAgainBT.setVisibility(View.GONE);
    }
    void hideProgress(){

        loadingPB.setVisibility(View.GONE);
        loadingTV.setVisibility(View.GONE);

    }
    void loadErrorMessage(String message){
        oopsTV.setText(message);
    }

    @Override
    public void onMethodClick(ApplicableItem method) {
        Toast.makeText(this,""+method.getLabel()+" Selected as Payment Method",Toast.LENGTH_SHORT).show();

    }
}