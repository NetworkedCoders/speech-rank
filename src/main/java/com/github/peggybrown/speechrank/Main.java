package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.delivery.rest.RatpackRestServer;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class Main {

    public static void main(String... args) throws Exception {
        String apiKey = ConfigFactory.parseFile(new File("api.conf"))
                       .getString("youtube.apiKey");

                    new RatpackRestServer(new com.github.peggybrown.speechrank.ConferencesRepository(apiKey), options)
                .start();
    }

    private static String options =
            "GET /api/conferences\n" +
            "GET /api/conference/:id\n" +
            "POST /api/rating\n" +
            "POST /api/comment\n" +
            "POST /api/import\n";

}
