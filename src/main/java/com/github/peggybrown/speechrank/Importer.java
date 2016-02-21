package com.github.peggybrown.speechrank;

import java.io.IOException;
import java.util.ArrayList;
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

	String apiKey = "";

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

	public List<VideoData> importConfitura2015() {

		List<VideoData> videos = new ArrayList<>();
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

			playlistitems.setPlaylistId("PLVbNBx5Phg3Ct6pIPeWpOW37OH7F8hcMO");
			playlistitems.setKey(apiKey);
			playlistitems.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

			final PlaylistItemListResponse response = playlistitems.execute();
			final List<PlaylistItem> items = response.getItems();
			if (items != null) {
				videos = items.stream()
						.map(item -> new VideoData(item.getContentDetails().getVideoId(),
								item.getSnippet() != null ? item.getSnippet().getTitle() : "",
								item.getSnippet() != null ? item.getSnippet().getDescription() : ""))
						.collect(Collectors.toList());
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

	@Data
	@AllArgsConstructor
	public static class VideoData {
		String videoId;
		String title;
		String description;
	}


}
