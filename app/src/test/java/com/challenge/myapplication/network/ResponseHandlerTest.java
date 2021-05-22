package com.challenge.myapplication.network;

import org.junit.Test;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;

import static org.junit.Assert.*;

public class ResponseHandlerTest {

    @Test
    public void returnMessage() {
        Response response = new Response.Builder()
                .request(new Request.Builder().url("http://url.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(400).message("").body(
                        ResponseBody.create(
                                MediaType.parse("application/json"),"{Mock:Json}"

                        ))
                .build();
        assertEquals("OK",new ResponseHandler(response).returnMessage());


    }
}