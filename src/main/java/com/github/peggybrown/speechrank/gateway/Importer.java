package com.github.peggybrown.speechrank.gateway;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Importer {

    String apiKey;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private static YouTube youtube;

    /**
     * Define a global instance of the HTTP transport.
     */
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Define a global instance of the JSON factory.
     */
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    public Importer(String apiKey) {
        this.apiKey = apiKey;
    }


    public javaslang.collection.List<VideoData> importFromYouTubePlaylist(String playlistId) {
        javaslang.collection.List<VideoData> videos = javaslang.collection.List.empty();
        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, request -> {
            }).setApplicationName("Speech Rank").build();

            // Define the API request for retrieving search results.
            YouTube.PlaylistItems.List playlistitems = youtube.playlistItems().list("contentDetails,snippet");

            playlistitems.setPlaylistId(playlistId);
            playlistitems.setKey(apiKey);
            playlistitems.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            final PlaylistItemListResponse response = playlistitems.execute();
            final List<PlaylistItem> items = response.getItems();
            if (items != null) {
                videos = javaslang.collection.List.ofAll(items.stream()
                    .map(item -> new VideoData(item.getContentDetails().getVideoId(),
                        item.getSnippet() != null ? item.getSnippet().getTitle() : "",
                        item.getSnippet() != null ? item.getSnippet().getDescription() : ""))
                    .collect(Collectors.toList()));
            }
            return videos;
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return videos;

    }

    public javaslang.collection.List<VideoData> importConfitura2019() {
        return importFromYouTubePlaylist("PLVbNBx5Phg3AwVti8rYNqx7965pgfMZWO");
    }

    public javaslang.collection.List<VideoData> importConfitura2018() {
        return importFromYouTubePlaylist("PLVbNBx5Phg3DkJO00oMB2ETHFmG7RUujm");
    }

    public javaslang.collection.List<VideoData> importBoilingFrogs2019() {
        return importFromYouTubePlaylist("PLVT0blg4rCWCUv3oEMQ12haQkMQ1drefo");
    }

    public javaslang.collection.List<VideoData> importBoilingFrogs2018() {
        return importFromYouTubePlaylist("PLVT0blg4rCWCEPTY20ZrZeGNQUD_2khrE");
    }

    public javaslang.collection.List<VideoData> importScalar2019() {
        return importFromYouTubePlaylist("PL8NC5lCgGs6MYG0hR_ZOhQLvtoyThURka");
    }

    public javaslang.collection.List<VideoData> importDevConf2019() {
        return importFromYouTubePlaylist("PL8BUDiR2Y8Ys3DHzQhws4BZng8DjvEwib");
    }

    public javaslang.collection.List<VideoData> importDevConf2017() {
        return importFromYouTubePlaylist("PL8BUDiR2Y8Yu3SFzqhRWqDrF9OdejbeV0");
    }

    @Data
    @AllArgsConstructor
    public static class VideoData {
        String videoId;
        String title;
        String description;
    }


}
