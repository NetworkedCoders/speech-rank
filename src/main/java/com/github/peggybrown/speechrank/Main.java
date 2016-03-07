package com.github.peggybrown.speechrank;

import com.github.peggybrown.speechrank.delivery.rest.RatpackRestServer;

public class Main {

    public static void main(String... args) throws Exception {
        new RatpackRestServer(new ConferencesRepository(), options)
                .start();
    }

    private static String options =
            "GET /api/conferences\n" +
            "GET /api/conference/:id\n" +
            "POST /api/rating\n" +
            "POST /api/comment\n" +
            "POST /api/import\n";

}
