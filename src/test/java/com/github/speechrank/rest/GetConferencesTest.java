package com.github.speechrank.rest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class GetConferencesTest {

    @Test
    public void conferences() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
        Request request = new Request.Builder()
            .url("https://76ea5e2c-c86f-4a27-baf2-c1667b6a1f35.mock.pstmn.io/api/conferences")
            .method("GET", null)
            .build();
        Response response = client.newCall(request).execute();

        //check is content-type is application/json
        assertEquals("text/html; charset=utf-8", response.header("content-type"));

        //check if status code is 200
        assertEquals(200, response.code());

        //check responseBody
        String responseBody = response.body().string();
        assertEquals("[{\"year\":\"2019\",\"conferences\":[{\"id\":\"12\",\"name\":\"DevConf\",\"presentations\":25},{\"id\":\"31\",\"name\":\"Boiling Frogs\",\"presentations\":25},{\"id\":\"41\",\"name\":\"Scalar\",\"presentations\":16},{\"id\":\"51\",\"name\":\"Confitura\",\"presentations\":25}]},{\"year\":\"2018\",\"conferences\":[{\"id\":\"21\",\"name\":\"Boiling Frogs\",\"presentations\":25},{\"id\":\"51\",\"name\":\"Confitura\",\"presentations\":25}]},{\"year\":\"2017\",\"conferences\":[{\"id\":\"11\",\"name\":\"DevConf\",\"presentations\":25}]}]", responseBody);
        //TODO: powyzsza asercje napewno da sie ulepszyc, ale jeszcze nie wiem jak :)
    }
}
