package com.challenge.myapplication.network;


import okhttp3.Response;

public class ResponseHandler {
    private Response response;

    public ResponseHandler(Response response) {
        this.response = response;
    }

    public String returnMessage(){
        String responseMessage;
        switch (response.code()){
            case 404:{
                responseMessage="Payment Methods could not be discovered now,";
                break;
            }
            case 500:{
                responseMessage="Oops! There is some problem on our server!";
                break;
            }
            case 200: {
                return "OK";
            }
            default: {
                responseMessage="Something went wrong,";
                break;
            }
        }
        responseMessage+=" Try again later";
        return responseMessage;

    }
}
