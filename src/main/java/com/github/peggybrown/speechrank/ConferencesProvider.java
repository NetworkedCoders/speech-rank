package com.github.peggybrown.speechrank;

import ratpack.handling.Context;

public class ConferencesProvider {
    static String getConference(String name){
        return conference;
    }

    public static String getConferences() {
        return conferences;
    }

    private static String conference = "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"Confitura\",\n" +
            "  \"presentations\": [{\n" +
            "    \"_id\": \"udsd-dsd-dsadas-e32rr43\",\n" +
            "    \"title\": \"2015 - Bolesław Dawidowicz - Prezentacja o standardach i protokołach bezpieczeństwa\",\n" +
            "    \"link\": \"https://www.youtube.com/watch?v=69B5EHznGu8\",\n" +
            "    \"rate\": 3.5,\n" +
            "    \"comments\": [{\n" +
            "        \"_id\": \"876546-6546g6v456-64v564-kjgu\",\n" +
            "        \"author\": \"Andrzej Bednarz\",\n" +
            "        \"twitter\": \"bednarz_andrzej\",\n" +
            "        \"content\": \"very nice!\"\n" +
            "      }, {\n" +
            "        \"_id\": \"876546-54dssv456-64v56l-kjgu \",\n" +
            "        \"author\": \"Ola Kunysz\",\n" +
            "        \"twitter\": \"OlaWaliczek\",\n" +
            "        \"content\": \"very good!\"\n" +
            "      }\n" +
            "\n" +
            "    ]\n" +
            "  }]\n" +
            "}";

    private static String conferences = "[{\n" +
            "  \"year\": 2015,\n" +
            "  \"conferences\": [{\n" +
            "    \"id\": \"1\",\n" +
            "    \"name\": \"Confitura\",\n" +
            "    \"presentations\": 12\n" +
            "  }, {\n" +
            "    \"id\": \"2\",\n" +
            "    \"name\": \"Geecon\",\n" +
            "    \"presentations\": 23\n" +
            "  }]\n" +
            "}, {\n" +
            "  \"year\": 2014,\n" +
            "  \"conferences\": [{\n" +
            "    \"id\": \"3\",\n" +
            "    \"name\": \"Confitura\",\n" +
            "    \"presentations\": 10\n" +
            "  }, {\n" +
            "    \"id\": \"4\",\n" +
            "    \"name\": \"Geecon\",\n" +
            "    \"presentations\": 20\n" +
            "  }]\n" +
            "}]";
}
