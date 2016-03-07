package com.github.peggybrown.speechrank.gateway;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

@Data
public class Importer {

    String apiKey = "xyz";

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


    private javaslang.collection.List<VideoData> importFromYouTubePlaylist(String playlistId) {
        javaslang.collection.List<VideoData> videos = javaslang.collection.List.empty();
        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("andbed-1227").build();

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
        return null;

    }

    public javaslang.collection.List<VideoData> importConfitura2015() {
        return importFromYouTubePlaylist("PLVbNBx5Phg3Ct6pIPeWpOW37OH7F8hcMO");
    }

    public javaslang.collection.List<VideoData> importConfitura2014() {
        return importFromYouTubePlaylist("PLVbNBx5Phg3C_NhQauABRuX8Jr58LKX_u");
    }

    public javaslang.collection.List<VideoData> importDevoxxUK2015() {
        return importFromYouTubePlaylist("PLklQqdqnBkPjP1fyt0Y-OF90Bnx_PFo-V");
    }

    public javaslang.collection.List<VideoData> importHackSummit2016() {
        return importFromYouTubePlaylist("PL3awhJ17Z2rtmy3r9hXnFtAvdY0rs788e");
    }

    @Data
    @AllArgsConstructor
    public static class VideoData {
        String videoId;
        String title;
        String description;
    }


}
