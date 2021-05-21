package com.challenge.myapplication.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class get_payment_methods_json {

    public static final String JSON_DOWNLOAD_LINK = "https://raw.githubusercontent.com/optile/checkout-android/develop/shared-test/lists/listresult.json";
    private String json;
    private RequestQueue mQueue;

    public void getJsonFromServer() {

        String url = get_payment_methods_json.JSON_DOWNLOAD_LINK;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println(response.toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });



    }
}